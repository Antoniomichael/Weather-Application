package amg.technicalevaluation.kracekennedyemployeeapplication;

import amg.technicalevaluation.kracekennedyemployeeapplication.DAO.DBEmailAccessor;
import amg.technicalevaluation.kracekennedyemployeeapplication.DAO.ReportNotification;
import amg.technicalevaluation.kracekennedyemployeeapplication.model.DaysModel;
import amg.technicalevaluation.kracekennedyemployeeapplication.model.Email;
import amg.technicalevaluation.kracekennedyemployeeapplication.model.WeatherAPI;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class mainController implements Initializable {
    List<DaysModel> KingstonDaysInfo = new ArrayList<>();
    List<DaysModel> MontegoBayDaysInfo = new ArrayList<>();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AutomateEverything();
        updateUI();
//
    }

    public void AutomateEverything() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::updateUI, 0, 6, TimeUnit.HOURS);

    }


    public void updateUI() {


        KingstonDaysInfo = new ArrayList<>();
        MontegoBayDaysInfo = new ArrayList<>();
        String ITEmailsListKingston;
        String ITEmailsListMobay;

        String WorkersEmailsListKingston;
        String WorkersEmailsListMobay;

        WeatherAPI KingstonWeather = new WeatherAPI();
        WeatherAPI MontegoBayWeather = new WeatherAPI();


        try {
            KingstonDaysInfo = KingstonWeather.checkURL(KingstonURL);
            MontegoBayDaysInfo = MontegoBayWeather.checkURL(MontegoBayURL);

            WorkersEmailsListKingston = DBEmailAccessor.getManufacturingWorkersEmailsKingston();
            WorkersEmailsListMobay = DBEmailAccessor.getManufacturingWorkersEmailsMobay();

            ITEmailsListKingston = DBEmailAccessor.getITEmailsKingston();
            ITEmailsListMobay = DBEmailAccessor.getITEmailsMobay();
            assert ITEmailsListKingston != null;
//            System.out.println(ITEmailsListKingston);
            assert ITEmailsListMobay != null;

//                System.out.println(ITEmailsListMobay);
            assert WorkersEmailsListMobay != null;

//                System.out.println(WorkersEmailsListMobay);
            assert WorkersEmailsListKingston != null;

//                System.out.println(WorkersEmailsListKingston);

            if (!KingstonDaysInfo.get(1).isSunny()) {

                if (ReportNotification.wasNotNotified(KingstonDaysInfo.get(0).getDay(), KingstonDaysInfo.get(1).getDay(),
                        KingstonDaysInfo.get(1).getWeather(), 2)) {

                    if (!ITEmailsListKingston.isEmpty()) {
                        Email.EmailITWorkers(ITEmailsListKingston);
                    }
                    if (!WorkersEmailsListKingston.isEmpty()) {
                        Email.EmailGeneralWorkers(WorkersEmailsListKingston);
                    }
                }
            }
            if (!MontegoBayDaysInfo.get(1).isSunny()){
                if (ReportNotification.wasNotNotified(MontegoBayDaysInfo.get(0).getDay(),MontegoBayDaysInfo.get(1).getDay(),
                        MontegoBayDaysInfo.get(1).getWeather(),1 )) {
                    if (!ITEmailsListMobay.isEmpty()) {
                        Email.EmailITWorkers(ITEmailsListMobay);
                    }
                    if (!WorkersEmailsListMobay.isEmpty()){
                        Email.EmailGeneralWorkers(WorkersEmailsListMobay);
                    }
                }
            }
            Platform.runLater(this::updateJAVAFX);

        } catch (IOException | ParseException | SQLException e) {
            e.printStackTrace();
//            System.out.println("mainController");
        }

    }

    public void updateJAVAFX() {
        assert KingstonDaysInfo != null;
        if (!KingstonDaysInfo.isEmpty()) {
            txtcurrentDayKingston.setText(KingstonDaysInfo.get(0).getDay().getDayOfWeek().toString() + ", " + KingstonDaysInfo.get(0).getDay().getDayOfMonth());
//            fontAwesomeICONCurrentDayMobay.setIcon(FontAwesomeIconName.SMILE_ALT);
            if (!KingstonDaysInfo.get(0).isSunny()) {
                fontAwesomeICONCurrentDayKingston.setIcon(FontAwesomeIconName.UMBRELLA);
            }
            txtDay2Kingston.setText(KingstonDaysInfo.get(1).getDay().getDayOfWeek().toString() + ", " + KingstonDaysInfo.get(1).getDay().getDayOfMonth());
            if (!KingstonDaysInfo.get(1).isSunny()) {
                fontAwesomeICONDay2Kingston.setIcon(FontAwesomeIconName.UMBRELLA);
            }
            txtDay3Kingston.setText(KingstonDaysInfo.get(2).getDay().getDayOfWeek().toString() + ", " + KingstonDaysInfo.get(2).getDay().getDayOfMonth());
            if (!KingstonDaysInfo.get(2).isSunny()) {
                fontAwesomeICONDay3Kingston.setIcon(FontAwesomeIconName.UMBRELLA);
            }
            txtDay4Kingston.setText(KingstonDaysInfo.get(3).getDay().getDayOfWeek().toString() + ", " + KingstonDaysInfo.get(3).getDay().getDayOfMonth());
            if (!KingstonDaysInfo.get(3).isSunny()) {
                fontAwesomeICONDay4Kingston.setIcon(FontAwesomeIconName.UMBRELLA);
            }
            txtDay5Kingston.setText(KingstonDaysInfo.get(4).getDay().getDayOfWeek().toString() + ", " + KingstonDaysInfo.get(4).getDay().getDayOfMonth());
            if (!KingstonDaysInfo.get(4).isSunny()) {
                fontAwesomeICONDay5Kingston.setIcon(FontAwesomeIconName.UMBRELLA);
            }


            txtDescriptionCurrentDayKingston.setText(KingstonDaysInfo.get(0).getWeather());
            txtDescriptionDay2Kingston.setText(KingstonDaysInfo.get(1).getWeather());
            txtDescriptionDay3Kingston.setText(KingstonDaysInfo.get(2).getWeather());
            txtDescriptionDay4Kingston.setText(KingstonDaysInfo.get(3).getWeather());
            txtDescriptionDay5Kingston.setText(KingstonDaysInfo.get(4).getWeather());

        }

        assert MontegoBayDaysInfo != null;
        if (!MontegoBayDaysInfo.isEmpty()) {
            txtCurrentDayMoBay.setText(MontegoBayDaysInfo.get(0).getDay().getDayOfWeek().toString() + ", " + MontegoBayDaysInfo.get(0).getDay().getDayOfMonth());
            if (!MontegoBayDaysInfo.get(0).isSunny()) {
                fontAwesomeICONCurrentDayMobay.setIcon(FontAwesomeIconName.UMBRELLA);
            }
            txtDay2Mobay.setText(MontegoBayDaysInfo.get(1).getDay().getDayOfWeek().toString() + ", " + MontegoBayDaysInfo.get(1).getDay().getDayOfMonth());
            if (!MontegoBayDaysInfo.get(1).isSunny()) {
                fontAwesomeICONDayMobay2.setIcon(FontAwesomeIconName.UMBRELLA);
            }
            txtDay3Mobay.setText(MontegoBayDaysInfo.get(2).getDay().getDayOfWeek().toString() + ", " + MontegoBayDaysInfo.get(2).getDay().getDayOfMonth());
            if (!MontegoBayDaysInfo.get(2).isSunny()) {
                fontAwesomeICONDayMobay3.setIcon(FontAwesomeIconName.UMBRELLA);
            }
            txtDay4Mobay.setText(MontegoBayDaysInfo.get(3).getDay().getDayOfWeek().toString() + ", " + MontegoBayDaysInfo.get(3).getDay().getDayOfMonth());
            if (!MontegoBayDaysInfo.get(3).isSunny()) {
                fontAwesomeICONDayMobay4.setIcon(FontAwesomeIconName.UMBRELLA);
            }
            txtDay5Mobay.setText(MontegoBayDaysInfo.get(4).getDay().getDayOfWeek().toString() + ", " + MontegoBayDaysInfo.get(4).getDay().getDayOfMonth());
            if (!MontegoBayDaysInfo.get(4).isSunny()) {
                fontAwesomeICONDayMobay5.setIcon(FontAwesomeIconName.UMBRELLA);
            }

            txtDescriptionCurrentDayMobay.setText(MontegoBayDaysInfo.get(0).getWeather());
            txtDescriptionDay2Mobay.setText(MontegoBayDaysInfo.get(1).getWeather());
            txtDescriptionDay3Mobay.setText(MontegoBayDaysInfo.get(2).getWeather());
            txtDescriptionDay4Mobay.setText(MontegoBayDaysInfo.get(3).getWeather());
            txtDescriptionDay5Mobay.setText(MontegoBayDaysInfo.get(4).getWeather());
        }
    }
}


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
