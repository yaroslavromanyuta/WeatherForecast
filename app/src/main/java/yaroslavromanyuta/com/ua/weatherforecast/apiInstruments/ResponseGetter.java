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

import static yaroslavromanyuta.com.ua.weatherforecast.Constants.TAG;

/**
 * Created by Yaroslav on 25.05.2016.
 */
 class ResponseGetter {

    Context context;

    ResponseGetter(Context context){
        this.context = context;


    }


    public ForecastResponce getForecastResponse(double latitude, double longitude) throws IOException {

        OpenWeatherServise openWeatherServise;

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ForecastResponce.class, new ForecastResponseDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        openWeatherServise = retrofit.create(OpenWeatherServise.class);

        Call<ForecastResponce> call = openWeatherServise.getForecastByLocation(context.getString(R.string.api_key),
                                                                                        latitude, longitude);

        Response<ForecastResponce> response = null;
        response = call.execute();

        Log.d(TAG, "getForecastResponse: " + "response created. HTTP status code= " + response.code() + "; message= " + response.message() +
                "; success = " + response.isSuccessful() );
        Log.d(TAG, "getForecastResponse() returned: " + response);

        return response.body();
    }

}
