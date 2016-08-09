package yaroslavromanyuta.com.ua.weatherforecast.apiInstruments.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.City;

/**
 * Created by Yaroslav on 27.05.2016.
 */
public class CityResponseDeserializer implements JsonDeserializer<City> {
    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
