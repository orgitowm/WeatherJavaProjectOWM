package WeatherCoreLibrary;

// Implement WeatherData Service Factory in singleton design pattern - Right now support only OpenWeatherMap.org
public class WeatherDataServiceFactory {
	public enum WeatherWebServices {
		OPEN_WEATHER_MAP;
	}

	// constructor
	private WeatherDataServiceFactory() {
	}

	public static IWeatherDataService getWeatherDataService(WeatherWebServices serviceRequired) {
		IWeatherDataService localIWeatherDataService = null;

		if (serviceRequired.equals(WeatherWebServices.OPEN_WEATHER_MAP)) {
			localIWeatherDataService = GetWeatherDataFromAPI.getInstance();
		}

		return localIWeatherDataService;
	}
}
