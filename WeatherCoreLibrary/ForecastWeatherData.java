package WeatherCoreLibrary;

import org.json.JSONArray;
import org.json.JSONObject;

// Define ForecastWeatherData Data Type and implement functions to set/get data.
public class ForecastWeatherData {
	public WeatherData[] forecastDataArr = new WeatherData[3];
	
	public void setForecastWeatherData(JSONArray owmAllForecastData) throws WeatherDataServiceException{
		try{
			for(int i=0;i<3;i++)
			{
				JSONObject owmAllData = owmAllForecastData.getJSONObject(i+1); // start from next day
				this.forecastDataArr[i] = new WeatherData();
				try{
					this.forecastDataArr[i].setTemperature(owmAllData.getJSONObject("temp").get("day").toString());
					this.forecastDataArr[i].setHumidity(owmAllData.get("humidity").toString());
					this.forecastDataArr[i].setLastUpdate(WeatherData.setLastUpdateTime(owmAllData.get("dt").toString()));
					this.forecastDataArr[i].setWeatherMain(owmAllData.getJSONArray("weather").getJSONObject(0).get("main").toString());
					this.forecastDataArr[i].setWeatherDesc(owmAllData.getJSONArray("weather").getJSONObject(0).get("description").toString());
				}
				catch(Exception SetWeatherDataException){
					throw new WeatherDataServiceException(SetWeatherDataException.getMessage());
				}
			}
		}
		catch(Exception SetWeatherDataException){
			throw new WeatherDataServiceException(SetWeatherDataException.getMessage());
		}
	}
	
	public WeatherData[] getForecastWeatherData(){
		return this.forecastDataArr;
	}

}

