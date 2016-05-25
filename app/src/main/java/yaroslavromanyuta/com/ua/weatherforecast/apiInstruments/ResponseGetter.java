package yaroslavromanyuta.com.ua.weatherforecast.apiInstruments;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yaroslavromanyuta.com.ua.weatherforecast.R;
import yaroslavromanyuta.com.ua.weatherforecast.apiInstruments.deserializers.ForecastResponseDeserializer;
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

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ForecastResponce.class, new ForecastResponseDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
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
        Log.d(TAG, "getResponse() returned: " + response);

        return response.body();
    }
}
