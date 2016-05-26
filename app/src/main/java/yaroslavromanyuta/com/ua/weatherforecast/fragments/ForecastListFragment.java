package yaroslavromanyuta.com.ua.weatherforecast.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yaroslavromanyuta.com.ua.weatherforecast.R;

/**
 * Created by Yaroslav on 26.05.2016.
 */
public class ForecastListFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forecast_list_fragment,null);
    }
}
