package yaroslavromanyuta.com.ua.weatherforecast.apiInstruments;

import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.City;

/**
 * Created by Yaroslav on 25.05.2016.
 */
public interface RequestCallBack {
    void RequestFinished(City curentCity);
}
