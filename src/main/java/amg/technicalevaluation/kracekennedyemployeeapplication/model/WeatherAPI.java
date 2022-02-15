package amg.technicalevaluation.kracekennedyemployeeapplication.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class WeatherAPI {
    List<DaysModel> daysInfo = new ArrayList<>();
    public List<DaysModel> checkURL(URL url) throws IOException, ParseException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        //this call to set request lets the connection know that this is a get method and so will receive data and not post
        connection.setRequestMethod("GET");
        connection.connect();

        //Checking connection response code: 429 means free subscription requests has been exceeded
        int responseCode = connection.getResponseCode();
        checkResponsecode(responseCode);

        return getJSONObject(setWeatherInfo(url));

    }


    public int checkResponsecode(int responsecode){
        if (responsecode==200)
            return 0;

        System.out.println("Connection failed");
        return 1;
    }

    public StringBuilder setWeatherInfo(URL url) throws IOException, ParseException {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());

        while(scanner.hasNext())
        {
            stringBuilder.append(scanner.nextLine());

        }
        scanner.close();
//        System.out.println(stringBuilder);
//        System.out.println("Finished here");
        getJSONObject(stringBuilder);
        return stringBuilder;
    }

    public List<DaysModel> getJSONObject(StringBuilder weatherAsString) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonWeatherArray = (JSONObject) jsonParser.parse(String.valueOf(weatherAsString));

        int results = jsonWeatherArray.size();
        int tempIterator = 0;

        try {
            if (jsonWeatherArray.get("daily") != null) {
                List<JSONObject> daysInformationList = new ArrayList<>();

                JSONArray jsonArrayOfDays = (JSONArray) jsonWeatherArray.get("daily");
                JSONArray weatherInfo;


                JSONObject weatherforthisday = new JSONObject();

                LocalDate localDate = LocalDate.now();

                while(tempIterator < results){
                    daysInformationList.add((JSONObject) jsonArrayOfDays.get(tempIterator));

                    try{

                    weatherInfo = (JSONArray) daysInformationList.get(tempIterator).get("weather");

                    weatherforthisday = (JSONObject) weatherInfo.get(0);
                    }catch (IndexOutOfBoundsException i){
//                        System.out.println("index out of bounds");
                    }
                    if(weatherforthisday.get("main") == "Rain"){
//                        System.out.println(weatherforthisday.get("main"));
                        daysInfo.add(new DaysModel(localDate.plusDays(tempIterator), (String) weatherforthisday.get("main")));
                    }else if(weatherforthisday.get("main") != "Rain"){
//                        System.out.println(weatherforthisday.get("main"));
                        daysInfo.add(new DaysModel(localDate.plusDays(tempIterator), (String) weatherforthisday.get("main")));
                    }


                    tempIterator = tempIterator+1;
                }

//                System.out.println(daysInfo);

            } else {
//                System.out.println("None Existent");
            }
            return daysInfo;
        }catch(NullPointerException npe){
//            System.out.println("Field null");
        }
        return  daysInfo;
    }
}
