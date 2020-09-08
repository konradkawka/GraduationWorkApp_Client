package pl.kawka.appobrona.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import org.json.simple.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class EmployeeCreateController {

    @FXML
    private TextField idFieldFirstName, idFieldLastName, idFieldLogin, idFieldPassword;

    @FXML
    private ChoiceBox choiceBoxStatus;


    public void initialize(){
        choiceBoxStatus.setTooltip(new Tooltip("Wybierz status"));
        choiceBoxStatus.setItems(FXCollections.observableArrayList(
                "Pracownik", new Separator(), "Admin"));
        choiceBoxStatus.getSelectionModel().selectFirst();
    }

    @FXML
    public void actionCreateEmployee() {

        System.out.println("********** Tworzenie pracownika **********");

        JSONObject json = new JSONObject();
        json.put("id", 0);
        json.put("firstName", idFieldFirstName.getText());
        json.put("lastName", idFieldLastName.getText());
        json.put("status", choiceBoxStatus.getValue());
        json.put("login", idFieldLogin.getText());
        json.put("password", idFieldPassword.getText());

        //System.out.println(json);

        try {
            URL url = new URL("http://localhost:8080/api/employee/create");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            wr.flush();
            wr.close();
            conn.getInputStream();

        } catch (Exception ex) {
            ex.printStackTrace();
            //logger.error("Loading Application Error.", ex);
        }

    }

}
