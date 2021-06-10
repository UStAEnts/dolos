package net.entscrew.dolos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import net.entscrew.dolos.ClientOSC;
import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;


public class Dolos extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Dolos.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        OscP5 oscP5;
        NetAddress x32;

        oscP5 = new OscP5(new Object(), 10023);
        x32 = new NetAddress("192.168.1.78", 10023);


        OscMessage oscMessage = new OscMessage("/ch/01/mix/on");
        oscMessage.add(1);
        oscP5.send(oscMessage, x32);

        launch();

    }

}
