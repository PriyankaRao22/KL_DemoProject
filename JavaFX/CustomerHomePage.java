package JavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;

public class CustomerHomePage {

    @FXML
    private Button  HomeCancel;
    @FXML
    private Button CPetInfo;

    @FXML
    private Scene sc;
    private Stage st;
    private Parent pr;

    public void homeCancelOnAction(ActionEvent e) throws Exception {
        Stage st=(Stage)HomeCancel.getScene().getWindow();
        st.close();
    }

    public void homePet_InfoOnAction(ActionEvent e) throws Exception {
        Parent pt= FXMLLoader.load(getClass().getResource("CustomerPetInfo.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();

    }
    public void cartInfoOnAction(ActionEvent e) throws IOException {
        Parent pt= FXMLLoader.load(getClass().getResource("CustomerCartInfo.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();
    }
    public void logOutOnAction(ActionEvent e) throws IOException {
        Parent pt= FXMLLoader.load(getClass().getResource("Register.fxml"));
        st=(Stage)((Node)e.getSource()).getScene().getWindow();
        sc=new Scene(pt);
        st.setScene(sc);
        st.show();
    }
}
