package com.example.dbothitit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Weather> list;

    public WeatherAdapter(Context context, int layout, List<Weather> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder{
        TextView txtNgay3h;
        ImageView imgThoiTiet3h;
        TextView txtNhietDoMax;
        TextView txtNhietDoMin;
        TextView txtThoiTiet3h;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            viewHolder.txtNgay3h = view.findViewById(R.id.txtNgay3h);
            viewHolder.txtThoiTiet3h = view.findViewById(R.id.txtThoiTiet3h);
            viewHolder.imgThoiTiet3h = view.findViewById(R.id.imgThoiTiet3h);
            viewHolder.txtNhietDoMax = view.findViewById(R.id.txtNhietDoMax);
            viewHolder.txtNhietDoMin = view.findViewById(R.id.txtNhietDoMin);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Weather weather = list.get(i);
        viewHolder.txtNgay3h.setText(weather.getNgay3h());
        viewHolder.txtThoiTiet3h.setText(weather.getImgThoiTiet3h());
        Picasso.get().load(weather.getThoiTiet3h()).into(viewHolder.imgThoiTiet3h);
        viewHolder.txtNhietDoMin.setText(weather.getNhietDOMin()+"ºC");
        viewHolder.txtNhietDoMax.setText(weather.getNhietDoMax()+"ºC");
        return view;
    }
}