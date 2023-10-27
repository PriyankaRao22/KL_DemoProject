package JavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Registration{
    @FXML
    private Button cancelButton;
    @FXML
    private Label messageDisplay;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
  @FXML
  private Button loginNext;
    @FXML
    private Button registerBackButton;
    @FXML
    private  Button Admin;
    private Scene sc;
    private Stage st;
    private Parent pr;


    public void cancelButtonOnAction(ActionEvent a){
        Stage st=(Stage)cancelButton.getScene().getWindow();
        st.close();
    }
    public void loginButtonOnAction(ActionEvent a){
        if(userName.getText().isBlank()==false && password.getText().isBlank()==false) {
            valideLogIn();
        }
        else {
            messageDisplay.setText("Please enter all the required details");
        }
    }
    public void valideLogIn(){
        DatabaseConnection dc=new DatabaseConnection();
        Connection con=dc.getConnection();
       String query = "Select Password from UserInfo where UserName=? ";

        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,userName.getText());
            ResultSet rs= ps.executeQuery();

           if(true) {
                messageDisplay.setText("please enter correct username");
            }

                while (rs.next() != false) {
                    System.out.println("excute");
                    String pwd = rs.getString("Password");
                    if (pwd.equals(password.getText())) {
                        messageDisplay.setText("Your Login is Successfull ");
                        break;
                    }
                    else
                        messageDisplay.setText("Please enter correct password ");


                }
        }


        catch (SQLException e) {
            System.out.println(e);
        }


    }




    public void AdminOnAction(ActionEvent e) throws Exception {
        Parent pt= FXMLLoader.load(getClass().getResource("Admin.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();
    }

    public void registerBackOnAction(ActionEvent e) throws Exception {
        Parent pt= FXMLLoader.load(getClass().getResource("WelComePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();

    }

    public void redirectByLogInOnAction(ActionEvent e) throws IOException {
        Parent pt= FXMLLoader.load(getClass().getResource("CustomerHomePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();
    }


}


