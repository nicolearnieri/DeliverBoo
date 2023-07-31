module uid.project.deliverboo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.simplejavamail.core;
    requires org.simplejavamail;


    opens uid.project.deliverboo to javafx.fxml;
    exports uid.project.deliverboo;
}