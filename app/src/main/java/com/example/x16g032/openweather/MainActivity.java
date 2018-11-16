package com.example.x16g032.openweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements XmlReader.OnStarListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        textView.setText("データの読み込み中\n");
        //URLをもとに天気情報を取得
        XmlReader.getWeather("http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=d21a1076e3577e18ffe577b79bef2496&mode=xml",this);
    }

    @Override
    public void onStar(List<Map> stars) {

        ImageView weatherImage = findViewById(R.id.WeatherImage);
        TextView textView = findViewById(R.id.textView);
        if(stars!=null) {
//            textView.setText("結果\n");
            Map map = stars.get(0);

//            int num = Integer.parseInt(map.get("symbol_number"));
            if (map.get("symbol_number").equals(800)) {
                weatherImage.setImageResource(R.drawable.sunnyimage);
            }
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
}