package yaroslavromanyuta.com.ua.weatherforecast.forecast.dataController;

import android.util.Log;

import java.util.Calendar;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.DaoSession;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.ForecastResponce;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.Weather;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.WeatherDao;

import static yaroslavromanyuta.com.ua.weatherforecast.Constants.TAG;

/**
 * Created by Yaroslav on 25.05.2016.
 */
public class DataUpdater {
    DaoSession daoSession = null;
    ForecastResponce forecastResponce = null;

    public DataUpdater (){

    }

    public DataUpdater(DaoSession daoSession, ForecastResponce forecastResponce){
        this.daoSession = daoSession;
        this.forecastResponce = forecastResponce;
    }

    public DataUpdater(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public void updateData(ForecastResponce forecastResponce){
        if (daoSession != null) {

            WeatherDao weatherDao = daoSession.getWeatherDao();
            QueryBuilder queryBuilder = weatherDao.queryBuilder();
            queryBuilder.where(WeatherDao.Properties.Date.lt(Calendar.getInstance().getTime()));
            List<Weather> list = queryBuilder.list();
            weatherDao.deleteInTx(list);

            daoSession.getCityDao().insertOrReplace(forecastResponce.getCity());
            daoSession.getWeatherDao().insertOrReplaceInTx(forecastResponce.getWeather());


        }
        Log.d(TAG, "updateData() called with: " + "forecastResponce = [" + forecastResponce + "]");

    }

    public void updateData(){
        if (daoSession!=null && forecastResponce!=null){

            WeatherDao weatherDao = daoSession.getWeatherDao();
            QueryBuilder queryBuilder = weatherDao.queryBuilder();
            queryBuilder.where(WeatherDao.Properties.Date.lt(Calendar.getInstance().getTime()));
            List<Weather> list = queryBuilder.list();
            weatherDao.deleteInTx(list);

            daoSession.getCityDao().insertOrReplace(forecastResponce.getCity());
            daoSession.getWeatherDao().insertOrReplaceInTx(forecastResponce.getWeather());


        }

        Log.d(TAG, "updateData() called with: " + "");

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public ForecastResponce getForecastResponce() {
        return forecastResponce;
    }

    public void setForecastResponce(ForecastResponce forecastResponce) {
        this.forecastResponce = forecastResponce;
    }
}
