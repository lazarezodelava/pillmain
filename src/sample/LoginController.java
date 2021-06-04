package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;



    public class LoginController implements Initializable{
        @FXML
        private Button cancelButton;
        @FXML
        private Label loginMessageLabel;
        @FXML
        private ImageView brandingImageView;
        @FXML
        private TextField usernameTextField;
        @FXML
        private PasswordField enterPasswordField;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            File brandingFile = new File("image/pill.png");
            Image brandingImage = new Image(brandingFile.toURI().toString());
            brandingImageView.setImage(brandingImage);
        }


    public void loginButtonOnAction(ActionEvent event) {

        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            loginMessageLabel.setText("Wrong Username or Password");
            validateLogin();
        }else {
            loginMessageLabel.setText("please enter username or password");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtonOnAction(ActionEvent event){
        crateAccountForm();
    }

        public void validateLogin(){
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String veryfyLogin = " SELECT * from user_account where username = '" + usernameTextField.getText() + "' and password = '" + enterPasswordField.getText() + "'";

            try {
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(veryfyLogin);

                while(queryResult.next()){
                    if(queryResult.getInt(1) == 1){
                        loginMessageLabel.setText("Logged In Succesfully");

                    }else {
                        loginMessageLabel.setText("Invalid Log In");
                    }
                }


            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }

        }

        public  void crateAccountForm(){
            try{

                Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
                Stage registerStage = new Stage();
                registerStage.initStyle(StageStyle.UNDECORATED);
                registerStage.setScene(new Scene(root, 600, 522));
                registerStage.show();

            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }

    }
