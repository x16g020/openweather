package com.example.x16g032.openweather;

import android.os.Handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlReader {
    interface OnStarListener{
        void onStar(List<Map> stars);
    }

    public static void getWeather(final String url, final OnStarListener listener){
        final Handler handler = new Handler();
        new Thread(){
            @Override
            public void run() {

                //ArrayListの定義
                final List<Map> list = new ArrayList();

                try {
                    //XMLデータを読み出す
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(url);

                    //最上位エレメントの確認
                    Element element = doc.getDocumentElement();

                    if(!"weatherdata".equals(element.getTagName()))
                        return;

                    //子ノードを探索
                    //weatherdataの子ノード（location,meta,forecast）を取得
                    NodeList nodeList = element.getChildNodes();
                    for(int i = 0; i < nodeList.getLength(); i++) {
//                        Node node = nodeList.item(4);
                        Node node = nodeList.item(i);
                        //forecastノードを見つけたらパラメータを格納
                        if("forecast".equals(node.getNodeName())){
                            //HashMapのインスタンスを定義
                            Map map = new HashMap();
                            //forecastの子ノード（time）を格納
                            NodeList nodeList2 = node.getChildNodes();

                            //timeノードの数繰り返し
                            for(int j = 0; j < nodeList2.getLength(); j++) {
                                //j番目のtimeノードを格納
                                Node node2 = nodeList2.item(j);
                                if (String.valueOf(node2.getNodeName()).equals("time")){
                                    NamedNodeMap attrs2 = node2.getAttributes();
                                    Node attr2 = attrs2.getNamedItem("from");
                                    Node attr3 = attrs2.getNamedItem("to");
                                    if (attr2 != null) {
                                        map.put("time_from", String.valueOf(attr2.getNodeValue()));
                                        map.put("time_to", String.valueOf(attr3.getNodeValue()));
                                        list.add(map);
                                    }
                                }
                                //timeの子ノード（symbol,precipitation,windDirectio,windSpeed,tempererure,pressure,humidity,clouds）を格納
                                NodeList nodeList3 = node2.getChildNodes();
                                for(int k = 0; k < nodeList3.getLength(); k++) {
                                    Node node3 = nodeList3.item(k);
                                    if (String.valueOf(node3.getNodeName()).equals("symbol")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("number");
                                        Node attr3 = attrs2.getNamedItem("name");
                                        Node attr4 = attrs2.getNamedItem("var");
                                        if (attr2 != null) {
//                                            map = new HashMap();
                                            map.put("symbol_number", String.valueOf(attr2.getNodeValue()));
                                            map.put("symbol_name", String.valueOf(attr3.getNodeValue()));
                                            map.put("symbol_var", String.valueOf(attr4.getNodeValue()));
                                            list.add(map);
                                        }
                                    }

                                    if (String.valueOf(node3.getNodeName()).equals("precipitation")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("unit");
                                        Node attr3 = attrs2.getNamedItem("value");
                                        Node attr4 = attrs2.getNamedItem("type");
                                        if (attr2 != null) {
//                                            map = new HashMap();
                                            map.put("precipitation_unit", String.valueOf(attr2.getNodeValue()));
                                            map.put("precipitation_value", String.valueOf(attr3.getNodeValue()));
                                            map.put("precipitation_type", String.valueOf(attr4.getNodeValue()));
                                            list.add(map);
                                        }
                                    }

                                    if (String.valueOf(node3.getNodeName()).equals("windDirection")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("deg");
                                        Node attr3 = attrs2.getNamedItem("code");
                                        Node attr4 = attrs2.getNamedItem("name");
                                        if (attr2 != null) {
//                                            map = new HashMap();
                                            map.put("windDirection_deg", String.valueOf(attr2.getNodeValue()));
                                            map.put("windDirection_code", String.valueOf(attr3.getNodeValue()));
                                            map.put("windDirection_name", String.valueOf(attr4.getNodeValue()));
                                            list.add(map);
                                        }
                                    }

                                    if (String.valueOf(node3.getNodeName()).equals("windSpeed")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("mps");
                                        Node attr3 = attrs2.getNamedItem("name");
                                        if (attr2 != null) {
//                                            map = new HashMap();
                                            map.put("windSpeed_mps", String.valueOf(attr2.getNodeValue()));
                                            map.put("windSpeed_name", String.valueOf(attr3.getNodeValue()));
                                            list.add(map);
                                        }
                                    }

                                    if (String.valueOf(node3.getNodeName()).equals("temperature")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("unit");
                                        Node attr3 = attrs2.getNamedItem("value");
                                        Node attr4 = attrs2.getNamedItem("min");
                                        Node attr5 = attrs2.getNamedItem("max");
                                        if (attr2 != null) {
//                                            map = new HashMap();
                                            map.put("temperature_unit", String.valueOf(attr2.getNodeValue()));
                                            map.put("temperature_value", String.valueOf(attr3.getNodeValue()));
                                            map.put("temperature_min", String.valueOf(attr4.getNodeValue()));
                                            map.put("temperature_max", String.valueOf(attr5.getNodeValue()));
                                            list.add(map);
                                        }
                                    }

                                    if (String.valueOf(node3.getNodeName()).equals("pressure")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("unit");
                                        Node attr3 = attrs2.getNamedItem("value");
                                        if (attr2 != null) {
//                                            map = new HashMap();
                                            map.put("pressure_unit", String.valueOf(attr2.getNodeValue()));
                                            map.put("pressure_value", String.valueOf(attr3.getNodeValue()));
                                            list.add(map);
                                        }
                                    }


                                    if (String.valueOf(node3.getNodeName()).equals("humidity")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("value");
                                        Node attr3 = attrs2.getNamedItem("unit");
                                        if (attr2 != null) {
//                                            map = new HashMap();
                                            map.put("humidity_value", String.valueOf(attr2.getNodeValue()));
                                            map.put("humidity_unit", String.valueOf(attr3.getNodeValue()));
                                            list.add(map);
                                        }
                                    }

                                    if (String.valueOf(node3.getNodeName()).equals("clouds")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("value");
                                        Node attr3 = attrs2.getNamedItem("all");
                                        Node attr4 = attrs2.getNamedItem("unit");
                                        if (attr2 != null) {
//                                            map = new HashMap();
                                            map.put("clouds_value", String.valueOf(attr2.getNodeValue()));
                                            map.put("clouds_all", String.valueOf(attr3.getNodeValue()));
                                            map.put("clouds_unit", String.valueOf(attr4.getNodeValue()));
                                            list.add(map);
                                        }
                                    }
                                }
                                if(node2.getNodeType() == Node.ELEMENT_NODE){
                                    map.put(node2.getNodeName(),node2.getTextContent());
                                }
                            }
                            list.add(map);
                        }
                    }

                    //結果をメインスレッドのリスナーに通知
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onStar(list);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    //結果をメインスレッドのリスナーに通知
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onStar(null);
                        }
                    });
                }
            }
        }.start();
    }
}