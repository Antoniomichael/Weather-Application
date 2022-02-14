package amg.technicalevaluation.kracekennedyemployeeapplication;

import amg.technicalevaluation.kracekennedyemployeeapplication.DAO.DBEmailAccessor;
import amg.technicalevaluation.kracekennedyemployeeapplication.model.DaysModel;
import amg.technicalevaluation.kracekennedyemployeeapplication.model.Email;
import amg.technicalevaluation.kracekennedyemployeeapplication.model.WeatherAPI;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName.CLOUD;

public class mainController implements Initializable {
    public FontAwesomeIcon fontAwesome3;

    //Days Mobay side of UI
    public Label txtCurrentDayMoBay;
    public Label txtDay2Mobay;
    public Label txtDay3Mobay;
    public Label txtDay4Mobay;
    public Label txtDay5Mobay;

    //Days Kingston side of UI
    public Label txtcurrentDayKingston;
    public Label txtDay2Kingston;
    public Label txtDay3Kingston;
    public Label txtDay4Kingston;
    public Label txtDay5Kingston;

    //Mobay side description info
    public Label txtDescriptionCurrentDayMobay;
    public Label txtDescriptionDay2Mobay;
    public Label txtDescriptionDay3Mobay;
    public Label txtDescriptionDay4Mobay;
    public Label txtDescriptionDay5Mobay;

    //Kingston side description info
    public Label txtDescriptionCurrentDayKingston;
    public Label txtDescriptionDay2Kingston;
    public Label txtDescriptionDay3Kingston;
    public Label txtDescriptionDay4Kingston;
    public Label txtDescriptionDay5Kingston;

    //Fonts for Mobay
    public FontAwesomeIcon fontAwesomeICONCurrentDayMobay;
    public FontAwesomeIcon fontAwesomeICONDayMobay2;
    public FontAwesomeIcon fontAwesomeICONDayMobay3;
    public FontAwesomeIcon fontAwesomeICONDayMobay4;
    public FontAwesomeIcon fontAwesomeICONDayMobay5;

    //Fonts for Kingston
    public FontAwesomeIcon fontAwesomeICONCurrentDayKingston;
    public FontAwesomeIcon fontAwesomeICONDay2Kingston;
    public FontAwesomeIcon fontAwesomeICONDay3Kingston;
    public FontAwesomeIcon fontAwesomeICONDay4Kingston;
    public FontAwesomeIcon fontAwesomeICONDay5Kingston;



    final URL KingstonURL = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=17.997&lon=-76.7936&exclude=current,minutely,hourly&appid=add89d8722d269d9d4d96f1ce7a66257");
    final URL MontegoBayURL = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=18.4712&lon=-77.9188&exclude=current,minutely,hourly&appid=add89d8722d269d9d4d96f1ce7a66257");

    public mainController() throws MalformedURLException {
    }

    public void changeWeatherIcon(){
//        fo
    fontAwesome3.setIcon(CLOUD);
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<DaysModel> KingstonDaysInfo = null;
        List<DaysModel> MontegoBayDaysInfo = null;
        String ITEmailsList;
        String WorkersEmailsList;
        WeatherAPI KingstonWeather = new WeatherAPI();
        WeatherAPI MontegoBayWeather = new WeatherAPI();


        try {
            KingstonDaysInfo = KingstonWeather.checkURL(KingstonURL);
            MontegoBayDaysInfo = MontegoBayWeather.checkURL(MontegoBayURL);
            WorkersEmailsList = DBEmailAccessor.getManufacturingWorkersEmails();
            ITEmailsList = DBEmailAccessor.getITEmails();
            System.out.println(ITEmailsList);
            System.out.println(WorkersEmailsList);
            Email.EmailGeneralWorkers(WorkersEmailsList);
            Email.EmailITWorkers(ITEmailsList);
        } catch (IOException | ParseException | SQLException e) {
            e.printStackTrace();
        }
        if (!KingstonDaysInfo.isEmpty()){
            txtcurrentDayKingston.setText(KingstonDaysInfo.get(0).getDay().toString());
            txtDay2Kingston.setText(KingstonDaysInfo.get(1).getDay().toString());
            txtDay3Kingston.setText(KingstonDaysInfo.get(2).getDay().toString());
            txtDay4Kingston.setText(KingstonDaysInfo.get(3).getDay().toString());
            txtDay5Kingston.setText(KingstonDaysInfo.get(4).getDay().toString());
        }

        if(!MontegoBayDaysInfo.isEmpty()){
            txtCurrentDayMoBay.setText(MontegoBayDaysInfo.get(0).toString());
            txtDay2Mobay.setText(MontegoBayDaysInfo.get(1).toString());
            txtDay3Mobay.setText(MontegoBayDaysInfo.get(2).toString());
            txtDay4Mobay.setText(MontegoBayDaysInfo.get(3).toString());
            txtDay5Mobay.setText(MontegoBayDaysInfo.get(4).toString());
        }


//        txtDescriptionCurrentDayMobay;
//        txtDescriptionDay2Mobay;
//        txtDescriptionDay3Mobay;
//        txtDescriptionDay4Mobay;
//        txtDescriptionDay5Mobay;
//
//        //Kingston side description info
//        txtDescriptionCurrentDayKingston;
//        txtDescriptionDay2Kingston;
//        txtDescriptionDay3Kingston;
//        txtDescriptionDay4Kingston;
//        txtDescriptionDay5Kingston;
//
//        //Fonts for Mobay
//        fontAwesomeICONCurrentDayMobay;
//        fontAwesomeICONDayMobay2;
//        fontAwesomeICONDayMobay3;
//        fontAwesomeICONDayMobay4;
//        fontAwesomeICONDayMobay5;
//
//        //Fonts for Kingston
//        fontAwesomeICONCurrentDayKingston;
//        fontAwesomeICONDay2Kingston;
//        fontAwesomeICONDay3Kingston;
//        fontAwesomeICONDay4Kingston;
//        fontAwesomeICONDay5Kingston;

    }
}