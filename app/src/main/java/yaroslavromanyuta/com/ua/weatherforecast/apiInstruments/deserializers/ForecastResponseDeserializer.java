package yaroslavromanyuta.com.ua.weatherforecast.apiInstruments.deserializers;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.City;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.ForecastResponce;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.Weather;

import static yaroslavromanyuta.com.ua.weatherforecast.Constants.*;

/**
 * Created by Yaroslav on 25.05.2016.
 */
public class ForecastResponseDeserializer implements JsonDeserializer<ForecastResponce> {
    @Override
    public ForecastResponce deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject response = json.getAsJsonObject();

        City city = new City();
        JsonObject jsonCity = response.getAsJsonObject("city");
        city.setId((long) jsonCity.get("id").getAsInt());
        city.setName(jsonCity.get("name").getAsString());
        city.setCountry(jsonCity.get("country").getAsString());
        city.setLat(jsonCity.getAsJsonObject("coord").get("lat").getAsDouble());
        city.setLon(jsonCity.getAsJsonObject("coord").get("lon").getAsDouble());


        List<Weather> weatherList = new ArrayList<>(response.get("cnt").getAsInt());
        JsonArray jsonWeatherList = response.getAsJsonArray("list");
        for (int i = 0; i < jsonWeatherList.size() ; i++) {

            Weather weather = new Weather();
            weather.setCity(city);

            JsonObject jsonWeather = jsonWeatherList.get(i).getAsJsonObject();

            weather.setId(jsonWeather.get("dt").getAsLong());
            JsonObject jsonMain = jsonWeather.getAsJsonObject("main");
            weather.setTemp(jsonMain.get("temp").getAsDouble());
            weather.setPressure(jsonMain.get("pressure").getAsDouble());
            weather.setHumidity(jsonMain.get("humidity").getAsInt());
            JsonObject jsonWeatherDescription = jsonWeather.getAsJsonObject("weather");
            weather.setMain(jsonWeatherDescription.get("main").getAsString());
            weather.setDescription(jsonWeatherDescription.get("description").getAsString());
            weather.setIcon(jsonWeatherDescription.get("icon").getAsString());
            weather.setClouds(jsonWeather.getAsJsonObject("clouds").get("all").getAsInt());
            JsonObject jsonWind = jsonWeather.getAsJsonObject("wind");
            weather.setWindSpeed(jsonWind.get("speed").getAsDouble());
            weather.setWindDeg(jsonWind.get("deg").getAsDouble());
            weather.setRain(jsonWeather.getAsJsonObject("rain").get("3h").getAsInt());
            weather.setSnow(jsonWeather.getAsJsonObject("snow").get("3h").getAsInt());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            try {
                date = format.parse(jsonWeather.get("dt_txt").getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d(TAG, "deserialize:  date exeption " + e );
            }
            weather.setDate(date);

            weatherList.set(i,weather);

        }

        return new ForecastResponce(city, weatherList);
    }
}
