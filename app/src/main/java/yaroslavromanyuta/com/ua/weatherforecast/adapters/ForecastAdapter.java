package yaroslavromanyuta.com.ua.weatherforecast.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yaroslavromanyuta.com.ua.weatherforecast.R;
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

    static class ViewHolder{
        protected TextView txtDate;
        protected ImageView icon;
        protected TextView temp;
        protected TextView description;
        protected TextView windSpeed;
        protected TextView clouds;
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
        ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.forecast_list_item, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon_forecast_list_item);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.txt_date_forecast_list_item);
            viewHolder.temp = (TextView) convertView.findViewById(R.id.txt_temp_forecast_list_item);
            viewHolder.windSpeed = (TextView) convertView.findViewById(R.id.txt_windspeed_forecast_list_item);
            viewHolder.clouds = (TextView) convertView.findViewById(R.id.txt_clouds_forecast_list_item);
            viewHolder.description = (TextView) convertView.findViewById(R.id.txt_describle_forecast_list_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Weather weather = weatherList.get(position);
        //viewHolder.icon.setweather.getIcon();
        viewHolder.txtDate.setText(weather.getDate().toGMTString());
        viewHolder.description.setText(weather.getDescription());
        viewHolder.windSpeed.setText(weather.getWindSpeed().toString());
        viewHolder.clouds.setText(weather.getClouds().toString());
        viewHolder.temp.setText(weather.getTemp().toString());

        return convertView;
    }

    public void swapItems(List<Weather> weatherList){
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }
}
