package com.wanho.java180.util;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Field;

public class ViewUtil {
    public static void show(String viewUrl){
        try {
            Stage primaryStage = new Stage() ;
            FXMLLoader fxmlLoader = new FXMLLoader(ViewUtil.class.getResource(viewUrl));
            Parent root = fxmlLoader.load();
            Object controller = fxmlLoader.getController();
            System.out.println("=========当前界面对应的类:============="+controller.getClass().getName());
            primaryStage.setTitle("Java180 接口训练");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
