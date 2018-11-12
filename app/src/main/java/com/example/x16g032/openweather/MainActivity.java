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
        XmlReader.getStar("http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=d21a1076e3577e18ffe577b79bef2496&mode=xml",this);
    }

    @Override
    public void onStar(List<Map> stars) {
        TextView textView = findViewById(R.id.textView);
        if(stars!=null) {
            textView.setText("結果\n");
            for (Map map : stars) {

                if (map.get("number") != null) {
                    textView.append(String.format("%s %s %s\n", map.get("number"), "---------"+map.get("name")+"---------", map.get("var")));

                }else if (map.get("unit") !=null){
                    textView.append(String.format("%s %s %s\n", map.get("unit"), map.get("value"), map.get("type")));
                }else if(map.get("deg") !=null){
                    textView.append(String.format("%s %s %s\n", map.get("deg"),map.get("code"),map.get("name")));
                }else if(map.get("mps") !=null) {
                    textView.append(String.format("%s %s\n",map.get("mps"),map.get("name")));
                }else if(map.get("unit") !=null){
                    textView.append(String.format("%s %s %s %s\n",map.get("unit"),map.get("value"),map.get("min"),map.get("max")));
                }else if(map.get("unit") !=null){
                    textView.append(String.format("%s %s\n",map.get("unit"),map.get("value")));
                }else if(map.get("value") !=null){
                    textView.append(String.format("%s %s\n",map.get("value"),map.get("unit")));
                }else if(map.get("value") !=null){
                    textView.append(String.format("%s %s %s\n",map.get("value"),map.get("all"),map.get("unit")));
                }



            }
        }else{
            textView.setText("エラー\n");
        }
    }
}