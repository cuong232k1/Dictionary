package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Web extends Controller implements Initializable {
    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //    @FXML
        //    private TextField addressBar;
        WebEngine engine = webView.getEngine();
        engine.load("https://translate.google.com/?hl=vi");
//        engine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
//            if (newState == Worker.State.SUCCEEDED){
//                addressBar.setText(engine.getLocation());
//            }
//        });
    }
//    public void keyHandle(KeyEvent ke){
//        if (ke.getCode().equals(KeyCode.ENTER)){
//            String address = addressBar.getText();
//            engine.load(address);
//        }
//    }
//
//    public void backHandle(ActionEvent e){
//        if(engine.getHistory().getCurrentIndex()>0){
//            engine.getHistory().go(-1);
//        }
//    }
//
//    public void forwardHandle(ActionEvent e){
//        if (engine.getHistory().getCurrentIndex()+1 < engine.getHistory().getEntries().size()){
//            engine.getHistory().go(1);
//        }
//    }
}