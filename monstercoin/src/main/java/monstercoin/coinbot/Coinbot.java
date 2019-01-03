package monstercoin.coinbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class Coinbot
{
    public static void request() throws IOException {
        URL url = new URL("https://api.coinpaprika.com/v1/tickers/btc-bitcoin");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();// maybe StringBuilder
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();
        System.out.println(content);

        JSONObject jsonObject = new JSONObject(content.toString());

        System.out.println("id: " + jsonObject.getString("id"));
        System.out.println("name: " + jsonObject.getString("name"));
        System.out.println("symbol: " + jsonObject.getString("symbol"));
        System.out.println("last_updated: " + jsonObject.getString("last_updated"));

        JSONObject jsonObject2 = jsonObject.getJSONObject("quotes");
        System.out.println(jsonObject2);
        System.out.println("price: " + jsonObject2.getString("price"));


    }
}
