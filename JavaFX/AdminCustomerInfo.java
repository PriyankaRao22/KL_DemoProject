package JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminCustomerInfo {
    @FXML
    private Scene sc;
    private Stage st;
    private Parent pr;
    CustomerData cd;
    @FXML
    private TableView<CustomerData> AdminCustomerTablleView;
    @FXML
    private TableColumn<CustomerData,String> ContactColumn;

    @FXML
    private TableColumn<CustomerData,Integer> IDColumn;

    @FXML
    private TableColumn<CustomerData,String> NameColumn;

    @FXML
    private TableColumn<CustomerData,String> PasswordColumn;
    @FXML
    private Label AdminCustomerMessageDisplay;
    @FXML
    private Button getAllField;

    @FXML
    private Button getByIDField;

    @FXML
    private TextField idField;
    ObservableList<CustomerData> custData= FXCollections.observableArrayList();
    ObservableList<CustomerData>custData1= FXCollections.observableArrayList();
    Connection connection;



    public void customerInfoBackOnAction(ActionEvent e) throws Exception {
        Parent pt= FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();

    }


    private void refreshtable( int id){
        try{
          custData1.clear();
            String query="Select * from Customer_Info where Id=?";
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()!=false){
             custData1.add(new CustomerData(rs.getInt("Id")
                     ,rs.getString("UserName"),rs.getString("MobileNo")
                     ,rs.getString("Password")));
              AdminCustomerTablleView.setItems(custData1);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public  void  aCGetByIDOnAction(ActionEvent e){
        if(idField.getText().isBlank()==false  ) {

            int id = Integer.parseInt(idField.getText());
         cd=new CustomerData(id);
            getById(id);
        }
        else
            AdminCustomerMessageDisplay.setText("Please Enter Id");

    }

    private void getById(int id){
        DatabaseConnection dc=new DatabaseConnection();
        connection=dc.getConnection();
        refreshtable(id);
      IDColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
       NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ContactColumn.setCellValueFactory(new PropertyValueFactory<>("MobileNo"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));



    }






    private void refreshtableAll(){
        try{
            custData.clear();
            String query="Select * from Customer_Info";
            PreparedStatement ps=connection.prepareStatement(query);

            ResultSet rs=ps.executeQuery();
            while (rs.next()!=false){
                custData.add(new CustomerData(rs.getInt("Id")
                        ,rs.getString("UserName"),rs.getString("MobileNo")
                        ,rs.getString("Password")));
                AdminCustomerTablleView.setItems(custData);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public  void  aCGetAllOnAction(ActionEvent e){

        getAll();

    }

    private void getAll(){
        DatabaseConnection dc=new DatabaseConnection();
        connection=dc.getConnection();
        refreshtableAll();
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ContactColumn.setCellValueFactory(new PropertyValueFactory<>("MobileNo"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));

    }






}
