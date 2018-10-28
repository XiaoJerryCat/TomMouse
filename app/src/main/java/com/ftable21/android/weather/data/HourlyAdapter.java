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
import com.ftable21.android.weather.basic.BaseRecyclerViewAdapter;;

public class HourlyAdapter extends BaseRecyclerViewAdapter<HourlyAdapter.ViewHolder> {

    private List<WeatherHourly> mWeatherHourlyList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hourly_time)
        TextView time;
        @BindView(R.id.hourly_icon)
        ImageView icon;
        @BindView(R.id.hourly_type)
        TextView type;
        @BindView(R.id.hourly_tmp)
        TextView tmp;
        ViewHolder(View itemView, HourlyAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(view -> adapter.onItemHolderClick(ViewHolder.this));
        }
    }

    public HourlyAdapter(List<WeatherHourly> weatherHourlyList) { mWeatherHourlyList = weatherHourlyList; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public int getItemCount() { return mWeatherHourlyList == null ? 0 : mWeatherHourlyList.size(); }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherHourly weatherHourly = mWeatherHourlyList.get(position);
        holder.time.setText(weatherHourly.getHourly());
        holder.icon.setImageResource(weatherHourly.getIcon());
        holder.type.setText(weatherHourly.getType());
        holder.tmp.setText(weatherHourly.getTmp() + "°");
    }
}
