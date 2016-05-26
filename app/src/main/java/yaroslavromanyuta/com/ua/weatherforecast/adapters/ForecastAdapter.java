package yaroslavromanyuta.com.ua.weatherforecast.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.Weather;

/**
 * Created by Yaroslav on 26.05.2016.
 */
public class ForecastAdapter extends BaseAdapter {
    Context context;
    List<Weather> weatherList;

    public ForecastAdapter(Context context, List<Weather> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
    }

    @Override
    public int getCount() {
        return weatherList.size();
    }

    @Override
    public Weather getItem(int position) {
        return weatherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
