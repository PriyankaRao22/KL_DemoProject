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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpPage {
    @FXML
    private Button cancelSignUp;
    @FXML
    private Button signUp;
    @FXML
    private TextField userNameSignUp;
    @FXML
    private TextField mobileSignUp;
    @FXML
    private PasswordField passwordSignUp;
    @FXML
    private Label messageDisplaySignUp;
    @FXML
    private Button signupBack;
    @FXML
    private Scene sc;
    private Stage st;
    private Parent pr;

    public void cancelButtonSignUpOnAction(ActionEvent a){
        Stage st=(Stage)cancelSignUp.getScene().getWindow();
        st.close();
    }
    public void signUpButtonOnAction(ActionEvent a){
        if(userNameSignUp.getText().isBlank()==false && passwordSignUp.getText().isBlank()==false
                &&mobileSignUp.getText().isBlank()==false )
            // messageDisplay.setText("You are trying to SignUp");
            valideSignUp();
        else
            messageDisplaySignUp.setText("Please enter all the required details ");

    }
    public void valideSignUp(){

        DatabaseConnection dc=new DatabaseConnection();
        Connection con=dc.getConnection();

        String query1 = "insert into UserInfo(Username,Password,Role)values(?,?,?)";
        String query2="Select Id from UserInfo where UserName=? and password=?";
        String query3="insert into Customer_Info(Id,UserName,Password" +
                              ",MobileNo) values(?,?,?,?)";
        String query4="delete from UserInfo where UserName=? ";
        PreparedStatement ps1;
        String role="Customer";

        try {

                    ps1 = con.prepareStatement(query1);
                    ps1.setString(1, userNameSignUp.getText());
                    ps1.setString(2, passwordSignUp.getText());
                    ps1.setString(3, role);
                    int i = ps1.executeUpdate();
                    if (i > 0) {
                        System.out.println("2 query excution start");
                        ps1 = con.prepareStatement(query2);
                        ps1.setString(1, userNameSignUp.getText());
                        ps1.setString(2, passwordSignUp.getText());
                        ResultSet rs1 = ps1.executeQuery();
                        String Id = " ";
                        while (rs1.next() != false) {
                            Id = rs1.getString("Id");
                            System.out.println(Id);
                            if (Id != " ")
                                break;
                        }
                        System.out.println("3 query excution start");
                        ps1 = con.prepareStatement(query3);
                        ps1.setString(1, Id);
                        ps1.setString(2, userNameSignUp.getText());
                        ps1.setString(3, passwordSignUp.getText());
                        ps1.setString(4, mobileSignUp.getText());
                        int i1 = ps1.executeUpdate();
                        System.out.println(i1);
                        if (i1 > 0){
                            messageDisplaySignUp.setText("Your SignUp is Successfull ");


                    }
                        else {
                            System.out.println("else condition");
                            ps1 = con.prepareStatement(query4);
                            ps1.setString(1, userNameSignUp.getText());
                            int i3 = ps1.executeUpdate();
                            messageDisplaySignUp.setText("you already login with this number ");
                                    }
                              }
                   }
                    catch (SQLException e) {
                        System.out.println(e);}

    }
       public void backOnAction(ActionEvent e) throws Exception {
        Parent pt= FXMLLoader.load(getClass().getResource("WelComePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();

    }


    public void redirectBySignUp(ActionEvent e) throws IOException {
        Parent pt= FXMLLoader.load(getClass().getResource("CustomerHomePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();
    }



}
