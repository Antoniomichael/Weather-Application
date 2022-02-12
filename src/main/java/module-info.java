module amg.technicalevaluation.kracekennedyemployeeapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;

    opens amg.technicalevaluation.kracekennedyemployeeapplication to javafx.fxml;
    exports amg.technicalevaluation.kracekennedyemployeeapplication;
}