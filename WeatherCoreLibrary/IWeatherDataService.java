package WeatherCoreLibrary;
import java.io.IOException;

import org.json.JSONException;

// Define main interface 
public interface IWeatherDataService {
	public WeatherData getWeatherData(Location location) throws WeatherDataServiceException, IOException, JSONException;

	public ForecastWeatherData getForecastWeatherData(Location location)
			throws WeatherDataServiceException, IOException, JSONException;
}
