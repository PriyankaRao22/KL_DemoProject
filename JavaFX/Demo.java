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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Demo {
    //  implements Initializable
    // @FXML
//    private ChoiceBox<String> role;
//    String [] UserRole={"Admin","Customer"};
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        role.getItems().addAll(UserRole);
//    }


//    @FXML
//    private Button welcomeSignUp;
//    private Button welcomeLogIn;
//    private Scene sc;
//    private Stage st;
//    private Parent pr;
    // public void welcomeSignUpOnAction(ActionEvent e) throws Exception {
//        Parent pt= FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
//        sc=new Scene(pt);
//        st.setScene(sc);
//        st.show();

    // }


    ////        button=new Button();
////        button.setText("Click here");
//
//        //or
//        Button button=new Button("Click here");
//        StackPane layout=new StackPane(button);
//        // layout.getChildren().add(button);
//
//        Scene scene=new Scene(layout,500,250);
//        stage.setScene(scene);
//        stage.show();
//
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent act) {
//             System.out.println("hello World");
//            }
//        });

//package JavaFX;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//
//import javafx.fxml.Initializable;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.BreakIterator;
//import java.util.ResourceBundle;
//
//    public class AdminPetInfo implements Initializable {
//        @FXML
//        private TextField Id;
//        @FXML
//        private TextField PetType;
//        @FXML
//        private TextField PetBreed;
//        @FXML
//        private  TextField PetAge;
//        @FXML
//        private TextField PetPrice;
//        @FXML
//        private TextField PetAvailability;
//        @FXML
//        private Label MessageDisplay1;
//        @FXML
//        private TableView<PetData> petTableView;
//        @FXML
//        private TableColumn<PetData,Integer> petId;
//        @FXML
//        private TableColumn<PetData,String> type;
//
//        @FXML
//        private TableColumn<PetData, String> breed;
//
//        @FXML
//        private TableColumn<PetData,Integer> age;
//
//        @FXML
//        private TableColumn<PetData,Integer> price;
//        @FXML
//        private TableColumn<PetData,Integer> availability;
//        PetData pd;
//        Connection connection;
//        ObservableList<PetData> petData= FXCollections.observableArrayList();
//        ObservableList<PetData>petData1= FXCollections.observableArrayList();
//
//
//
//
//        public void addButtonOnAction(ActionEvent a){
//            if(Id.getText().isBlank()==false && PetType.getText().isBlank()==false
//                    &&PetBreed.getText().isBlank()==false&&
//                    PetAge.getText().isBlank()==false&&PetPrice.getText().isBlank()==false
//                    &&PetAvailability.getText().isBlank()==false ) {
//
//                int id = Integer.parseInt(Id.getText());
//
//                String type = PetType.getText();
//                String breed = PetBreed.getText();
//                int age = Integer.parseInt(PetAge.getText());
//                int price = Integer.parseInt(PetPrice.getText());
//                int availability = Integer.parseInt(PetAvailability.getText());
//                pd = new PetData(id, age, price, availability, type, breed);
//                // messageDisplay.setText("You are trying to SignUp");
//                addData();
//            }
//            else
//                MessageDisplay1.setText("Please enter all the required details ");
//
//        }
//        public  void addData()  {
//            DatabaseConnection dc=new DatabaseConnection();
//            Connection con=dc.getConnection();
//            String query1 = "insert into Pet_Info values(?,?,?,?,?,?)";
//            PreparedStatement ps1;
//            try {
//
//                ps1 = con.prepareStatement(query1);
//                ps1.setInt(1,pd.getId());
//                ps1.setString(2, pd.getType());
//                ps1.setString(3, pd.getBreed());
//                ps1.setInt(4,pd.getAge());
//                ps1.setInt(5,pd.getPrice());
//                ps1.setInt(6,pd.getAvailability());
//                int i=ps1.executeUpdate();
//                if(i>0)
//                    MessageDisplay1.setText("Data inserted successfully");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//
//
//
//
//
//        public void updateButtonOnAction(ActionEvent a){
//            if(Id.getText().isBlank()==false && PetType.getText().isBlank()==false
//                    &&PetBreed.getText().isBlank()==false&&
//                    PetAge.getText().isBlank()==false&&PetPrice.getText().isBlank()==false
//                    &&PetAvailability.getText().isBlank()==false ) {
//
//                int id=Integer.parseInt(Id.getText());
//
//                String type=PetType.getText();
//                String breed=PetBreed.getText();
//                int age=Integer.parseInt( PetAge.getText());
//                int  price=Integer.parseInt(PetPrice.getText());
//                int availability=Integer.parseInt(PetAvailability.getText());
//                PetData pd=new PetData(id,age,price,availability,type,breed);
//                updateData();
//            }
//            else
//                MessageDisplay1.setText("Please enter all the required details ");
//
//        }
//        public  void updateData()  {
//            DatabaseConnection dc=new DatabaseConnection();
//            Connection con=dc.getConnection();
//            String query1 = "Update Pet_Info set Type=?,Breed=?,Age=?,Price=?,Availability=? where Id=? ";
//            PreparedStatement ps1;
//            try {
//                ps1 = con.prepareStatement(query1);
//
//                ps1.setString(1, pd.getType());
//                ps1.setString(2, pd.getBreed());
//                ps1.setInt   (3,pd.getAge());
//                ps1.setInt   (4,pd.getPrice());
//                ps1.setInt   (5,pd.getAvailability());
//                ps1.setInt   (6,pd.getId());
//
//                int i=ps1.executeUpdate();
//                if(i>0)
//                    MessageDisplay1.setText("Data updated successfully");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//
//
//
//
//
//
//        public void deleteButtonOnAction(ActionEvent a){
//            if(Id.getText().isBlank()==false  ) {
//
//                int id = Integer.parseInt(Id.getText());
//                String type = PetType.getText();
//                String breed = PetBreed.getText();
//                int age = Integer.parseInt(PetAge.getText());
//                int price = Integer.parseInt(PetPrice.getText());
//                int availability = Integer.parseInt(PetAvailability.getText());
//                pd = new PetData(id, age, price, availability, type, breed);
//                // messageDisplay.setText("You are trying to SignUp");
//                deleteData();
//            }
//            else
//                MessageDisplay1.setText("Please enter Id ");
//
//        }
//        public  void deleteData()  {
//            DatabaseConnection dc=new DatabaseConnection();
//            Connection con=dc.getConnection();
//            String query1 = "delete from Pet_Info where Id=?";
//            PreparedStatement ps1;
//            try {
//
//                ps1 = con.prepareStatement(query1);
//                ps1.setInt   (1,pd.getId());
//                int i=ps1.executeUpdate();
//                if(i>0)
//                    MessageDisplay1.setText("Data deleted successfully");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//
//
//
//
//
//
//
//        @Override
//        public void initialize(URL url, ResourceBundle resourceBundle) {
//            // getById();
//        }
//        private void refreshtable( int id){
//            try{
//                petData.clear();
//                String query="Select * from Pet_Info where Id=?";
//                PreparedStatement ps=connection.prepareStatement(query);
//                ps.setInt(1,id);
//                ResultSet rs=ps.executeQuery();
//                while (rs.next()!=false){
//                    petData.add(new PetData(rs.getInt("Id"),rs.getInt("Age"),
//                            rs.getInt("Price"),rs.getInt("Availability"),rs.getString("Type"),
//                            rs.getString("Breed")));
//                    petTableView.setItems(petData);
//                }
//            }
//            catch (Exception e){
//                System.out.println(e);
//            }
//        }
//        public  void  getByIDOnAction(ActionEvent e){
//            if(Id.getText().isBlank()==false  ) {
//
//                int id = Integer.parseInt(Id.getText());
////            String type = PetType.getText();
////            String breed = PetBreed.getText();
////            int age = Integer.parseInt(PetAge.getText());
////            int price = Integer.parseInt(PetPrice.getText());
////            int availability = Integer.parseInt(PetAvailability.getText());
////            pd = new PetData(id, age, price, availability, type, breed);
//                pd=new PetData(id);
//                getById(id);
//
//
//            }
//        }
//
//        private void getById(int id){
//            DatabaseConnection dc=new DatabaseConnection();
//            connection=dc.getConnection();
//            refreshtable(id);
//            petId.setCellValueFactory(new PropertyValueFactory<>("id"));
//            type.setCellValueFactory(new PropertyValueFactory<>("type"));
//            breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
//            age.setCellValueFactory(new PropertyValueFactory<>("age"));
//            price.setCellValueFactory(new PropertyValueFactory<>("price"));
//            availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
//
//
//        }
//
//
//
//
//        private void refreshtableAll( ){
//            try{
//                petData.clear();
//                String query="Select * from Pet_Info";
//                PreparedStatement ps=connection.prepareStatement(query);
//
//                ResultSet rs=ps.executeQuery();
//                while (rs.next()!=false){
//                    petData.add(new PetData(rs.getInt("Id"),rs.getInt("Age"),
//                            rs.getInt("Price"),rs.getInt("Availability"),rs.getString("Type"),
//                            rs.getString("Breed")));
//                    petTableView.setItems(petData);
//                }
//            }
//            catch (Exception e){
//                System.out.println(e);
//            }
//        }
//        public  void  getAllOnAction(ActionEvent e){
//
//            getAll();
//
//        }
//
//        private void getAll(){
//            DatabaseConnection dc=new DatabaseConnection();
//            connection=dc.getConnection();
//            refreshtableAll();
//            petId.setCellValueFactory(new PropertyValueFactory<>("id"));
//            type.setCellValueFactory(new PropertyValueFactory<>("type"));
//            breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
//            age.setCellValueFactory(new PropertyValueFactory<>("age"));
//            price.setCellValueFactory(new PropertyValueFactory<>("price"));
//            availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
//
//
//        }
//
//
//
//
//
    //  public void updateButtonOnAction(ActionEvent a){
//        if(Id.getText().isBlank()==false && PetType.getText().isBlank()==false
//                &&PetBreed.getText().isBlank()==false&&
//                PetAge.getText().isBlank()==false&&PetPrice.getText().isBlank()==false
//                &&PetAvailability.getText().isBlank()==false ) {
//
//            int id=Integer.parseInt(Id.getText());
//
//            String type=PetType.getText();
//            String breed=PetBreed.getText();
//            int age=Integer.parseInt( PetAge.getText());
//            int  price=Integer.parseInt(PetPrice.getText());
//            int availability=Integer.parseInt(PetAvailability.getText());
//            PetData pd=new PetData(id,age,price,availability,type,breed);
//            updateData();
//        }
//        else
//            MessageDisplay1.setText("Please enter all the required details ");
//
//    }
//    public  void updateData()  {
//        DatabaseConnection dc=new DatabaseConnection();
//        Connection con=dc.getConnection();
//        String query1 = "Update Pet_Info set Type=?,Breed=?,Age=?,Price=?,Availability=? where Id=? ";
//        PreparedStatement ps1;
//        try {
//            ps1 = con.prepareStatement(query1);
//
//            ps1.setString(1, pd.getType());
//            ps1.setString(2, pd.getBreed());
//            ps1.setInt   (3,pd.getAge());
//            ps1.setDouble (4,pd.getPrice());
//            ps1.setInt   (5,pd.getAvailability());
//            ps1.setInt   (6,pd.getId());
//
//            int i=ps1.executeUpdate();
//            if(i>0)
//                MessageDisplay1.setText("Data updated successfully");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

 //   public void deleteButtonOnAction(ActionEvent a){
//        if(Id.getText().isBlank()==false  ) {
//
//            int id = Integer.parseInt(Id.getText());
//            String type = PetType.getText();
//            String breed = PetBreed.getText();
//            int age = Integer.parseInt(PetAge.getText());
//            int price = Integer.parseInt(PetPrice.getText());
//            int availability = Integer.parseInt(PetAvailability.getText());
//            pd = new PetData(id, age, price, availability, type, breed);
//
//            deleteData();
//        }
//        else
//            MessageDisplay1.setText("Please enter Id ");
//
//    }
//    public  void deleteData()  {
//        DatabaseConnection dc=new DatabaseConnection();
//        Connection con=dc.getConnection();
//        String query1 = "delete from Pet_Info where Id=?";
//        PreparedStatement ps1;
//        try {
//
//            ps1 = con.prepareStatement(query1);
//            ps1.setInt   (1,pd.getId());
//            int i=ps1.executeUpdate();
//            if(i>0)
//                MessageDisplay1.setText("Data deleted successfully");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }




























//    private void refreshtableAll() {
//        try {
//            petData.clear();
//            String query = "Select * from Pet_Info";
//            PreparedStatement ps = connection.prepareStatement(query);
//
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next() != false) {
//
//                petData.add(new PetData(rs.getInt("Id"), rs.getInt("Age"),
//                        rs.getInt("Price"), rs.getInt("Availability"), rs.getString("Type"),
//                        rs.getString("Breed")));
//                CustomerPetDetails.setItems(petData);
//
//
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    public void PetDetailsOnAction(ActionEvent e) {
//        System.out.println(1);
//        getAll();
//
//
//    }
//
//    private void getAll() {
//        DatabaseConnection dc = new DatabaseConnection();
//        connection = dc.getConnection();
//        refreshtableAll();
//        // CId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        CType.setCellValueFactory(new PropertyValueFactory<>("type"));
//        CBreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
//        CAge.setCellValueFactory(new PropertyValueFactory<>("age"));
//        CPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        CAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
//
//
//    }
//
//    public void CustomerPetBackOnAction(ActionEvent e) throws Exception {
//        Parent pt = FXMLLoader.load(getClass().getResource("CustomerHomePage.fxml"));
//        st = (Stage) ((Node) e.getSource()).getScene().getWindow();
//        sc = new Scene(pt);
//        st.setScene(sc);
//        st.show();
//
//    }
//
//
//    public void addToCartOnActionBuuton(ActionEvent a) {
//        PetData selectedPet = CustomerPetDetails.getSelectionModel().getSelectedItem();
//
//        if (selectedPet == null) {
//            CPetMessage.setText("No Product selected Please select a product to add to your cart");
//        }
//        else
//        {
//            DatabaseConnection dc=new DatabaseConnection();
//            Connection con=dc.getConnection();
//            String query1 = "insert into Customer_Cart(Type,Breed,Age,Price,Availability) values(?,?,?,?,?)";
//            PreparedStatement ps1;
//            try {
//                ps1 = con.prepareStatement(query1);
//
//                ps1.setString(1, selectedPet.getType());
//                ps1.setString(2, selectedPet.getBreed());
//                ps1.setInt   (3,selectedPet.getAge());
//                ps1.setDouble (4,selectedPet.getPrice());
//                ps1.setInt   (5,selectedPet.getAvailability());
//
//
//                int i=ps1.executeUpdate();
//                if(i>0) {
//                    CPetMessage.setText("Product successfully added to your cart");
//
//
//                }
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}













    }
