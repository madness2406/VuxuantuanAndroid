package com.example.dbothitit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    List<Weather> weatherList;
    WeatherAdapter adapter;
    ListView lvThoiTiet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        lvThoiTiet = findViewById(R.id.lvThoiTiet);
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        weatherList = new ArrayList<>();
        adapter = new WeatherAdapter(Main2Activity.this, R.layout.activity_main3, weatherList);
        lvThoiTiet.setAdapter(adapter);
        getJsonNextDay(city);
    }

    private void getJsonNextDay(String city) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+city+"&units=metric&cnt=7&appid=acbae9c57a24663635f3918fd4e8f0c7";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray list = response.getJSONArray("list");
                            //Toast.makeText(NextDayActivity.this, "list size ="+list.length(), Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < list.length(); i++) {

                                JSONObject item = list.getJSONObject(i);
                                String sNgay = item.getString("dt");//xử chuyển thành ngày
                                long lNgay = Long.parseLong(sNgay);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(lNgay*1000);
                                String ngay3h = dateFormat.format(date);//ngày giờ hiện tại
                                JSONObject main = item.getJSONObject("main");
                                JSONArray weather = item.getJSONArray("weather");
                                JSONObject weatherItem = weather.getJSONObject(0);
                                String thoiTiet3h = weatherItem.getString("description");
                                String icon = weatherItem.getString("icon");
                                String imgThoiTiet3h = "https://openweathermap.org/img/wn/"+icon+".png";//ảnh icon
                                String nhietDoMax = main.getString("temp_max");//nhiệt độ lớn nhất trong ngày
                                String nhietDOMin = main.getString("temp_min");//nhiệt độ nhỏ nhất trong ngày



                                weatherList.add(new Weather(ngay3h, thoiTiet3h, nhietDoMax ,nhietDOMin, imgThoiTiet3h));
                                //Toast.makeText(NextDayActivity.this, ""+weatherList.size(), Toast.LENGTH_SHORT).show();
                            }

                            Toast.makeText(Main2Activity.this, ""+weatherList.size(), Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            Toast.makeText(Main2Activity.this, "Có lỗi: "+e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                        Log.d("MyError1:",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this, "Không có dữ liệu cho thành phố ", Toast.LENGTH_LONG).show();
                        Log.d("MyError:",error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}