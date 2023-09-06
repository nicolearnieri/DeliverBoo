package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.mindrot.jbcrypt.BCrypt;
import uid.project.deliverboo.model.CurrentUser;
import uid.project.deliverboo.model.ExecutorProvider;
import uid.project.deliverboo.model.TaskCreator;
import uid.project.deliverboo.view.SceneHandler;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class PasswordController {

    @FXML
    private Button confirmationButton;

    @FXML
    private ImageView eyeImage;

    @FXML
    private Label infoLabel;

    @FXML
    private PasswordField password;

    @FXML
    private TextField seePassword;

    @FXML
    private StackPane stackPane;

    @FXML
    private Label warningLabel;


    Image openEye = new Image(Objects.requireNonNull(getClass().getResource("/Icone/eye.png")).toExternalForm());
    Image eyeOff = new Image(Objects.requireNonNull(getClass().getResource("/Icone/eye-off.png")).toExternalForm());

    private ExecutorService executor = ExecutorProvider.getExecutor();

    void initialize(){

     password.textProperty().bindBidirectional(seePassword.textProperty());
        eyeImage.setImage(openEye);

        eyeImage.setOnMouseClicked(event -> {
        if (eyeImage.getImage() == openEye) {
            eyeImage.setImage(eyeOff);
            seePassword.setVisible(true);
            password.setVisible(false);

        } else {
            eyeImage.setImage(openEye);
            seePassword.setVisible(false);
            password.setVisible(true);

        }
    });
}

    @FXML
    void confirmDelete(ActionEvent event) throws ExecutionException, InterruptedException {
        String pw = password.getText();
        String userId = CurrentUser.getInstance().getNomeUtente();

        Callable<String> getPwCallable = TaskCreator.createGetPassword(userId);
        Future<String> resultP = executor.submit(getPwCallable);

        boolean passwordOk = BCrypt.checkpw(pw, resultP.get());

        if (passwordOk)
        {
            CurrentUser.getInstance().logOut();

            Callable<Boolean> delete = TaskCreator.createDeleteUser(userId);
            Future<Boolean> exec = executor.submit(delete);

            if (exec.get())
                SceneHandler.getInstance().showConfirmation("o", "o");
        }




    }

}
