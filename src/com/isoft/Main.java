package com.isoft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import static com.isoft.DrawGraph.drawGraph;
import static com.isoft.DrawGraph.setGraphData;

public class Main {


    public static void main(String[] args) {
        /** //double[] graphData = {46377.36, 45896.56,46985.36,46458.23,46836.23,45865.21,45398.69};
        double[] graphData = {59, 70, 60, 90, 200, 20, 60, 80, 70, 40, 70, 30, 20, 60, 80, 40, 70, 60, 410.63, 20, 390, 200, 40, 40, 40, 50, 80, 90, 60, 70, 50, 30};
        drawGraph(graphData,true);
        //Arrays.sort(graphData);
        //drawGraph(graphData, true);
    }**/

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String val = getHTML("https://api.thingspeak.com/apps/thinghttp/send_request?api_key=FGQQYSTA53D0PCK8");
                    ValueConverter v = new ValueConverter();
                    double k = v.toDouble(val, true);
                    System.out.println(k);
                    double[] gd = setGraphData(k);
                    if(DrawGraph.c == 25){
                        DrawGraph.drawGraph(gd, true);
                    }

                    //System.out.println(k);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 25000);

    }
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    }



