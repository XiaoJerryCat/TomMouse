package com.ftable21.android.weather.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.ftable21.android.weather.R;
import com.ftable21.android.weather.basic.BaseRecyclerViewAdapter;

public class ForecastAdapter extends BaseRecyclerViewAdapter<ForecastAdapter.ViewHolder> {

    private List<WeatherForecast> mWeatherForecastList;

    public ForecastAdapter(List<WeatherForecast> weatherForecastList) { mWeatherForecastList = weatherForecastList; }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.week_text_view)
        TextView week;
        @BindView(R.id.date_text_view)
        TextView date;
        @BindView(R.id.weather_type_icon)
        ImageView icon;
        @BindView(R.id.weather_max_tmp)
        TextView max;
        @BindView(R.id.weather_min_tmp)
        TextView min;
        @BindView(R.id.weather_type_text)
        TextView type;

        ViewHolder(View itemView, ForecastAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(view -> adapter.onItemHolderClick(ViewHolder.this));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherForecast weatherForecast = mWeatherForecastList.get(position);
        holder.week.setText(weatherForecast.getWeek());
        holder.date.setText(weatherForecast.getDate());
        holder.icon.setImageResource(weatherForecast.getIcon());
        holder.type.setText(weatherForecast.getType());
        holder.max.setText(weatherForecast.getMaxTmp() + "°");
        holder.min.setText(weatherForecast.getMinTmp() + "°");
    }

    @Override
    public int getItemCount() { return mWeatherForecastList == null ? 0 : mWeatherForecastList.size(); }
}
