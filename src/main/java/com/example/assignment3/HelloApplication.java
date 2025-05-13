package com.example.assignment3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch();try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/orders", "root", "Stef0578");
            if (conn != null) {
                System.out.println("Conectare reușită la baza de date!");
            } else {
                System.out.println("Conexiune eșuată.");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Eroare la conectarea cu baza de date:");
            e.printStackTrace();
        }

    }
}