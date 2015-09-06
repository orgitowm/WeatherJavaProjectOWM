package WeatherCoreLibrary;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Implements IWeatherDataService Interface
public class GetWeatherDataFromAPI implements IWeatherDataService {
	// constructor
	private GetWeatherDataFromAPI() {
	}
	
	public static final String API_KEY = "1111111111";

	private static IWeatherDataService instance = null;

	public static GetWeatherDataFromAPI getInstance() {
		if (instance == null) {
			instance = new GetWeatherDataFromAPI();
		}
		return (GetWeatherDataFromAPI) instance;
	}

	// Current Weather - Build api url path, get data from web service and set data with SetWeatherData.
	@Override
	public WeatherData getWeatherData(Location location)
			throws WeatherDataServiceException, IOException, JSONException {
		// Builds the api url path, checks if the city name entered by the user contains a space and adjusts the structure to a suitable url. 
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + (location.cityName.contains(" ")?location.cityName.replace(" ", "%20"):location.cityName) + "&units=metric&&APPID=" + "API_KEY";
		WeatherData localWeatherData = new WeatherData();
		JSONObject owmAllData = readJsonFromUrl(url);
		localWeatherData.setWeatherData(owmAllData);
		return localWeatherData;
	}
	
	// Forecast Weather - Build api url path, get data from web service and set data with setForecastWeatherData.
	@Override
	public ForecastWeatherData getForecastWeatherData(Location location)
			throws WeatherDataServiceException, IOException, JSONException {
		// Builds the api url path, checks if the city name entered by the user contains a space and adjusts the structure to a suitable url. 
		String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + (location.cityName.contains(" ")?location.cityName.replace(" ", "%20"):location.cityName) + "&units=metric&cnt=4&&APPID=" + "API_KEY";
		ForecastWeatherData localForecastData = new ForecastWeatherData();
		JSONObject owmAllData = readJsonFromUrl(url);
		JSONArray owmAllForecastData = owmAllData.getJSONArray("list");
		localForecastData.setForecastWeatherData(owmAllForecastData);
		return localForecastData;
	}

	
	private static String readAll(Reader rd) throws WeatherDataServiceException, IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	// Get data from api path url.
	public static JSONObject readJsonFromUrl(String url)
			throws WeatherDataServiceException, IOException, JSONException {
		
		// Open connection with web service
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
}
