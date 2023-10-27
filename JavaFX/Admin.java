package JavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    @FXML
    private Button adminLogInButton;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private Label adminMessageDisplay;
    @FXML
    private Button adminBack;
    @FXML
    private  Button Admin;
    private Scene sc;
    private Stage st;
    private Parent pr;


    public void adminLogInOnAction(ActionEvent e){
        if(adminPassword.getText().isBlank()==false)
            validPassword();
        else{
         adminMessageDisplay.setText("Password is required");
        }
    }
    public void validPassword(){
        DatabaseConnection dc=new DatabaseConnection();
        Connection con=dc.getConnection();

        String query = "Select Password from UserInfo where Role='Admin' ";

        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ResultSet rs= ps.executeQuery();
            while(rs.next()!=false) {
                String pwd = rs.getString("password");
                System.out.println(pwd);
                if (pwd.equals(adminPassword.getText()))
                    adminMessageDisplay.setText("Welcome ");
                else {
                    adminMessageDisplay.setText("Please Enter correct Password ");
                }
            }
            }
                    catch (SQLException e) {
                System.out.println(e);}
    }
    public void BackOnAction(ActionEvent e) throws Exception {
        Parent pt= FXMLLoader.load(getClass().getResource("Register.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();

    }
    public void adminLogInButtonOnAction(ActionEvent e) throws Exception {
        Parent pt= FXMLLoader.load(getClass().getResource("CustomerHomePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();

    }
    public void adminNext(ActionEvent e) throws IOException {
        Parent pt= FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();
    }


}
