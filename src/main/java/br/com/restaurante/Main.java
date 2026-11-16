package br.com.restaurante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/produto-view.fxml"));
        Scene scene = new Scene(loader.load(), 900, 620);
        stage.setTitle("Restaurante — Cadastro de produtos");
        stage.setMinWidth(780);
        stage.setMinHeight(560);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        br.com.restaurante.persistence.HibernateUtil.shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
