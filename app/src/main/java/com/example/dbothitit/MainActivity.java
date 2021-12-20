package com.example.dbothitit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    static final String API_KEY = "dc3c3c2f214fc8ad7c686d2176dd46df";
    String city = "";
    ImageView img1;
    Button btnOk, btnCacNgayTiep;
    EditText edtTenTP;
    TextView txtTenTP, txtTenQG, txtNhietDo, txtThoiTiet, txtimg2, txtimg3,txtimg4,txtNgay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mapping();
        btnOk.setOnClickListener(this);
        btnCacNgayTiep.setOnClickListener(this);
        if (city == ""){
            getJsonWeather("hanoi");
        }else getJsonWeather(city);
    }
    public void getJsonWeather(final String city){
        final String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+API_KEY+"&units=metric";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject weatherObj = weatherArray.getJSONObject(0);
                            String icon = weatherObj.getString("icon");//icon thời tiết
                            String urlIcon = "https://openweathermap.org/img/wn/"+icon+".png";
                            Picasso.get().load(urlIcon).into(img1);
                            String temperState = weatherObj.getString("main");//trạng thái
                            txtThoiTiet.setText(temperState);
                            JSONObject main = response.getJSONObject("main");
                            String temp = main.getString("temp");//nhiệt độ
                            txtNhietDo.setText(temp+"ºC");
                            String humidity = main.getString("humidity");//độ ẩm
                            txtimg3.setText(humidity+"%");
                            JSONObject wind = response.getJSONObject("wind");
                            String speed = wind.getString("speed");//tóc độ gió
                            txtimg4.setText(speed+"m/s");
                            JSONObject clouds = response.getJSONObject("clouds");
                            String all = clouds.getString("all");//phần trăm mây
                            txtimg2.setText(all+"%");
                            String sNgay = response.getString("dt");
                            long lNgay = Long.parseLong(sNgay);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(lNgay*1000);
                            String currentTime = dateFormat.format(date);//ngày giờ hiện tại
                            txtNgay.setText(currentTime);
                            String name = response.getString("name");// thành phố
                            txtTenTP.setText("Thành phố "+name);
                            JSONObject sys = response.getJSONObject("sys");
                            String country = sys.getString("country");//quốc gia
                            txtTenQG.setText("Quốc gia "+country);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("MyError1:",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Không có dữ liệu của thành phố "+city, Toast.LENGTH_LONG).show();
                        Log.d("MyError:",error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
    private void mapping() {
        img1 = findViewById(R.id.img1);
        btnOk = findViewById(R.id.btnOk);
        btnCacNgayTiep = findViewById(R.id.btnCacNgayTiep);
        edtTenTP = findViewById(R.id.edtTenTP);
        txtimg2 = findViewById(R.id.txtImg2);
        txtimg3 = findViewById(R.id.txtImg3);
        txtimg4 = findViewById(R.id.txtImg4);
        txtTenTP = findViewById(R.id.txtTenTp);
        txtTenQG = findViewById(R.id.txtTenQG);
        txtThoiTiet = findViewById(R.id.txtThoiTiet);
        txtNgay = findViewById(R.id.txtNgay);
        txtNhietDo = findViewById(R.id.txtNhietDo);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnOk:
                city = edtTenTP.getText().toString().trim();
                if (city.equals(""))
                    city = "hanoi";
                getJsonWeather(city);
                break;
            case R.id.btnCacNgayTiep:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("city", city);
                startActivity(intent);
                break;
        }
    }
}
