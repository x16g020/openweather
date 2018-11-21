package com.example.x16g032.openweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements XmlReader.OnStarListener, View.OnClickListener {

    final Double KELVIN = 273.15;

    int     num;
    int     cnt;
    String  dateT;
//    Date dateValue;

    Double  temperature;
    Double  h_temperature;
    Double  l_temperature;

    ImageView weatherImage;
    TextView dateText;
    TextView temp;
    TextView max_min;

    Button day_before;
    Button hour_before;
    Button hour_after;
    Button day_after;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherImage = findViewById(R.id.WeatherImage);
        weatherImage.setImageResource(R.drawable.sunny);

        dateText = findViewById(R.id.dateT);
        temp = findViewById(R.id.temperature);
        max_min = findViewById(R.id.max_min);

        cnt = 0;

        //URLをもとに天気情報を取得
        XmlReader.getWeather("http://api.openweathermap.org/data/2.5/forecast?id=1850147&APPID=d21a1076e3577e18ffe577b79bef2496&mode=xml",this);

        day_before = (Button)findViewById(R.id.before_day);
        hour_before = (Button)findViewById(R.id.before_hour);
        hour_after = (Button)findViewById(R.id.after_hour);
        day_after = (Button)findViewById(R.id.after_day);

        day_before.setOnClickListener(this);
        hour_before.setOnClickListener(this);
        hour_after.setOnClickListener(this);
        day_after.setOnClickListener(this);

        day_before.setEnabled(false);
        hour_before.setEnabled(false);
        hour_after.setEnabled(false);
        day_after.setEnabled(false);
    }

    @Override
    public void onStar(List<Map> stars) {
        //イベントの設定

        if(stars!=null) {
//            textView.setText("結果\n");
            Map map = stars.get(cnt);
            num = Integer.parseInt(map.get("symbol_number").toString());

//            int num = Integer.parseInt(map.get("symbol_number"));
            if (num >= 800) {
                weatherImage.setImageResource(R.drawable.sunny);
            }else if (num >= 600 && num < 800){
                weatherImage.setImageResource(R.drawable.rainny);
            }else{
            }

            dateT = map.get("")

//            String s = "テストもじれつ";
//            s = s.substring(0,4);
//            max_min.setText(s);

//            temperature = Double.parseDouble(map.get("temperature_value").toString()) - KELVIN;
//            h_temperature = Double.parseDouble(map.get("temperature_max").toString()) - KELVIN;
//            l_temperature = Double.parseDouble(map.get("temperature_min").toString()) - KELVIN;

            temperature = Double.parseDouble(map.get("temperature_value").toString());
//            h_temperature = Double.parseDouble(map.get("temperature_max").toString());
//            l_temperature = Double.parseDouble(map.get("temperature_min").toString());

            temp.setText(String.valueOf(temperature));
//            max_min.setText(String.valueOf(h_temperature) + " / " + String.valueOf(l_temperature));
        }





//            for (Map map : stars) {
//                if (map.get("symbol_number") != null) {
//                    textView.append(String.format("%s %s %s\n", map.get("symbol_number"), "---------"+map.get("symbol_name")+"---------", map.get("symbol_var")));
//                }else if (map.get("precipitation_unit") !=null){
//                    textView.append(String.format("%s %s %s\n", map.get("precipitation_unit"), map.get("precipitation_value"), map.get("precipitation_type")));
//                }else if(map.get("windDirection_deg") !=null){
//                    textView.append(String.format("%s %s %s\n", map.get("windDirection_deg"),map.get("windDirection_code"),map.get("windDirection_name")));
//                }else if(map.get("windSpeed_mps") !=null) {
//                    textView.append(String.format("%s %s\n",map.get("windSpeed_mps"),map.get("windSpeed_name")));
//                }else if(map.get("temperature_unit") !=null){
//                    textView.append(String.format("%s %s %s %s\n",map.get("temperature_unit"),map.get("temperature_value"),map.get("temperature_min"),map.get("temperature_max")));
//                }else if(map.get("unit") !=null){
//                    textView.append(String.format("%s %s\n",map.get("unit"),map.get("value")));
//                }else if(map.get("value") !=null){
//                    textView.append(String.format("%s %s\n",map.get("value"),map.get("unit")));
//                }else if(map.get("value") !=null){
//                    textView.append(String.format("%s %s %s\n",map.get("value"),map.get("all"),map.get("unit")));
//                }
//            }
//        }else{
//            textView.setText("エラー\n");
//        }
    }


    @Override
    public void onClick(View v) {
//        if(v.getId() == R.id.before_day){
//            if(cnt < 8){
//                cnt = 0;
//            }else {
//                cnt = cnt - 8;
//            }
//        }else if(v.getId() == R.id.before_hour){
//            cnt = cnt - 1;
//        }else if(v.getId() == R.id.after_hour){
//            cnt = cnt + 1;
//        }else if(v.getId() == R.id.after_day){
//
//        }
    }
}