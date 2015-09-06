package WeatherUILibrary;

import WeatherCoreLibrary.ForecastWeatherData;
import WeatherCoreLibrary.WeatherData;

import java.awt.event.ActionListener;

// Define of Interface that create and manage the frame.
public interface IWeatherUI {
	public void addFrameListener(ActionListener listenForButtons);
	void setFrameWithWeatherData(WeatherData todayData, ForecastWeatherData forecastData);
	void setFrameCityNotFound(String cityNotFound);
	WeatherPanel getInfoFromFrame();
}
