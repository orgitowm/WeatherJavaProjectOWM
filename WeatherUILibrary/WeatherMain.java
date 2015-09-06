package WeatherUILibrary;

import WeatherUILibrary.Controller;

public class WeatherMain {

	public static void main(String[] args)
	{
		WeatherFrame frame = new WeatherFrame();
		Controller controller = new Controller(frame);
	}
}
