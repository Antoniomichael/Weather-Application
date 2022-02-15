package amg.technicalevaluation.kracekennedyemployeeapplication.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;

public class ReportNotification {

    static ResultSet resultSet;


    public static Boolean wasNotNotified(LocalDate currentDate, LocalDate datetoNotify, String weather, int LocationID) {
        try {
            String selectWorkersEmails = "SELECT dbo.NotificationReportTable.dateNotifiedFor FROM dbo.NotificationReportTable WHERE dateNotifiedFor = '" + datetoNotify.toString() +"'";

            ServerConnection.setConnectionString(DBLibrary.CONNECTIONURL.toString());
            Connection newcon = ServerConnection.getConnection();
            assert newcon != null;
             Statement statement = Objects.requireNonNull(newcon.createStatement());
            resultSet = statement.executeQuery(selectWorkersEmails);
//
            if(resultSet.next()){
                newcon.close();
              return false;
//                System.out.println("Was Null");
            }else{
                submitNotificationReport(currentDate, datetoNotify, weather, LocationID);
                return  true;
            }

        }catch(NullPointerException | SQLException Exception){
//            System.out.println("Null return");
            Exception.printStackTrace();
            return false;
        }//            System.out.println("Failed");

    }

    public static void submitNotificationReport(LocalDate currentDate, LocalDate datetoNotify, String weather, int LocationID) throws SQLException {
        ServerConnection.setConnectionString(DBLibrary.CONNECTIONURL.toString());
        Connection newcon = ServerConnection.getConnection();
        assert newcon != null;
        CallableStatement storedProcedureStatement = newcon.prepareCall("{call dbo.reportNotification (?,?,?,?)}");
        storedProcedureStatement.setString("notifDate", currentDate.toString());
        storedProcedureStatement.setString("notifdateFor", datetoNotify.toString());
        storedProcedureStatement.setString("notifReason",weather);
        storedProcedureStatement.setInt("notifLocation",LocationID);


        storedProcedureStatement.executeUpdate();

    }


}
