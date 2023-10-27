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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCartInfo {
    @FXML
    private TextField cartId;
    @FXML
    private TableView<CartData> cartTableView;
    @FXML
    private TableColumn<CartData,Integer> CartId;
    @FXML
    private TableColumn<CartData,String> Carttype;
   @FXML
   private Label CartMessageDisplay;
    @FXML
    private TableColumn<CartData, String> Cartbreed;

    @FXML
    private TableColumn<CartData,Integer> Cartage;

    @FXML
    private TableColumn<CartData,Integer> Cartprice;
    @FXML
    private TableColumn<CartData,Integer> Cartavailability;
    @FXML
    private Scene sc;
    private Stage st;
    private Parent pr;

    PetData pd;

    Connection connection;
    ObservableList<CartData> cartData= FXCollections.observableArrayList();





    public void cartRemoveButtonOnAction(ActionEvent a){
      CartData selected = cartTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
           CartMessageDisplay.setText("No pet selected select a pet to remove");
        }
        else{
            DatabaseConnection dc = new DatabaseConnection();
            Connection con = dc.getConnection();
            String query1 = "delete from Customer_Cart where Id=?";
            PreparedStatement ps1;
            try {

                ps1 = con.prepareStatement(query1);
                ps1.setInt(1, selected.getId());
                int i = ps1.executeUpdate();
                if (i > 0) {
                 CartMessageDisplay.setText("Product  successfully removed from your cart");

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }



    public void cartClearButtonOnAction(ActionEvent a){
        cartClearData();
    }
    public  void cartClearData()  {
        DatabaseConnection dc=new DatabaseConnection();
        Connection con=dc.getConnection();
        String query1 = "delete from Customer_Cart ";
        PreparedStatement ps1;
        try {

            ps1 = con.prepareStatement(query1);


            int i=ps1.executeUpdate();
            if(i>0)
                CartMessageDisplay.setText(" Successfully deleted from  your Cart");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






    private void refreshtableAll( ){
        try{
            cartData.clear();
            String query="Select * from Customer_Cart";
            PreparedStatement ps=connection.prepareStatement(query);

            ResultSet rs=ps.executeQuery();
            while (rs.next()!=false){
               cartData.add(new CartData(rs.getInt("Id"),rs.getInt("Age"),
                        rs.getInt("Price"),rs.getInt("Availability"),rs.getString("Type"),
                        rs.getString("Breed")));
              cartTableView.setItems(cartData);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public  void  cartDetails(ActionEvent e){
        getAll();

    }

    private void getAll(){
        DatabaseConnection dc=new DatabaseConnection();
        connection=dc.getConnection();
        refreshtableAll();

        Carttype.setCellValueFactory(new PropertyValueFactory<>("type"));
        Cartbreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        Cartage.setCellValueFactory(new PropertyValueFactory<>("age"));
      Cartprice.setCellValueFactory(new PropertyValueFactory<>("price"));
       Cartavailability.setCellValueFactory(new PropertyValueFactory<>("availability"));


    }




    public void cartBackOnAction(ActionEvent e) throws IOException {
        Parent pt= FXMLLoader.load(getClass().getResource("CustomerHomePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();
    }

    public void showAlert(String title,String header,String content){
        Alert alert=new Alert(Alert.AlertType.INFORMATION) ;
        alert.setTitle(title);
        alert.setHeaderText(header) ;
        alert.setContentText(content);
        alert.showAndWait();
    }

      public void payButtonOnAction(ActionEvent e)throws IOException {
          CartData selected = cartTableView.getSelectionModel().getSelectedItem();

          if (selected == null) {
              CartMessageDisplay.setText(" Select a product for payment ");
          }
          else {
              showAlert("Payment", "", "Your Order is successfully placed");
          }
      }



}
