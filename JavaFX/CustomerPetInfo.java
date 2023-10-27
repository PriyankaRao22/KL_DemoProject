package JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerPetInfo {
    @FXML
    private TableView<PetData> CustomerPetDetails;
    @FXML
    private TableColumn<PetData, Integer> CId;
    @FXML
    private TableColumn<PetData, String> CType;

    @FXML
    private TableColumn<PetData, String> CBreed;

    @FXML
    private TableColumn<PetData, Integer> CAge;

    @FXML
    private TableColumn<PetData, Integer> CPrice;
    @FXML
    private TableColumn<PetData, Integer> CAvailability;
    @FXML
    private Button CPIBack;
    @FXML

    private Scene sc;
    private Stage st;
    private Parent pr;
    @FXML
    private Label CPetMessage;
    Connection connection;
    ObservableList<PetData> petData = FXCollections.observableArrayList();

    private void refreshtableAll() {
        try {
            petData.clear();
            String query = "Select * from Pet_Info";
            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next() != false) {

                petData.add(new PetData(rs.getInt("Id"), rs.getInt("Age"),
                        rs.getInt("Price"), rs.getInt("Availability"), rs.getString("Type"),
                        rs.getString("Breed")));
                CustomerPetDetails.setItems(petData);


            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void PetDetailsOnAction(ActionEvent e) {
        System.out.println(1);
        getAll();


    }

    private void getAll() {
        DatabaseConnection dc = new DatabaseConnection();
        connection = dc.getConnection();
        refreshtableAll();
        // CId.setCellValueFactory(new PropertyValueFactory<>("id"));
        CType.setCellValueFactory(new PropertyValueFactory<>("type"));
        CBreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        CAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        CPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        CAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));


    }

    public void CustomerPetBackOnAction(ActionEvent e) throws Exception {
        Parent pt = FXMLLoader.load(getClass().getResource("CustomerHomePage.fxml"));
        st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        sc = new Scene(pt);
        st.setScene(sc);
        st.show();

    }


    public void addToCartOnActionBuuton(ActionEvent a) {
        PetData selectedPet = CustomerPetDetails.getSelectionModel().getSelectedItem();

        if (selectedPet == null) {
            CPetMessage.setText("No Product selected Please select a product to add to your cart");
        }
        else
        {
            DatabaseConnection dc=new DatabaseConnection();
            Connection con=dc.getConnection();
            String query1 = "insert into Customer_Cart(Type,Breed,Age,Price,Availability) values(?,?,?,?,?)";
            PreparedStatement ps1;
            try {
                ps1 = con.prepareStatement(query1);

                ps1.setString(1, selectedPet.getType());
                ps1.setString(2, selectedPet.getBreed());
                ps1.setInt   (3,selectedPet.getAge());
                ps1.setDouble (4,selectedPet.getPrice());
                ps1.setInt   (5,selectedPet.getAvailability());


                int i=ps1.executeUpdate();
                if(i>0) {
                    CPetMessage.setText("Product successfully added to your cart");


                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


