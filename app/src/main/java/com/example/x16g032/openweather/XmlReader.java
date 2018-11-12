package com.example.x16g032.openweather;

import android.os.Handler;
import android.util.Log;

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

    public static void getStar(final String url, final OnStarListener listener){
        final Handler handler = new Handler();
        new Thread(){
            @Override
            public void run() {
                final List<Map> list = new ArrayList();
                try {
                    //XMLデータを読み出す
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(url);
                    //最上位エレメントの確認
                    Element element = doc.getDocumentElement();
                    Log.w("dbg1--------------",element.getTagName());

                    if(!"weatherdata".equals(element.getTagName()))
                        return;

                    //子ノードを探索
                    NodeList nodeList = element.getChildNodes();
                    Log.w("dbg1--------------",String.valueOf(nodeList.item(0).getNodeName()));
                    Log.w("dbg1a--------------",String.valueOf(nodeList.item(0).getTextContent()));
                    for(int i = 0; i < nodeList.getLength(); i++) {
                        Node node = nodeList.item(i);

                        Log.w("dbg1-" + i +"---------------",String.valueOf(nodeList.item(i).getNodeName()));


                        //Starノードを見つけたらパラメータを格納
                        if("forecast".equals(node.getNodeName())){
                            Map map = new HashMap();
                            NodeList nodeList2 = node.getChildNodes();
                            //Log.w("dbg2---------------",String.valueOf(nodeList2.item(0).getNodeName()));


                            for(int j = 0; j < nodeList2.getLength(); j++) {
                                Node node2 = nodeList2.item(j);
                                Log.w("dbg2a-" + j +"---------------",String.valueOf(node2.getNodeName()));
                                //Log.w("dbg2b-" + j +"---------------",String.valueOf(node2.getNodeValue()));

                                NamedNodeMap attrs = node2.getAttributes();
                                Node attr1 = attrs.getNamedItem("from");
                                Log.w("dbg2z-" + j +"---------------",String.valueOf(attrs.getLength()));
                                Log.w("dbg2a1-" + j +"---------------",String.valueOf(attr1.getNodeName()));
                                Log.w("dbg2b1-" + j +"---------------",String.valueOf(attr1.getNodeValue()));

                                attr1 = attrs.getNamedItem("to");
                                Log.w("dbg2z-"  + j +"---------------",String.valueOf(attrs.getLength()));
                                Log.w("dbg2a1-" + j +"---------------",String.valueOf(attr1.getNodeName()));
                                Log.w("dbg2b1-" + j +"---------------",String.valueOf(attr1.getNodeValue()));
                                /*-------------------*/



                                /*-------------------*/
                                NodeList nodeList3 = node2.getChildNodes();
                                for(int k = 0; k < nodeList3.getLength(); k++) {
                                        Node node3 = nodeList3.item(k);
                                        Log.w("dbg3a-" + k +"---------------",String.valueOf(node3.getNodeName()));





                                    if (String.valueOf(node3.getNodeName()).equals("symbol")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("number");
                                        Node attr3 = attrs2.getNamedItem("name");
                                        Node attr4 = attrs2.getNamedItem("var");
                                        if (attr2 != null) {
                                            Log.w("dbg3z-" + k + "---------------", String.valueOf(attrs2.getLength()));
                                            //Log.w("dbg3a1-" + k + "---------------", String.valueOf(attr2.getNodeName()));
                                            Log.w("dbg3b1-" + k + "---------------", String.valueOf(attr2.getNodeValue()));
                                            Log.w("dbg3b2-" + k + "---------------", String.valueOf(attr3.getNodeValue()));
                                            Log.w("dbg3b3-" + k + "---------------", String.valueOf(attr4.getNodeValue()));

                                            map = new HashMap();
                                            map.put("number", String.valueOf(attr2.getNodeValue()));
                                            map.put("name", String.valueOf(attr3.getNodeValue()));
                                            map.put("var", String.valueOf(attr4.getNodeValue()));
                                            list.add(map);
                                        }
                                    }

                                    if (String.valueOf(node3.getNodeName()).equals("precipitation")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("unit");
                                        Node attr3 = attrs2.getNamedItem("value");
                                        Node attr4 = attrs2.getNamedItem("type");
                                        if (attr2 != null) {
                                            Log.w("dbg3z-" + k + "---------------", String.valueOf(attrs2.getLength()));
                                            //Log.w("dbg3a1-" + k + "---------------", String.valueOf(attr2.getNodeName()));
                                            Log.w("dbg3b4-" + k + "---------------", String.valueOf(attr2.getNodeValue()));
                                            Log.w("dbg3b5-" + k + "---------------", String.valueOf(attr3.getNodeValue()));
                                            Log.w("dbg3b6-" + k + "---------------", String.valueOf(attr4.getNodeValue()));

                                            map = new HashMap();
                                            map.put("unit", String.valueOf(attr2.getNodeValue()));
                                            map.put("value", String.valueOf(attr3.getNodeValue()));
                                            map.put("type", String.valueOf(attr4.getNodeValue()));
                                            list.add(map);
                                        }
                                    }

                                    if (String.valueOf(node3.getNodeName()).equals("windDirection")) {
                                            NamedNodeMap attrs2 = node3.getAttributes();
                                            Node attr2 = attrs2.getNamedItem("deg");
                                            Node attr3 = attrs2.getNamedItem("code");
                                            Node attr4 = attrs2.getNamedItem("name");
                                            if (attr2 != null) {
                                                Log.w("dbg3z-" + k + "---------------", String.valueOf(attrs2.getLength()));
                                                //Log.w("dbg3a1-" + k + "---------------", String.valueOf(attr2.getNodeName()));
                                                Log.w("dbg3b4-" + k + "---------------", String.valueOf(attr2.getNodeValue()));
                                                Log.w("dbg3b5-" + k + "---------------", String.valueOf(attr3.getNodeValue()));
                                                Log.w("dbg3b6-" + k + "---------------", String.valueOf(attr4.getNodeValue()));

                                                map = new HashMap();
                                                map.put("deg", String.valueOf(attr2.getNodeValue()));
                                                map.put("code", String.valueOf(attr3.getNodeValue()));
                                                map.put("name", String.valueOf(attr4.getNodeValue()));
                                                list.add(map);
                                            }
                                    }
                                    if (String.valueOf(node3.getNodeName()).equals("windSpeed")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("mps");
                                        Node attr3 = attrs2.getNamedItem("name");
                                        if (attr2 != null) {
                                            map = new HashMap();
                                            map.put("mps", String.valueOf(attr2.getNodeValue()));
                                            map.put("name", String.valueOf(attr3.getNodeValue()));
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

                                            Log.w("dbg3z-" + k + "---------------", String.valueOf(attrs2.getLength()));
                                            Log.w("dbg3a1-" + k + "---------------", String.valueOf(attr2.getNodeName()));
                                            Log.w("dbg3b4-" + k + "---------------", String.valueOf(attr3.getNodeValue()));
                                            Log.w("dbg3b5-" + k + "---------------", String.valueOf(attr4.getNodeValue()));
                                            Log.w("dbg3b6-" + k + "---------------", String.valueOf(attr5.getNodeValue()));

                                            map = new HashMap();
                                            map.put("unit", String.valueOf(attr2.getNodeValue()));
                                            map.put("value", String.valueOf(attr3.getNodeValue()));
                                            map.put("min", String.valueOf(attr4.getNodeValue()));
                                            map.put("max", String.valueOf(attr5.getNodeValue()));
                                            list.add(map);
                                        }
                                    }


                                    if (String.valueOf(node3.getNodeName()).equals("pressure")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("unit");
                                        Node attr3 = attrs2.getNamedItem("value");

                                        if (attr2 != null) {

                                            map = new HashMap();
                                            map.put("unit", String.valueOf(attr2.getNodeValue()));
                                            map.put("value", String.valueOf(attr3.getNodeValue()));
                                            list.add(map);
                                        }
                                    }


                                    if (String.valueOf(node3.getNodeName()).equals("humidity")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("value");
                                        Node attr3 = attrs2.getNamedItem("unit");

                                        if (attr2 != null) {

                                            map = new HashMap();
                                            map.put("value", String.valueOf(attr2.getNodeValue()));
                                            map.put("unit", String.valueOf(attr3.getNodeValue()));
                                            list.add(map);
                                        }
                                    }


                                    if (String.valueOf(node3.getNodeName()).equals("clouds")) {
                                        NamedNodeMap attrs2 = node3.getAttributes();
                                        Node attr2 = attrs2.getNamedItem("value");
                                        Node attr3 = attrs2.getNamedItem("all");
                                        Node attr4 = attrs2.getNamedItem("unit");

                                        if (attr2 != null) {

                                            map = new HashMap();
                                            map.put("value", String.valueOf(attr2.getNodeValue()));
                                            map.put("all", String.valueOf(attr3.getNodeValue()));
                                            map.put("unit", String.valueOf(attr4.getNodeValue()));
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