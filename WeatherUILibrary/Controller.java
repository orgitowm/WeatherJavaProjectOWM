package WeatherUILibrary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import WeatherCoreLibrary.*;

// This program write in 'mvc' design pattern. Controller get data from "Model"(WeatherData,ForecastWeatherData) And send it to the "view" element that serve as a User Interface.
// The Controller Control the flow of the program, data movement and The view for the user.
public class Controller {
	
	private IWeatherUI frame;
	private IWeatherDataService weatherService;
	
	public Controller(WeatherFrame frame)
	{
		this.frame = frame;
		weatherService = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.WeatherWebServices.OPEN_WEATHER_MAP);		
		this.frame.addFrameListener(new FrameListener());
	
	}
	
	public class FrameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Location location = new Location(frame.getInfoFromFrame().getCityTextField());
			WeatherData currentWeatherData = null;
			ForecastWeatherData forecastData = null;
			try{				
				currentWeatherData = weatherService.getWeatherData(location);
			}
			catch(Exception e){
				if (e.getMessage().contains("JSONObject[\"name\"] not found" )) {
					frame.setFrameCityNotFound("Error: City not found!");		
				}
			}	
			try{
				forecastData = weatherService.getForecastWeatherData(location);
			}
			catch(Exception e){		
			}
			frame.setFrameWithWeatherData(currentWeatherData,forecastData);
		}
		
	}
	
}

