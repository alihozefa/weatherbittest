package utils;

public class ResourceProvider {

    public String provideCurrentWeatherDataResource(){
        String res = "/current";
        return res;
    }

    public String provideHourlyForecastResource(){
        String res = "/forecast/3hourly";
        return res;
    }

}
