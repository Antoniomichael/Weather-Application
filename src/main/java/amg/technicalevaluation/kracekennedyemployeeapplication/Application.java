package amg.technicalevaluation.kracekennedyemployeeapplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, ParseException {
        final URL Kingstonurl = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=17.997&lon=-76.7936&exclude=current,minutely,hourly&appid=add89d8722d269d9d4d96f1ce7a66257");
        final URL MontegoBayurl = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=18.4712&lon=-77.9188&exclude=current,minutely,hourly&appid=add89d8722d269d9d4d96f1ce7a66257");

        WeatherAPI KingstonWeather = new WeatherAPI();
        WeatherAPI MontegoBayWeather = new WeatherAPI();

        KingstonWeather.setWeatherInfo(Kingstonurl);
        MontegoBayWeather.setWeatherInfo(MontegoBayurl);

//        launch();


    }
}