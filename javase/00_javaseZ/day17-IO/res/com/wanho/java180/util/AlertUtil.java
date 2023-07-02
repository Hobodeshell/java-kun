package com.wanho.java180.util;

import javafx.scene.control.Alert;

public class AlertUtil {
    public static void alertInfo(String data){
        alert(data,Alert.AlertType.INFORMATION);
    }

    private static void alert(String data, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType) ;
        alert.setContentText(data);
        alert.show();
    }

    public static void alertError(String data){
        alert(data,Alert.AlertType.ERROR);
    }

    public static void alertConfirm(String data){
        alert(data,Alert.AlertType.CONFIRMATION);
    }
}
