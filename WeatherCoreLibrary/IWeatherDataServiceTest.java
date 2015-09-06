package WeatherCoreLibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// Test IWeatherDataService Interface by JUnit
public class IWeatherDataServiceTest {
	
	public static final String CITY_TEST = "new york";
	public static final String EXCEPTED_COUNTRY_CODE_TEST = "US";
	public static final double EXCEPTED_MIN_TEMP = -80.0;
	public static final double EXCEPTED_MAX_TEMP = 80.0;
	
	WeatherData testWeatherData;
	IWeatherDataService localIWeatherDataService = null;
	ForecastWeatherData testForecastWeatherData;
	Location testLocation;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.testWeatherData = new WeatherData();
		this.testForecastWeatherData = new ForecastWeatherData();
		this.testLocation = new Location(CITY_TEST);
		localIWeatherDataService = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.WeatherWebServices.OPEN_WEATHER_MAP);
	}

	@After
	public void tearDown() throws Exception {
	}

	// Test IWeatherDataService.getWeatherData
	@Test
	public void testGetWeatherData() {
		try{
			testWeatherData = localIWeatherDataService.getWeatherData(testLocation);
		}
		catch(Exception e){
		}
		
		String actualCountryCode = testWeatherData.getCountryCode();
	    try{
	    	assertEquals(actualCountryCode,EXCEPTED_COUNTRY_CODE_TEST);
	        System.out.println("Country Code Test Success for getWeatherData method.");
	     }catch(AssertionError e){
	    	 System.out.println("Country Code Test Failed! for getWeatherData method.");
	    	 throw e;
	     }
	}

	// Test IWeatherDataService.getForecastWeatherData
	@Test
	public void testGetForecastWeatherData() {
		try{
			testForecastWeatherData = localIWeatherDataService.getForecastWeatherData(testLocation);
		}
		catch(Exception e){
		}
		
		String actualTemp = testForecastWeatherData.getForecastWeatherData()[1].getTemperature();
		double actualTempNum = Double.parseDouble(actualTemp);
	    try{
	    	assertTrue(EXCEPTED_MIN_TEMP <= actualTempNum && actualTempNum <= EXCEPTED_MAX_TEMP);
	        System.out.println("Temperature Test Success for getForecastWeatherData method.");
	     }catch(AssertionError e){
	    	System.out.println("Temperature Test Failed! for getForecastWeatherData method.");

	        throw e;
	     }
		}

}
