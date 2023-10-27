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
import java.text.BreakIterator;
import java.util.ResourceBundle;

public class AdminPetInfo {
    @FXML
    private TextField Id;
    @FXML
    private TextField PetType;
    @FXML
    private TextField PetBreed;
    @FXML
    private  TextField PetAge;
    @FXML
    private TextField PetPrice;
    @FXML
    private TextField PetAvailability;
    @FXML
    private Label MessageDisplay1;
    @FXML
     private TableView<PetData> petTableView;
    @FXML
    private TableColumn<PetData,Integer> petId;
    @FXML
    private TableColumn<PetData,String> type;

    @FXML
    private TableColumn<PetData, String> breed;

    @FXML
    private TableColumn<PetData,Integer> age;

    @FXML
    private TableColumn<PetData,Integer> price;
    @FXML
    private TableColumn<PetData,Integer> availability;
    @FXML
    private Scene sc;
    private Stage st;
    private Parent pr;

    PetData pd;

    Connection connection;
    ObservableList<PetData>petData= FXCollections.observableArrayList();
    ObservableList<PetData>petData1= FXCollections.observableArrayList();




    public void addButtonOnAction(ActionEvent a){
        if( PetType.getText().isBlank()==false
                &&PetBreed.getText().isBlank()==false&&
                PetAge.getText().isBlank()==false&&PetPrice.getText().isBlank()==false
                &&PetAvailability.getText().isBlank()==false ) {


            String type = PetType.getText();
            String breed = PetBreed.getText();
            int age = Integer.parseInt(PetAge.getText());
            double price =Double.parseDouble(PetPrice.getText());
            int availability = Integer.parseInt(PetAvailability.getText());
             pd = new PetData( age, price, availability, type, breed);
            // messageDisplay.setText("You are trying to SignUp");
            addData();
        }
        else
            MessageDisplay1.setText("Please enter all the required details ");

    }
    public  void addData()  {
        DatabaseConnection dc=new DatabaseConnection();
        Connection con=dc.getConnection();
        String query1 = "insert into Pet_Info(Type ,Breed,Age,Price ,Availability) values(?,?,?,?,?)";
        PreparedStatement ps1;
        try {

            ps1 = con.prepareStatement(query1);

            ps1.setString(1, pd.getType());
            ps1.setString(2, pd.getBreed());
            ps1.setInt(3,pd.getAge());
            ps1.setDouble(4,pd.getPrice());
            ps1.setInt(5,pd.getAvailability());
            int i=ps1.executeUpdate();
            if(i>0)
                MessageDisplay1.setText("Data inserted successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }






@FXML
public void handleRowSelection() {
    PetData selectedPet=petTableView.getSelectionModel().getSelectedItem();
    if (selectedPet!=null) {
        Id.setText(Integer.toString(selectedPet.getId()));
       PetType.setText(selectedPet.getType());
       PetBreed.setText(selectedPet.getBreed());
        PetAge.setText(Integer.toString(selectedPet.getAge()));
        PetPrice.setText(Double.toString(selectedPet.getPrice()));
        PetAvailability.setText(Integer.toString(selectedPet.getAvailability()));
    }
}
    @FXML
    public void handleUpdateButton(ActionEvent event) {
        PetData selectedPet = petTableView.getSelectionModel().getSelectedItem();

        if (selectedPet == null) {
            MessageDisplay1.setText("No pet selected select a pet to update");

        } else {
            if (PetType.getText().isBlank() == false
                    && PetBreed.getText().isBlank() == false &&
                    PetAge.getText().isBlank() == false && PetPrice.getText().isBlank() == false
                    && PetAvailability.getText().isBlank() == false) {
              selectedPet.setType(PetType.getText());
                selectedPet.setBreed(PetBreed.getText());
                selectedPet.setAge(Integer.parseInt(PetAge.getText()));
                selectedPet.setPrice(Double.parseDouble(PetPrice.getText()));
                selectedPet.setAvailability(Integer.parseInt(PetAvailability.getText()));
                updateData(selectedPet);
            }
            else{
                MessageDisplay1.setText("Except Id all details are required");

            }
        }
    }
        public  void updateData(PetData sd)  {
            System.out.println( " updateData");
        DatabaseConnection dc=new DatabaseConnection();
        Connection con=dc.getConnection();
        String query1 = "Update Pet_Info set Type=?,Breed=?,Age=?,Price=?,Availability=? where Id=? ";
        PreparedStatement ps1;
        try {
            ps1 = con.prepareStatement(query1);

            ps1.setString(1, sd.getType());
            ps1.setString(2, sd.getBreed());
            ps1.setInt   (3,sd.getAge());
            ps1.setDouble (4,sd.getPrice());
            ps1.setInt   (5,sd.getAvailability());
            ps1.setInt   (6,sd.getId());
           System.out.println( sd.getId());

            int i=ps1.executeUpdate();
            if(i>0) {
                MessageDisplay1.setText("Data updated successfully");


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }













    public void deleteButtonOnAction(ActionEvent a){
        PetData selectedPet = petTableView.getSelectionModel().getSelectedItem();

        if (selectedPet == null) {
            MessageDisplay1.setText("No pet selected select a pet to delete");
        }
            else{
                DatabaseConnection dc = new DatabaseConnection();
                Connection con = dc.getConnection();
                String query1 = "delete from Pet_Info where Id=?";
                PreparedStatement ps1;
                try {

                    ps1 = con.prepareStatement(query1);
                    ps1.setInt(1, selectedPet.getId());
                    int i = ps1.executeUpdate();
                    if (i > 0) {
                        MessageDisplay1.setText("Data deleted successfully");

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

    }








    private void refreshtable( int id){
        try{
            petData.clear();
            String query="Select * from Pet_Info where Id=?";
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()!=false){
                petData.add(new PetData(rs.getInt("Id"),rs.getInt("Age"),
                        rs.getInt("Price"),rs.getInt("Availability"),rs.getString("Type"),
                        rs.getString("Breed")));
                petTableView.setItems(petData);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public  void  getByIDOnAction(ActionEvent e){
        if(Id.getText().isBlank()==false  ) {

            int id = Integer.parseInt(Id.getText());
            pd=new PetData(id);
            getById(id);
        }
        else {
            MessageDisplay1.setText("Please enter Id ");
        }
    }

    private void getById(int id){
        DatabaseConnection dc=new DatabaseConnection();
        connection=dc.getConnection();
        refreshtable(id);
        petId.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        availability.setCellValueFactory(new PropertyValueFactory<>("availability"));


    }




    private void refreshtableAll(){
        try{
            petData.clear();
            String query="Select * from Pet_Info";
            PreparedStatement ps=connection.prepareStatement(query);

            ResultSet rs=ps.executeQuery();
            while (rs.next()!=false){
                petData.add(new PetData(rs.getInt("Id"),rs.getInt("Age"),
                        rs.getInt("Price"),rs.getInt("Availability"),rs.getString("Type"),
                        rs.getString("Breed")));
                petTableView.setItems(petData);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public  void  getAllOnAction(ActionEvent e){

            getAll();

    }

    private void getAll(){
        DatabaseConnection dc=new DatabaseConnection();
        connection=dc.getConnection();
        refreshtableAll();
        petId.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        availability.setCellValueFactory(new PropertyValueFactory<>("availability"));


    }

    public void aPBackOnAction(ActionEvent e) throws IOException {
        Parent pt= FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();
    }


}
