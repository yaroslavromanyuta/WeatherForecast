package yaroslavromanyuta.com.ua.weatherforecast;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class DbGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1,"yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel");
        schema.enableKeepSectionsByDefault();

        Entity city = schema.addEntity("City");
        city.addIdProperty();
        city.addIntProperty("cityId");
        city.addDoubleProperty("lon");
        city.addDoubleProperty("lat");
        city.addStringProperty("country");

        Entity weather = schema.addEntity("Weather");
        weather.addIdProperty();
        weather.addDateProperty("date");
        weather.addDoubleProperty("temp");
        weather.addDoubleProperty("pressure");
        weather.addIntProperty("humidity");
        weather.addStringProperty("main");
        weather.addStringProperty("description");
        weather.addStringProperty("icon");
        weather.addIntProperty("clouds");
        weather.addIntProperty("windSec");
        weather.addIntProperty("windDeg");
        weather.addIntProperty("rain");
        weather.addIntProperty("snow");

        Property cityId = weather.addLongProperty("cityId").getProperty();
        weather.addToOne(city,cityId);

        ToMany cityToWeather = city.addToMany(weather,cityId);
        cityToWeather.setName("forecast");

        try {
            new DaoGenerator().generateAll(schema, "../app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
