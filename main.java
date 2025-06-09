import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Simple Weather App");
        System.out.print("Enter city name: ");
        String city = scanner.nextLine().trim();

        // Fetch coordinates (latitude, longitude) for the given city
        double[] coords = getCoordinates(city);
        if (coords != null) {
            getWeather(coords[0], coords[1]);
        } else {
            System.out.println(" Could not get coordinates for the city.");
        }
    }

    // Method to get latitude and longitude using Open-Meteo Geocoding API
    public static double[] getCoordinates(String city) {
        try {
            String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city.replace(" ", "%20") + "&count=1";
            // Create HTTP client and send GET request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();
            // Extract latitude and longitude from JSON using regex
            double lat = extractNumber(body, "\"latitude\":([\\d.\\-]+)");
            double lon = extractNumber(body, "\"longitude\":([\\d.\\-]+)");

            if (lat != -1 && lon != -1) {
                return new double[]{lat, lon};
            }
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
        return null;
    }

    public static void getWeather(double lat, double lon) {
        try {
            String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%.4f&longitude=%.4f&current_weather=true", lat, lon);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            String temperature = extractGroup(body, "\"temperature\":([\\d.\\-]+)");
            String windspeed = extractGroup(body, "\"windspeed\":([\\d.\\-]+)");
            String time = extractGroup(body, "\"time\":\"([^\"]+)\"");

            if (temperature != null && windspeed != null && time != null) {
                System.out.println("\n📍 Weather Info:");
                System.out.println("Temperature: " + temperature + "°C");
                System.out.println("Wind Speed:  " + windspeed + " km/h");
                
            } else {
                System.out.println("Weather data not found.");
            }
        } catch (Exception e) {
            System.out.println(" Error fetching weather: " + e.getMessage());
        }
    }

    // Helper to extract numeric value using regex
    private static double extractNumber(String text, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return -1;
    }

    // Helper to extract string group using regex
    private static String extractGroup(String text, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
