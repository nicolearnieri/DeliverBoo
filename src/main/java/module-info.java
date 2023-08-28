module uid.project.deliverboo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.simplejavamail.core;
    requires org.simplejavamail;
    requires jbcrypt;
    requires org.json;
    requires org.kordamp.ikonli.javafx;
    requires org.controlsfx.controls;


    opens uid.project.deliverboo to javafx.fxml;
    exports uid.project.deliverboo;
    opens uid.project.deliverboo.controller to javafx.fxml;
}