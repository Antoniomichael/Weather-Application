package amg.technicalevaluation.kracekennedyemployeeapplication;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class WeatherAPI {
    final URL Kingstonurl = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=17.997&lon=-76.7936&exclude=current,minutely,hourly&appid=add89d8722d269d9d4d96f1ce7a66257");
    final URL MontegoBayurl = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=18.4712&lon=-77.9188&exclude=current,minutely,hourly&appid=add89d8722d269d9d4d96f1ce7a66257");

    public WeatherAPI() throws MalformedURLException {

    }


    public void getMontegoBayData(URL url) throws IOException, ParseException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        //this call to set request lets the connection know that this is a get method and so will receive data and not post
        connection.setRequestMethod("GET");
        connection.connect();

        //Checking connection response code: 429 means free subscription requests has been exceeded
        int responseCode = connection.getResponseCode();
        checkResponsecode(responseCode);
        setWeatherInfo(url);
//        getJSONObject();

    }


    public int checkResponsecode(int responsecode){
        if (responsecode==200)
            return 0;

        System.out.println("Connection failed");
        return 1;
    }

    public void setWeatherInfo(URL url) throws IOException, ParseException {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());

        while(scanner.hasNext())
        {
            stringBuilder.append(scanner.nextLine());

        }
        scanner.close();
        System.out.println(stringBuilder);
        System.out.println("Finished here");
        getJSONObject(stringBuilder);
    }

    public void getJSONObject(StringBuilder weatherAsString) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonWeatherArray = (JSONObject) jsonParser.parse(String.valueOf(weatherAsString));

        int results = jsonWeatherArray.size();
        int tempIterator = 0;
//        while(tempIterator < results){
////            System.out.println(jsonWeatherArray.get(tempIterator));
////            System.out.println(jsonWeatherArray.get(tempIterator));
//
//            tempIterator= tempIterator +1;
//        }

//        JSONObject daysinfo = (JSONObject) jsonWeatherArray.get(0);
        try {
            if (jsonWeatherArray.get("daily") != null) {
                List<JSONObject> daysInformationList = new ArrayList<>();

                JSONArray jsonArrayOfDays = (JSONArray) jsonWeatherArray.get("daily");
                JSONArray weatherInfo;

                List<DaysModel> daysInfo = new ArrayList<>();
                JSONObject weatherforthisday = new JSONObject();
                while(tempIterator < results){
                    //Gets each day during loop
                    daysInformationList.add((JSONObject) jsonArrayOfDays.get(tempIterator));
                    try{
                    //Takes the weather from that day

                    weatherInfo = (JSONArray) daysInformationList.get(tempIterator).get("weather");

                    weatherforthisday = (JSONObject) weatherInfo.get(0);
                    }catch (IndexOutOfBoundsException i){
                        System.out.println("index out of bounds");
                    }
                    if(weatherforthisday.get("main") == "Rain"){
                        System.out.println(weatherforthisday.get("main"));
                        daysInfo.add(new DaysModel(tempIterator,false));
                    }else{

                        System.out.println(weatherforthisday.get("main"));
                        daysInfo.add(new DaysModel(tempIterator,true));
                    }


                    tempIterator = tempIterator+1;
                }

                System.out.println(daysInfo);
//                System.out.println(daysInformationList);



//
//                JSONObject jsonObjecttest=  (JSONObject) jsonArrayOfDays.get(0);
//                JSONArray weatherinfo =  (JSONArray) jsonObjecttest.get("weather");
//                System.out.println(weatherinfo.get(0));


            } else {
                System.out.println("None Existent");
            }
        }catch(NullPointerException npe){
            System.out.println("Field null");
        }



    }


}
