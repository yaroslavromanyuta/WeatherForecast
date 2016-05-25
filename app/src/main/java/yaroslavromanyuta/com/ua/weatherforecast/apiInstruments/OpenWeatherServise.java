package yaroslavromanyuta.com.ua.weatherforecast.apiInstruments;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.ForecastResponce;

/**
 * Created by Yaroslav on 25.05.2016.
 */
public interface OpenWeatherServise {
    @GET("forecast")
    Call<ForecastResponce> getForecastByLocation(@Query("appid") String apiKey,
                                                 @Query("lon") double longtitude,
                                                 @Query("lat") double latitude);
}
