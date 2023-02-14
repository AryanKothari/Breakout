package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.geometry.Point2D;

import java.awt.*;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;



/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Aryan Kothari
 */
public class Main extends Application {


    private Scene myScene;
    private Ball myBall;
    private Paddle myPaddle;
    private Group root;
    private Scoreboard myScoreboard;
    private List<Brick> myBricks;

    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start(Stage stage) {
        myScene = setupScene(Constants.SIZE, Constants.SIZE, Color.BLACK);
        stage.setScene(myScene);
        stage.setTitle(Constants.TITLE);
        stage.show();

        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(Constants.SECOND_DELAY), e -> step(Constants.SECOND_DELAY)));
        animation.play();
    }

    public Scene setupScene (int width, int height, Color background) {
        myPaddle = new Paddle();
        myBall = new Ball();
        myScoreboard = new Scoreboard();
        myBricks = new ArrayList<>();


        root = new Group();
        root.getChildren().add(myPaddle.getView());
        root.getChildren().add(myBall.getView());
       root.getChildren().add(myScoreboard.getView());
        loadLevel(Constants.STARTING_LEVEL);

        myScene = new Scene(root, width, height, background);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }

    private void step(double elapsedTime) {
        if(myScoreboard.isGameActive()) {
            myScoreboard.removeInstructions();
            moveBall(elapsedTime);
            checkCollisions(elapsedTime);
            updateGame();
        }
    }

    public void checkCollisions(double elapsedTime) {
        //check ball collision with paddle
        if (isIntersecting(myBall, myPaddle)) {
            myBall.paddleCollision(myPaddle);
        }

        //check ball collision with walls
        myBall.checkWallCollision(myScene.getWidth(), myScene.getHeight(), myScoreboard.getScoreboard().getHeight());

        //check ball collision with brick
        for (int i = 0; i < myBricks.size(); i++) {
            if(myBricks.get(i).isActive()) {
                if (isIntersecting(myBall, myBricks.get(i))) {
                    myBall.setVelocity(new Point2D(-myBall.getVelocity().getX(), -1*myBall.getVelocity().getY()));
                    myBricks.get(i).setActive(false);
                    root.getChildren().remove(myBricks.get(i).getView());
                    myScoreboard.incrementScore();
                    myScoreboard.setActiveBricks(myScoreboard.getActiveBricks()-1);
                }
            }
        }

    }
    public void updateGame() {
        //check if ball went out of bounds
        if(myBall.bottomWallCollision()) {
            myBall.reset();
            myScoreboard.updateLives(myScoreboard.getLivesLeft()-1);
        }

        //check if all bricks are cleared from screen
        if(myScoreboard.getActiveBricks() == 0) {
            if(myScoreboard.getLevel() == 3) {
                endGame("WON");
            } else {
                loadLevel(myScoreboard.getLevel()+1);
            }
        }

        //check if player has lost all lives
        if(myScoreboard.getLivesLeft() == 0) {
            endGame("LOST");
        }

    }
    public void moveBall(double elapsedTime) {
        myBall.move(elapsedTime);
    }
    private boolean isIntersecting (Circle a, Rectangle b) {
        return (a.getCenterX() + a.getRadius() >= b.getX() && a.getCenterX() - a.getRadius() <= b.getX() + b.getWidth() &&
                a.getCenterY() + a.getRadius() >= b.getY() && a.getCenterY() - a.getRadius() <= b.getY() + b.getHeight());
    }
    private void endGame(String status) {
        root.getChildren().removeAll(myBall, myPaddle);
        for(int i = 0; i<myBricks.size(); i++) {
            root.getChildren().remove(myBricks.get(i).getView());
        }
        myScoreboard.showGameOver(status);
    }
    private void loadLevel(int level) {
        myBall.reset();
        for (int i = 0; i < myBricks.size(); i++) {
            myBricks.get(i).setActive(false);
            root.getChildren().remove(myBricks.get(i).getView());
        }

        myScoreboard.setActiveBricks(0);
        myScoreboard.loadLevel(level, myBricks);
        initializeBricks();

    }
    private void initializeBricks() {
        for(int i = 0; i<myBricks.size(); i++) {
            if(myBricks.get(i).isActive()) {
                root.getChildren().add(myBricks.get(i).getView());
            }
        }
    }
    private void handleKeyInput(KeyCode code) {
        if(myScoreboard.isGameActive()) {
            myPaddle.handleKeyInput(code);
        }
        switch (code) {
            case L -> myScoreboard.updateLives(myScoreboard.getLivesLeft()+1);
            case R -> myBall.reset();
            case S -> myBall.setVelocity(new Point2D(myBall.getVelocity().getX()*1/2, myBall.getVelocity().getY()*1/2));
            case F -> myBall.setVelocity(new Point2D(myBall.getVelocity().getX()*2, myBall.getVelocity().getY()*2));
            case X -> myPaddle.setWidth(myPaddle.getWidth()+5);
            case DIGIT1 -> loadLevel(1);
            case DIGIT2 -> loadLevel(2);
            case DIGIT3 -> loadLevel(3);
            case SPACE -> myScoreboard.setGameActive(true);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
