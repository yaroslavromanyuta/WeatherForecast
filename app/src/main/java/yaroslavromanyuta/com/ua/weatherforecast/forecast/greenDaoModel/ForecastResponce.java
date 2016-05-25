package yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel;

import java.util.List;

/**
 * Created by Yaroslav on 25.05.2016.
 */
public class ForecastResponce {

    private City city;
    private  List<Weather> weather;

    public ForecastResponce(){}

    public ForecastResponce (City city){
        this.city = city;

        weather = city.getForecast();
    }

    public ForecastResponce(City city, List<Weather> weather){
        this.weather = weather;
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {

        String string = "Forecast resp for " + city.getName();
        return string;
    }
}
