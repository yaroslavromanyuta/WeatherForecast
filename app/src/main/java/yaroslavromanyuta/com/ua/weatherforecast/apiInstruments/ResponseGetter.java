package yaroslavromanyuta.com.ua.weatherforecast.apiInstruments;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import yaroslavromanyuta.com.ua.weatherforecast.R;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.ForecastResponce;

import static yaroslavromanyuta.com.ua.weatherforecast.Constants.*;

/**
 * Created by Yaroslav on 25.05.2016.
 */
 class ResponseGetter {

    OpenWeatherServise openWeatherServise;
    Context context;

    ResponseGetter(Context context){
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.base_url))
                .build();

        openWeatherServise = retrofit.create(OpenWeatherServise.class);
    }


    public ForecastResponce getResponse (double latitude, double longitude) throws IOException {
        Call<ForecastResponce> call = openWeatherServise.getForecastByLocation(context.getString(R.string.api_key),
                                                                                        latitude, longitude);

        Response<ForecastResponce> response = null;
        response = call.execute();

        Log.d(TAG, "getResponse: " + "response created. HTTP status code= " + response.code() + "; message= " + response.message() +
                "; success = " + response.isSuccessful() );

        ForecastResponce foorecastResponse = response.body();

        return foorecastResponse;
    }
}
