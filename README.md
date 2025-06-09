# 🌦️ Simple Weather Client (Java)

A lightweight command-line Java application that fetches and displays real-time weather information for a given city using the [Open-Meteo](https://open-meteo.com/) public APIs.

---

## 📌 Features

- Fetches **latitude and longitude** of any city using Open-Meteo Geocoding API.
- Retrieves **current weather** (temperature and wind speed) using Open-Meteo Forecast API.
- CLI-based, easy to run and understand.
- Uses modern `HttpClient` from Java 11+.

---

## 🛠️ Requirements

- **Java 11 or higher**
- Internet connection (to make API calls)

---

## 🚀 How to Run

1. **Clone this repository** (or download the Java file):

    ```bash
    git clone https://github.com/your-username/weather-client-java.git
    cd weather-client-java
    ```

2. **Compile the Java file:**

    ```bash
    javac WeatherClient.java
    ```

3. **Run the program:**

    ```bash
    java WeatherClient
    ```

4. **Example Output:**

    ```
     Simple Weather App
     Enter city name: Mumbai

     📍 Weather Info:
     Temperature: 32.1°C
     Wind Speed:  12.3 km/h
    ```

---

## 🔍 How It Works

- The program first calls the Geocoding API:  
  `https://geocoding-api.open-meteo.com/v1/search?name=Mumbai`
- It extracts the latitude and longitude using **regex**.
- Then, it calls the Forecast API:  
  `https://api.open-meteo.com/v1/forecast?latitude=...&longitude=...&current_weather=true`
- Finally, it prints out the weather data (temperature & windspeed).

---

## 📄 API Reference

- **Geocoding API:**  
  `https://geocoding-api.open-meteo.com/v1/search?name={city}`

- **Forecast API:**  
  `https://api.open-meteo.com/v1/forecast?latitude={lat}&longitude={lon}&current_weather=true`

---

## 🧠 Future Improvements

- Use a JSON parser like `org.json` or `Gson` instead of regex.
- Add unit tests.
- Support for additional weather details (humidity, pressure, etc.).
- GUI version with JavaFX or Swing.

---

## 📜 License

This project is open-source and free to use under the [MIT License](LICENSE).

---

## 👩‍💻 Author

Made with 💻 by [Ashutosh Gite]
