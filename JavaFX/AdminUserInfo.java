package JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminUserInfo implements Initializable {
    @FXML
    private TextField UserId;
    @FXML
    private TextField UserName;
    @FXML
    private TextField UserPassword;
    @FXML
    private TextField UserRole;

    @FXML
    private Label MessageDisplayUser;
    @FXML
    private TableView<UserData> UserTableView;
    @FXML
    private TableColumn<UserData, Integer> UCId;
    @FXML
    private TableColumn<UserData, String> UCName;

    @FXML
    private TableColumn<UserData, String> UCPassword;

    @FXML
    private TableColumn<UserData, String> UCRole;
    @FXML
    private Scene sc;
    private Stage st;
    private Parent pr;

    UserData ud;

    Connection connection;
    ObservableList<UserData> userData = FXCollections.observableArrayList();
    ObservableList<UserData> userData1 = FXCollections.observableArrayList();


    public void userAddButtonOnAction(ActionEvent a) {
        if (UserName.getText().isBlank() == false
                && UserPassword.getText().isBlank() == false &&
                UserRole.getText().isBlank() == false) {


            String name = UserName.getText();
            String password = UserPassword.getText();
            String role = UserRole.getText();

            ud = new UserData( name, password, role);
            // messageDisplay.setText("You are trying to SignUp");
            addData();
        } else
            MessageDisplayUser.setText("Please enter all the required details ");

    }

    public void addData() {
        DatabaseConnection dc = new DatabaseConnection();
        Connection con = dc.getConnection();
        String query1 = "insert into UserInfo(UserName,Password,Role) values(?,?,?)";
        PreparedStatement ps1;
        try {

            ps1 = con.prepareStatement(query1);

            ps1.setString(1, ud.getUserName());
            ps1.setString(2, ud.getPassword());
            ps1.setString(3, ud.getRole());

            int i = ps1.executeUpdate();
            if (i > 0)
                MessageDisplayUser.setText("Data inserted successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }








    @FXML
    public void handleRowSelection() {
        UserData selectedUser=UserTableView.getSelectionModel().getSelectedItem();
        if (selectedUser!=null) {
      //   UserId.setText(Integer.toString(selectedUser.getId()));
          UserName.setText((selectedUser.getUserName()));
            UserPassword.setText((selectedUser.getPassword()));
           UserRole.setText((selectedUser.getRole()));
        }
    }
    @FXML
    public void handleUserUpdateButton(ActionEvent event) {
       UserData selectedUser = UserTableView.getSelectionModel().getSelectedItem();

        if ( selectedUser == null) {
            MessageDisplayUser.setText("No USer is selected Please select a User to update");

        } else {
            if (UserName.getText().isBlank() == false
                    && UserPassword.getText().isBlank() == false &&
                    UserRole.getText().isBlank() == false ) {
                selectedUser.setUserName(UserName.getText());
                selectedUser.setPassword(UserPassword.getText());
                selectedUser.setRole((UserRole.getText()));

                updateData(selectedUser);
            }
            else{
                MessageDisplayUser.setText("All details are required");

            }
        }
    }
    public  void updateData(UserData sd)  {
        System.out.println( " updateData");
        DatabaseConnection dc=new DatabaseConnection();
        Connection con=dc.getConnection();
        String query1 = "Update UserInfo set UserName=?,Password=?,Role=? where Id=? ";
        PreparedStatement ps1;
        try {
            ps1 = con.prepareStatement(query1);

            ps1.setString(1, sd.getUserName());
            ps1.setString(2, sd.getPassword());
            ps1.setString  (3,sd.getRole());
            ps1.setInt(4,sd.getId());
            int i=ps1.executeUpdate();
            if(i>0) {
              MessageDisplayUser.setText("Data updated successfully");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }































    public void deleteUserButtonOnAction(ActionEvent a) {
        UserData selectedUser = UserTableView.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
           MessageDisplayUser.setText("No User Selected,  select a User to delete");
        } else {
            DatabaseConnection dc = new DatabaseConnection();
            Connection con = dc.getConnection();
            String query1 = "delete from UserInfo where Id=?";
            PreparedStatement ps1;
            try {

                ps1 = con.prepareStatement(query1);
                ps1.setInt(1, selectedUser.getId());
                int i = ps1.executeUpdate();
                if (i > 0) {
                    MessageDisplayUser.setText("Data deleted successfully");

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
























    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // getById();
    }
    private void refreshtable(String name ){
        try{
            userData.clear();
            String query="Select * from UserInfo where UserName=?";
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            while (rs.next()!=false){
                userData.add(new UserData(rs.getInt("Id"),rs.getString("UserName"),
                        rs.getString("Password"), rs.getString("Role")));
                UserTableView.setItems(userData);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public  void  userGetByNameOnAction(ActionEvent e){
        if(UserName.getText().isBlank()==false  ) {

           String name= UserName.getText();
            ud=new UserData(name);
            getByName(name);
        }
        else
            MessageDisplayUser.setText("Please enter UserName");
    }

    private void getByName(String name){
        DatabaseConnection dc=new DatabaseConnection();
        connection=dc.getConnection();
        refreshtable(name);
        UCId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        UCName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        UCPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        UCRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }






















    private void refreshtableAll( ){
        try{
            userData.clear();
            String query="Select * from UserInfo";
            PreparedStatement ps=connection.prepareStatement(query);

            ResultSet rs=ps.executeQuery();
            while (rs.next()!=false){
                userData.add(new UserData(rs.getInt("Id"),rs.getString("userName"),
                        rs.getString("password"), rs.getString("role")));
                UserTableView.setItems(userData);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public  void  usergetAllOnAction(ActionEvent e){
        usergetAll();

    }

    private void usergetAll(){
        DatabaseConnection dc=new DatabaseConnection();
        connection=dc.getConnection();
        refreshtableAll();
        UCId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        UCName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        UCPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        UCRole.setCellValueFactory(new PropertyValueFactory<>("role"));

    }
    public void userBackOnAction(ActionEvent e) throws Exception {
        Parent pt= FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();

    }




}