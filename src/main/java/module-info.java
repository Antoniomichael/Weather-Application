module amg.technicalevaluation.kracekennedyemployeeapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires json.simple;
//    requires com.googlecode.json-simple;

    opens amg.technicalevaluation.kracekennedyemployeeapplication to javafx.fxml;
    exports amg.technicalevaluation.kracekennedyemployeeapplication;
}