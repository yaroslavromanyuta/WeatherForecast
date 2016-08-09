package yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel;

import java.util.List;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "CITY".
 */
public class City {

    private Long id;
    private String name;
    private Double lon;
    private Double lat;
    private String country;
    private String main;
    private String description;
    private Double temp;
    private Double pressure;
    private Integer humidity;
    private Double windSpeed;
    private Double windDeg;
    private java.util.Date sunrise;
    private java.util.Date sunset;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient CityDao myDao;

    private List<Weather> forecast;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public City() {
    }

    public City(Long id) {
        this.id = id;
    }

    public City(Long id, String name, Double lon, Double lat, String country, String main, String description, Double temp, Double pressure, Integer humidity, Double windSpeed, Double windDeg, java.util.Date sunrise, java.util.Date sunset) {
        this.id = id;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.country = country;
        this.main = main;
        this.description = description;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCityDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Double windDeg) {
        this.windDeg = windDeg;
    }

    public java.util.Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(java.util.Date sunrise) {
        this.sunrise = sunrise;
    }

    public java.util.Date getSunset() {
        return sunset;
    }

    public void setSunset(java.util.Date sunset) {
        this.sunset = sunset;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Weather> getForecast() {
        if (forecast == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WeatherDao targetDao = daoSession.getWeatherDao();
            List<Weather> forecastNew = targetDao._queryCity_Forecast(id);
            synchronized (this) {
                if(forecast == null) {
                    forecast = forecastNew;
                }
            }
        }
        return forecast;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetForecast() {
        forecast = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here

    @Override
    public String toString() {
        String string = "City:  name - " + name + ", country - " + country + ", lat - " + lat + ", lon - " + lon + ", id - " +id;
        return string;
    }
    // KEEP METHODS END

}
