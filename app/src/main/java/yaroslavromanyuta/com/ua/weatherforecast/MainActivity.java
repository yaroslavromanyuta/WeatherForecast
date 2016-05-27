package yaroslavromanyuta.com.ua.weatherforecast;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import yaroslavromanyuta.com.ua.weatherforecast.adapters.ForecastAdapter;
import yaroslavromanyuta.com.ua.weatherforecast.apiInstruments.RequestCallBack;
import yaroslavromanyuta.com.ua.weatherforecast.apiInstruments.RequestController;
import yaroslavromanyuta.com.ua.weatherforecast.application.ApplicationWithDaoSession;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.City;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.DaoSession;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.Weather;

import static yaroslavromanyuta.com.ua.weatherforecast.Constants.TAG;

public class MainActivity extends AppCompatActivity implements RequestCallBack {

    Location location;
    DaoSession daoSession;

    ListView forecastList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        forecastList = (ListView) findViewById(R.id.forecast_list);

        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        daoSession = ((ApplicationWithDaoSession) getApplication()).getDaoSession();
        RequestController requestController = new RequestController(this, daoSession, this);
        requestController.execute(location);
    }

    @Override
    public void RequestFinished(City curentCity) {
        Log.d(TAG, "RequestFinished() called with: " + "");
        List<Weather> list = daoSession.getCityDao().load(curentCity.getId()).getForecast();
        ForecastAdapter forecastAdapter = new ForecastAdapter(this,list);

        forecastList.setAdapter(forecastAdapter);


    }
}
