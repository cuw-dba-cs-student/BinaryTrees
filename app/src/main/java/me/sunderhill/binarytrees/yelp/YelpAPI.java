package me.sunderhill.binarytrees.yelp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class YelpAPI extends Thread
{
    public void run()
    {
        try
        {
            URL airportURL = new URL("https://api.yelp.com/v3/businesses/search?location=Milwaukee, WI&categories=restaurants");
            HttpURLConnection conn = (HttpURLConnection)airportURL.openConnection();
            conn.setRequestProperty("Authorization", "Bearer 1TPchpZB3kzAgrUXMjF9kJWdQng55XRQVtISLlXqTsC5lnNrwlv47UWGOgpRwwR9goe1-0MClzcUUA-M1m0uOnomTsfVcBNCgTZHawoAVghf3AgdMkMY0TUNTl7OXHYx");
            Scanner input = new Scanner(conn.getInputStream());
            String data = "";

            while(input.hasNextLine())
            {
                data = data + input.nextLine();
            }
            input.close();
            System.out.println("*** DATA: " + data);
            JSONObject obj = new JSONObject(data);
            JSONArray businesses = obj.getJSONArray("businesses");
            for(int i = 0; i < businesses.length(); i++)
            {
                String name = businesses.getJSONObject(i).getString("name");
                System.out.println("*** " + name);
            }
        }
        catch(Exception e)
        {
            System.out.println("*** " + e.toString());
        }
    }
}
