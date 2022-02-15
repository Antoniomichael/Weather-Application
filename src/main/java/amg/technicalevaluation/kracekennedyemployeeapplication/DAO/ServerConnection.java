package amg.technicalevaluation.kracekennedyemployeeapplication.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ServerConnection {
    private static Connection connection;
    private static String connectionString;

    public static void setConnectionString(String connectionString){
        assert connectionString != null;
        ServerConnection.connectionString = connectionString;

    }

    public static Connection getConnection(){
        try{
            connection = DriverManager.getConnection(connectionString);

            assert connection != null;
            return connection;
        } catch (SQLException e) {
            System.out.println("Driver Issues/ connection string");
            e.printStackTrace();
            return null;
        }
    }

//    public static void closeConnection() throws SQLException {
//        connection.close();
//    }
}
