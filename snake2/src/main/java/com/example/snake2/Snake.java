package com.example.snake2;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public abstract class Snake {
    public static List<Circle> listOfPartsOfSnake = new ArrayList<>();
    public static boolean gameOver = false;

    public static void createNextPartOfSnake(Pane classicModePane) {
        if(!listOfPartsOfSnake.isEmpty()){
            Integer previousCircleId = listOfPartsOfSnake.size()-1;
            double previousCircleX = listOfPartsOfSnake.get(previousCircleId).getCenterX();
            double previousCircleY = listOfPartsOfSnake.get(previousCircleId).getCenterY();
            Circle circle = new Circle(previousCircleX+30, previousCircleY, 15, Color.GREEN);
            listOfPartsOfSnake.add(circle);
            classicModePane.getChildren().add(circle);
        } else {
            Circle circle = new Circle(15, Color.YELLOW);
            circle.setCenterX(198);
            circle.setCenterY(199);
            listOfPartsOfSnake.add(circle);
            classicModePane.getChildren().add(circle);
        }
    }

    public static void snakeMove() {
        for(int i = listOfPartsOfSnake.size()-1; i > 0; i--) {
            listOfPartsOfSnake.get(i).setCenterX(listOfPartsOfSnake.get(i-1).getCenterX());
            listOfPartsOfSnake.get(i).setCenterY(listOfPartsOfSnake.get(i-1).getCenterY());
        }
        switch (HelloController.direction) {
            case left -> {
                listOfPartsOfSnake.get(0).setCenterX(listOfPartsOfSnake.get(0).getCenterX()-30);
            }
            case right -> {
                listOfPartsOfSnake.get(0).setCenterX(listOfPartsOfSnake.get(0).getCenterX()+30);
            }
            case up -> {
                listOfPartsOfSnake.get(0).setCenterY(listOfPartsOfSnake.get(0).getCenterY()-30);
            }
            case down -> {
                listOfPartsOfSnake.get(0).setCenterY(listOfPartsOfSnake.get(0).getCenterY()+30);
            }
        }
    }

    public static void didSnakeTouchThemselves(){
        for (int i = 1; i < listOfPartsOfSnake.size(); i++) {
            if (listOfPartsOfSnake.get(0).getCenterX() == listOfPartsOfSnake.get(i).getCenterX() && listOfPartsOfSnake.get(0).getCenterY() == listOfPartsOfSnake.get(i).getCenterY()) {
                gameOver = true;
            }
        }
    }

    public static void didSnakeTouchTheBorder(){
        if(listOfPartsOfSnake.get(0).getCenterX() < 0 || listOfPartsOfSnake.get(0).getCenterY() < 0){
            gameOver = true;
        }
        if(listOfPartsOfSnake.get(0).getCenterX() > 546 || listOfPartsOfSnake.get(0).getCenterY() > 516){
            gameOver = true;
        }
    }

    public static void didTheUserLose(Pane classicModePane){
        if(gameOver) {
            for(int i = 0; i < listOfPartsOfSnake.size(); i++){
                Circle cirlce = listOfPartsOfSnake.get(i);
                classicModePane.getChildren().remove(cirlce);
            }
            classicModePane.setStyle("-fx-background-color: RED");
        }
    }

}
