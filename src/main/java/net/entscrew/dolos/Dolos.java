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
import oscP5.OscProperties;


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
        OscProperties x32;

        x32 = new OscProperties();
        x32.setRemoteAddress("192.168.1.78", 10023);
        x32.setListeningPort(10023);
        x32.setSRSP(OscProperties.ON);
        x32.setDatagramSize(1024);

        oscP5 = new OscP5(new Object(), x32);

        // Sign up to recieve updates from X32
        OscMessage oscMessage = new OscMessage("/xremote");
        oscP5.send(oscMessage);

        oscMessage = new OscMessage("/ch/01/mix/on");
        oscMessage.add(1);
        oscP5.send(oscMessage);

        oscMessage = new OscMessage("/unsubscribe");
        oscP5.send(oscMessage);

        launch();

    }

    void oscEvent(OscMessage oscMessage) {
        System.out.println("### received an osc message.");
        System.out.println(" addrpattern: "+oscMessage.addrPattern());
        System.out.println(" typetag: "+oscMessage.typetag());
    }

}
