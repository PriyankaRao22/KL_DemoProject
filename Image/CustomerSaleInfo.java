package Image;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CustomerSaleInfo {
//    @FXML
//    private TextField SalesId;
//    @FXML
//    private DatePicker SalesDate;
//    @FXML
//    private TextField SalesCustomer;
//    @FXML
//    private TextField SalesItems;
//    @FXML
//    private TextField SalesAmount;
//    @FXML
//    private TableView<SalesData> salesDataTableView;
//    @FXML
//    private TableColumn<SalesData, Integer> SId;
//    @FXML
//    private TableColumn<SalesData, String> SDate;
//
//    @FXML
//    private TableColumn<SalesData, String> SCustomer;
//
//    @FXML
//    private TableColumn<SalesData, Integer> SItems;
//    @FXML
//    private TableColumn<SalesData,Integer> SAmount;
@FXML
private Scene sc;
    private Stage st;
    private Parent pr;


    public void salesInfoBackOnAction(ActionEvent e) throws Exception {
        Parent pt= FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();

    }

}