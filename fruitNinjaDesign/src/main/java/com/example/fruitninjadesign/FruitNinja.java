package com.example.fruitninjadesign;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FruitNinja extends Application {
//    int timeToPlayLivesAgain=3700;
//    boolean gameOverFlag=false;
    @Override
    public void start(Stage stage) throws FileNotFoundException {


        Pane pane = new Pane();
        /*
         Setting The Background
        */
        FileInputStream fileInputStream_background = new FileInputStream("FinalProject//background1.jpg");
        Image im = new Image(fileInputStream_background);
        ImageView imageView = new ImageView(im);


        /*
         Creating Lives Viewer
        */
        Label label_misses = new Label();

        // Set Lives Label Size & Graphics //
        label_misses.setBackground(Background.EMPTY);
        FileInputStream fileInputStream_lives = new FileInputStream("FinalProject//lives.png");
        Image image_lives = new Image(fileInputStream_lives);
        ImageView imageView_lives = new ImageView(image_lives);
        imageView_lives.setFitWidth(100);
        imageView_lives.setFitHeight(50);
        label_misses.setGraphic(imageView_lives);

        // Setting Graphics Of Number Of Lives Remaining //
        Circle circle_1stmiss = new Circle(0,0,24, Color.GREEN);
        circle_1stmiss.setStroke(Color.LIGHTGREEN);
        circle_1stmiss.setStrokeWidth(3);
        Circle circle_2ndmiss = new Circle(0,0,24, Color.GREEN);
        circle_2ndmiss.setStroke(Color.LIGHTGREEN);
        circle_2ndmiss.setStrokeWidth(3);
        Circle circle_3rdmiss = new Circle(0,0,24, Color.GREEN);
        HBox hBox_misses = new HBox(10);
        circle_3rdmiss.setStroke(Color.LIGHTGREEN);
        circle_3rdmiss.setStrokeWidth(3);

        // Adding 3 lives Horizontally //
        hBox_misses.getChildren().addAll(circle_1stmiss, circle_2ndmiss, circle_3rdmiss);

        // Combining 3 Lives + Lives Label Vertically //
        VBox vBox_misses = new VBox(10);
        vBox_misses.getChildren().addAll(label_misses, hBox_misses);


        /*
        Creating Score Viewer
        */
        Label label_score = new Label();

        // Set Score Label Size & Graphics //
        label_score.setBackground(Background.EMPTY);
        FileInputStream fileInputStream_score = new FileInputStream("FinalProject//score.png");
        Image image_score = new Image(fileInputStream_score);
        ImageView imageView_score = new ImageView(image_score);
        imageView_score.setFitWidth(100);
        imageView_score.setFitHeight(50);
        label_score.setGraphic(imageView_score);



        // Creating Score Counter //
        Label label_scoreCount = new Label("0");
        label_scoreCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,26));
        label_scoreCount.setTextFill(Color.WHITE);

        FileInputStream gameoverimagepath = new FileInputStream("FinalProject/GameOver.jpg");
        Image gameoverimage = new Image(gameoverimagepath);
        // BackgroundImage gameOverBackGroundImage = new BackgroundImage(gameoverimage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT ,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        // Background gameoverbackground = new Background(gameOverBackGroundImage);

        BorderPane gameOver = new BorderPane();

        Scene gameOverScene = new Scene(gameOver,1000,600);

        ImageView img = new ImageView(gameoverimage);
        img.setFitWidth(gameOverScene.getWidth());
        img.setFitHeight(gameOverScene.getHeight());
        HBox gameOverControl = new HBox();
        gameOverControl.setAlignment(Pos.BOTTOM_CENTER);
        gameOverControl.getChildren().addAll(imageView_score, label_scoreCount);
        gameOver.setLeft(gameOverControl);
        gameOver.setCenter(img);


        /*
        Combining Score Label + Score Counter Vertically
        */
        VBox vBox_score = new VBox(8);
        vBox_score.getChildren().addAll(label_score, label_scoreCount);

        /*
        Combining Lives Viewer + Score Viewer Horizontally
        */
        HBox hBox_combined = new HBox(690);
        hBox_combined.getChildren().addAll(vBox_score,vBox_misses);
        hBox_combined.setPadding(new Insets(15,20,20,20));


        double [] seconds = new double[4];
        for (int i = 0 ; i < seconds.length ; i++){
            seconds[i] = 0.7 + 5 * Math.random();
        }
        /*
         Adding Watermelons
         */

        KeyFrame watermelonTime = new KeyFrame(Duration.seconds(seconds[0]), event -> {
            try {
                NinjaFruit watermelon = create_fruit_stream(new FileInputStream("FinalProject//watermelon.png"),
                        new FileInputStream("FinalProject//halfwatermelon.png"),
                        new FileInputStream("FinalProject//halfwatermelon2.png"));
                watermelon.setEventHandling();
                watermelon.fruit_paths[0].setOnFinished(e-> setLivesColor(circle_1stmiss, circle_2ndmiss, circle_3rdmiss, stage, gameOverScene));
                pane.getChildren().add(watermelon);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Timeline timeline_watermelon = new Timeline(watermelonTime);
        timeline_watermelon.setCycleCount(Timeline.INDEFINITE);
        timeline_watermelon.play();


        /*
        Adding Apples
         */

        KeyFrame appleTime = new KeyFrame(Duration.seconds(seconds[1]), event -> {
            try {
                NinjaFruit apple = create_fruit_stream(new FileInputStream("FinalProject//apple.png"),
                        new FileInputStream("FinalProject//half_apple.png"),
                        new FileInputStream("FinalProject//half_apple2.png"));
                apple.setEventHandling();
                apple.fruit_paths[0].setOnFinished(e-> setLivesColor(circle_1stmiss, circle_2ndmiss, circle_3rdmiss, stage, gameOverScene));
                pane.getChildren().add(apple);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Timeline timeline_apple = new Timeline(appleTime);
        timeline_apple.setCycleCount(Timeline.INDEFINITE);
        timeline_apple.play();

        /*
        Adding Pineapples
         */

        KeyFrame pineappleTime = new KeyFrame(Duration.seconds(seconds[2]), event -> {
            try {
                NinjaFruit pineapple = create_fruit_stream(new FileInputStream("FinalProject//pineapple.png"),
                        new FileInputStream("FinalProject//top_pineapple.png"),
                        new FileInputStream("FinalProject//bottom_pineapple.png"));
                pineapple.setEventHandling();
                pineapple.fruit_paths[0].setOnFinished(e-> setLivesColor(circle_1stmiss, circle_2ndmiss, circle_3rdmiss, stage, gameOverScene));
                pane.getChildren().add(pineapple);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Timeline timeline_pineapple = new Timeline(pineappleTime);
        timeline_pineapple.setCycleCount(Timeline.INDEFINITE);
        timeline_pineapple.play();



        /*
        Adding Lemons
         */

        KeyFrame lemonTime = new KeyFrame(Duration.seconds(seconds[3]), event -> {
            try {
                NinjaFruit lemon = create_fruit_stream(new FileInputStream("FinalProject//lemon.png"),
                        new FileInputStream("FinalProject//half_lemon.png"),
                        new FileInputStream("FinalProject//half_lemon.png"));
                lemon.setEventHandling();
                lemon.fruit_paths[0].setOnFinished(e-> setLivesColor(circle_1stmiss, circle_2ndmiss, circle_3rdmiss, stage, gameOverScene));
                pane.getChildren().add(lemon);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Timeline timeline_lemon = new Timeline(lemonTime);
        timeline_lemon.setCycleCount(Timeline.INDEFINITE);
        timeline_lemon.play();

//        Timeline lost = new Timeline(new KeyFrame(Duration.millis(100),e->{
//            // if player lost , reset the variables to the default
//            timeToPlayLivesAgain += 100;
//            if(gameOverFlag){
//                timeToPlayLivesAgain=0;
//                gameOverFlag=false;
//                pane.getChildren().removeAll(pane.getChildren());
//                stage.setScene(gameOverScene);
//                NinjaFruit.score=0;
//
//                pane.getChildren().addAll(imageView, hBox_combined); //re add the needed views to the pane again
//                label_scoreCount.setText(NinjaFruit.score + ""); //show the score after reset
//
//            }
//            if((stage.getScene()==gameOverScene)){// reset the color of lives
//                circle_1stmiss.setFill(Color.GREEN);
//                circle_2ndmiss.setFill(Color.GREEN);
//            }
//
//        }));
//        lost.setCycleCount(Timeline.INDEFINITE);
//        lost.play();

        /*
        Show Score
         */
        Timeline score_show = new Timeline(new KeyFrame(Duration.millis(500),
                e -> label_scoreCount.setText(NinjaFruit.score + "")));
        score_show.setCycleCount(-1);
        score_show.play();

        /*
        Adding Background, Lives Viewer, Score Viewer to a Pane
        */
        // setScoreCount(watermelon.getScore, label_scoreCount);
        pane.getChildren().addAll(imageView, hBox_combined);
        label_scoreCount.setText(NinjaFruit.score + "");
        /*
        Adding Pane to Scene
        */
        Scene scene = new Scene(pane, 1000, 600);
        imageView.setFitHeight(scene.getHeight());
        imageView.setFitWidth(scene.getWidth());

        /*
        Creating Stage
        */
        stage.setResizable(false);
        stage.setTitle("Fruit Ninja");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public NinjaFruit create_fruit_stream(FileInputStream whole_fruit,
                                          FileInputStream half_1,
                                          FileInputStream half_2) throws FileNotFoundException {

        return new NinjaFruit(whole_fruit, half_1, half_2);
    }

    public void setLivesColor(Circle circle, Circle circle1, Circle circle2, Stage stage, Scene scene) {
        if (circle1.getFill().equals(Color.RED)) {
            circle2.setFill(Color.RED);
            stage.setScene(scene);
            System.out.println("Your Score is " + NinjaFruit.score);
        }
        else if (circle.getFill().equals(Color.RED)){
            circle1.setFill(Color.RED);
        }
        else {
            circle.setFill(Color.RED);
        }
    }
}