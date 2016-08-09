package yaroslavromanyuta.com.ua.weatherforecast.apiInstruments;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import yaroslavromanyuta.com.ua.weatherforecast.forecast.dataController.DataUpdater;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.DaoSession;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.ForecastResponce;

import static yaroslavromanyuta.com.ua.weatherforecast.Constants.*;

/**
 * Created by Yaroslav on 25.05.2016.
 */
public class RequestController extends AsyncTask<Location, Void, ForecastResponce> {

    Context context;
    DaoSession daoSession;
    DataUpdater dataUpdater;
    RequestCallBack requestCallBack;
    ResponseGetter responseGetter;
    //Location location;

    public RequestController(Context context, DaoSession daoSession, RequestCallBack requestCallBack) {
        this.context = context;
        this.daoSession = daoSession;
        this.requestCallBack = requestCallBack;
        //this.location = location;
    }


    @Override
    protected ForecastResponce doInBackground(Location... params) {
        responseGetter = new ResponseGetter(context);
        Location location = params[0];
        ForecastResponce forecastResponce = new ForecastResponce();
        try {
            forecastResponce = responseGetter.getForecastResponse(location.getLatitude(), location.getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "doInBackground() called with: " + "params = [" + params + "]");
        Log.d(TAG, "doInBackground() returned: " + forecastResponce);
        return forecastResponce;


    }

    @Override
    protected void onPostExecute(ForecastResponce forecastResponce) {
        super.onPostExecute(forecastResponce);

        dataUpdater = new DataUpdater(daoSession, forecastResponce);
        dataUpdater.updateData();
        requestCallBack.RequestFinished(forecastResponce.getCity());

        Log.d(TAG, "onPostExecute() called with: " + "forecastResponce = [" + forecastResponce + "]");


    }
}
