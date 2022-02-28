package amg.technicalevaluation.kracekennedyemployeeapplication.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public final class DBEmailAccessor {
    private static String ManufacturingWorkersEmailsKingston = "";
    private static String ManufacturingWorkersEmailsMobay = "";
    private static String ITEmailsKingston = "";
    private static String ITEmailsMobay = "";
    static String selectWorkersEmailsKingston = "SELECT dbo.EmployeeTable.email FROM dbo.EmployeeTable WHERE role = 2 and addressLocation = 2";
    static String selectWorkersEmailsMobay = "SELECT dbo.EmployeeTable.email FROM dbo.EmployeeTable WHERE role = 2 and addressLocation = 1";

    static String selectITEmailsKingston = "SELECT dbo.EmployeeTable.email FROM dbo.EmployeeTable where role = 1 and addressLocation = 2";
    static String selectITEmailsMobay = "SELECT dbo.EmployeeTable.email FROM dbo.EmployeeTable where role = 1 and addressLocation = 1";

    static Statement statement;
    static ResultSet resultSet;


        public static String getManufacturingWorkersEmailsKingston() throws SQLException {
            try {

                ServerConnection.setConnectionString(DBLibrary.CONNECTIONURL.toString());
                Connection newcon = ServerConnection.getConnection();
                assert newcon != null;
                statement = Objects.requireNonNull(newcon.createStatement());

                resultSet = statement.executeQuery(selectWorkersEmailsKingston);
                if (resultSet.next()) {
                    do {
                        ManufacturingWorkersEmailsKingston = ManufacturingWorkersEmailsKingston.concat(resultSet.getString("email") + ",");
                    } while (resultSet.next());

                }
                System.out.println(ManufacturingWorkersEmailsKingston);

                newcon.close();
                if (!ManufacturingWorkersEmailsKingston.isEmpty()) {
                    StringBuilder sb = new StringBuilder(ManufacturingWorkersEmailsKingston);
                    return String.valueOf(sb.deleteCharAt(sb.length() - 1));
                } else {
                    return null;
                }

            }catch(NullPointerException nullPointerException){
//                System.out.println("Null return");
            }
            return null;
        }

    public static String getManufacturingWorkersEmailsMobay() throws SQLException {
        try {

            ServerConnection.setConnectionString(DBLibrary.CONNECTIONURL.toString());
            Connection newcon = ServerConnection.getConnection();
            assert newcon != null;
            statement = Objects.requireNonNull(newcon.createStatement());

            resultSet = statement.executeQuery(selectWorkersEmailsMobay);
//
            if (resultSet.next()) {
                do {
                   ManufacturingWorkersEmailsMobay = ManufacturingWorkersEmailsMobay.concat(resultSet.getString("email") + ",");
                } while (resultSet.next());

            }
            System.out.println(ManufacturingWorkersEmailsMobay);
//
            newcon.close();
            if (!ManufacturingWorkersEmailsMobay.isEmpty()) {
                StringBuilder sb = new StringBuilder(ManufacturingWorkersEmailsMobay);

                return String.valueOf(sb.deleteCharAt(sb.length() - 1));
            } else {
                return null;
            }
        }catch(NullPointerException nullPointerException){
//            System.out.println("Null return");
        }
        return null;
    }

    public static String getITEmailsKingston() throws SQLException {
        try {

            ServerConnection.setConnectionString(DBLibrary.CONNECTIONURL.toString());
            Connection newcon = ServerConnection.getConnection();
            assert newcon != null;
            statement = Objects.requireNonNull(newcon.createStatement());

            resultSet = statement.executeQuery(selectITEmailsKingston);
//
            if (resultSet.next()) {
                do {
                    ITEmailsKingston = ITEmailsKingston.concat(resultSet.getString("email") + ",");
                } while (resultSet.next());
            }
            System.out.println(ITEmailsKingston);
            newcon.close();
            if (!ITEmailsKingston.isEmpty()) {
                StringBuilder sb = new StringBuilder(ITEmailsKingston);

                try {
                    return String.valueOf(sb.deleteCharAt(sb.length() - 1));
                } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
                    System.out.println("String out of bounds");
                    return null;
                }
            } else {
                return null;
            }
        } catch (NullPointerException nullPointerException) {
//            System.out.println("Null return");
            return null;
        }

    }

    public static String getITEmailsMobay() throws SQLException {
        try {

            ServerConnection.setConnectionString(DBLibrary.CONNECTIONURL.toString());
            Connection newcon = ServerConnection.getConnection();
            assert newcon != null;
            statement = Objects.requireNonNull(newcon.createStatement());

            resultSet = statement.executeQuery(selectITEmailsMobay);
//
            if (resultSet.next()) {
                do {

                    ITEmailsMobay = ITEmailsMobay.concat(resultSet.getString("email") + ",");

                } while (resultSet.next());
            }
            System.out.println(ITEmailsMobay);
            newcon.close();
            if (!ITEmailsMobay.isEmpty()) {
                StringBuilder sb = new StringBuilder(ITEmailsMobay);
                return String.valueOf(sb.deleteCharAt(sb.length() - 1));
            } else {
                return null;
            }
        }catch(NullPointerException nullPointerException){
            return  null;
        }

    }

}
