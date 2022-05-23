package com.example.snake2;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class ClassicMode {

    public int speed = 5;
    public Pane classicPane;
    public int foodX = 0;
    public int foodY = 0;
    Circle circle = new Circle(0, 0, 15, Color.RED);


    public void startGame(Button classicModeStartButton, Button backFromClassicModeToGameModes, Pane classicModePane, VBox classicGameVBox) throws InterruptedException {
        classicPane = classicModePane;
        classicGameVBox.getChildren().remove(classicModeStartButton);
        classicGameVBox.getChildren().remove(backFromClassicModeToGameModes);
        createFood(classicPane);
        Snake.createNextPartOfSnake(classicModePane);
        Snake.createNextPartOfSnake(classicModePane);
        Snake.createNextPartOfSnake(classicModePane);
        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    Snake.snakeMove();
                    return;
                }

                if (now - lastTick > 1000000000 / 5) {
                    lastTick = now;
                    Snake.snakeMove();
                    didSnakeEatFood(classicPane);
                    Snake.didSnakeTouchThemselves();
                    Snake.didSnakeTouchTheBorder();
                    Snake.didTheUserLose(classicModePane);
                }
            }
            }.start();
        }

    public void createFood(Pane classicPane) {
        start: while (true) {
            Random rand = new Random();
            foodX = rand.nextInt(516);
            foodY = rand.nextInt(486);

            for (Circle c : Snake.listOfPartsOfSnake) {
                if (foodX < 30 ||
                        foodY < 30 ||
                        c.getCenterX() - foodX > 30 ||
                        c.getCenterX() - foodX < -30 ||
                        c.getCenterY() - foodY > 30 ||
                        c.getCenterY() - foodY < -30) {
                    continue start;
                }
            }
            circle.setCenterX(foodX);
            circle.setCenterY(foodY);
            classicPane.getChildren().add(circle);
            speed++;
            break;
        }
    }

    public void didSnakeEatFood(Pane classicPane) {
        if ((foodX - Snake.listOfPartsOfSnake.get(0).getCenterX() < 30
                && foodX - Snake.listOfPartsOfSnake.get(0).getCenterX() > -30)
                && (foodY - Snake.listOfPartsOfSnake.get(0).getCenterY() < 30
                && foodY - Snake.listOfPartsOfSnake.get(0).getCenterY() > -30)) {
            classicPane.getChildren().remove(circle);
            createFood(classicPane);
            Snake.createNextPartOfSnake(classicPane);
        }
    }
}

