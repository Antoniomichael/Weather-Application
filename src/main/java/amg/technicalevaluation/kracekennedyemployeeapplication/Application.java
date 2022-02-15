package amg.technicalevaluation.kracekennedyemployeeapplication;

import amg.technicalevaluation.kracekennedyemployeeapplication.DAO.DBEmailAccessor;
import amg.technicalevaluation.kracekennedyemployeeapplication.model.Email;
import amg.technicalevaluation.kracekennedyemployeeapplication.model.WeatherAPI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 580);
//        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, ParseException, SQLException {


        launch();


    }
}