module amg.technicalevaluation.kracekennedyemployeeapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires json.simple;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.mail;
    requires fontawesomefx;

    opens amg.technicalevaluation.kracekennedyemployeeapplication to javafx.fxml;
    exports amg.technicalevaluation.kracekennedyemployeeapplication;
    exports amg.technicalevaluation.kracekennedyemployeeapplication.DAO;
    opens amg.technicalevaluation.kracekennedyemployeeapplication.DAO to javafx.fxml;
    exports amg.technicalevaluation.kracekennedyemployeeapplication.model;
    opens amg.technicalevaluation.kracekennedyemployeeapplication.model to javafx.fxml;
}