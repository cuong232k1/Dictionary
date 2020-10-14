package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.FileOutputStream;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import java.io.File;
import java.io.FileOutputStream;

public class Controller {
    protected static final DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    @FXML
    TextField text_E;
    @FXML
    TextField text_V;
    @FXML
    TextField textField;
    @FXML
    VBox vBox;
    @FXML
    ListView<String> listView = new ListView<>();
    @FXML
    ObservableList<String> listWord = FXCollections.observableArrayList();
    @FXML
    public void TextListView()  {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                try {
                    listWord = FXCollections.observableArrayList(dictionaryCommandline.dictionarySearch(newValue));
                    listView.setItems(listWord);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (newValue.equals("")){
                listWord.clear();
            }
        });
    }
    public void setOnClick(){
        listView.setOnMouseClicked(mouseEvent -> listView.getSelectionModel().getSelectedItem());
        textField.setText(listView.getSelectionModel().getSelectedItem());
    }
    @FXML
    Text text;
    public void handle(ActionEvent event) throws Exception {
        dictionaryCommandline.dic.insertFromFile();
        String text_out = textField.getText();
        String Text = dictionaryCommandline.dic.dictionarylook(text_out);
        if (Text.equals("false;")){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Web.fxml"));
                Parent root2 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root2));
                stage.setTitle("Web");
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        else {
            text.setText(Text);
        }
    }
    public void hand(ActionEvent event) throws Exception {
        Word new_W = new Word(text_E.getText(), text_V.getText());
        dictionaryCommandline.dic.add_dictionaryFile(new_W);
    }
    @FXML
    private Button addButton;
    public void handleadd(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("add Word ");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private TextField text_remove;
    public void setRemove(ActionEvent event) throws Exception {
        String re_W = text_remove.getText();
        dictionaryCommandline.dic.delete_dictionaryFile(re_W);
    }
    @FXML   
    Button remove;
    public void handleremove(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("remove.fxml"));
            Parent root2 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));
            stage.setTitle("remove Word");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void handSound() throws Exception {
        VoiceProvider tts = new VoiceProvider("1996d9533ec54847ae859c5c70ef8de6");

        VoiceParameters params = new VoiceParameters(textField.getText(), Languages.English_UnitedStates);
        params.setCodec(AudioCodec.WAV);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);
        byte[] voice = tts.speech(params);
        FileOutputStream fos = new FileOutputStream("voice.wav");
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();
        String musicFile = "voice.wav";
        Media media = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
