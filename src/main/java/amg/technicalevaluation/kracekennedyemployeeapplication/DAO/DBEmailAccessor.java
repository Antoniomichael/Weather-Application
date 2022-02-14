package amg.technicalevaluation.kracekennedyemployeeapplication.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public final class DBEmailAccessor {
    private static String ManufacturingWorkersEmails = "";
    private static String ITEmails = "";
    static String selectWorkersEmails = "SELECT dbo.EmployeeTable.email FROM dbo.EmployeeTable WHERE role = 2";
    static String selectITEmails = "SELECT dbo.EmployeeTable.email FROM dbo.EmployeeTable where role = 1";

    static Statement statement;
    static ResultSet resultSet;


        public static String getManufacturingWorkersEmails() throws SQLException {
            try {

                ServerConnection.setConnectionString(DBLibrary.CONNECTIONURL.toString());
                Connection newcon = ServerConnection.getConnection();
                assert newcon != null;
                statement = Objects.requireNonNull(newcon.createStatement());

                resultSet = statement.executeQuery(selectWorkersEmails);
//
                if(resultSet.next()){
                    do {
//                        System.out.println(resultSet.getString("email"));
                        ManufacturingWorkersEmails = ManufacturingWorkersEmails.concat( resultSet.getString("email") + ",");
                    }while(resultSet.next());

                }
                    System.out.println(ManufacturingWorkersEmails);
//
                newcon.close();
                newcon.close();
                StringBuffer sb = new StringBuffer(ManufacturingWorkersEmails);

                return  String.valueOf(sb.deleteCharAt(sb.length()-1));

            }catch(NullPointerException nullPointerException){
                System.out.println("Null return");
            }
            return null;
        }

    public static String getITEmails() throws SQLException {
        try {

            ServerConnection.setConnectionString(DBLibrary.CONNECTIONURL.toString());
            Connection newcon = ServerConnection.getConnection();
            assert newcon != null;
            statement = Objects.requireNonNull(newcon.createStatement());

            resultSet = statement.executeQuery(selectITEmails);
//
            if (resultSet.next()) {
                do {

                ITEmails = ITEmails.concat(resultSet.getString("email") + ",");


//                System.out.println(resultSet.getString("email"));
            }while (resultSet.next());
//                while(resultSet.next()){
//                    ITEmails.add(resultSet.getString("email"));
//                    System.out.println(resultSet.getString("email"));
////                }
            }
            System.out.println(ITEmails);
            newcon.close();
            StringBuffer sb = new StringBuffer(ITEmails);

            return  String.valueOf(sb.deleteCharAt(sb.length()-1));
        }catch(NullPointerException nullPointerException){
            System.out.println("Null return");
            return  null;
        }

    }


}
