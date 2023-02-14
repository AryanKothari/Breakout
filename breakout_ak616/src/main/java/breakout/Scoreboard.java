package breakout;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files


public class Scoreboard {
    private int livesLeft;
    private int score;
    private int level;
    private int activeBricks;
    Text livesLeftLabel;
    Text scoreLabel;
    Text levelLabel;
    Text gameOverLabel;
    Text instructionsLabel;
    private Group root;
    private Rectangle scoreboard;
    private Rectangle gameOver;
    private Rectangle instructions;

    private boolean gameActive;


    public Scoreboard() {
        gameActive = false;
        scoreboard = new Rectangle(0, 0, 800, 20);
        scoreboard.setFill(Color.PURPLE);
        this.livesLeft = Constants.STARTING_LIVES;
        this.score = Constants.STARTING_SCORE;
        this.level = Constants.STARTING_LEVEL;
        livesLeftLabel = new Text(50, 15, "Lives left: " + this.livesLeft);
        scoreLabel = new Text(livesLeftLabel.getX() + 300, 15, "Score: " + this.score);
        levelLabel = new Text(livesLeftLabel.getX() + 600, 15, "Level: " + this.level);

        instructions = new Rectangle(150, Constants.SIZE / 2 + 20, 500, 200);
        instructions.setFill(Color.WHITE);
        instructionsLabel = new Text(instructions.getX() + 10, instructions.getY() + 30, "Rules: \n - Destroy the blocks to win. \n - You have 3 lives. \n - There are 3 levels total \n - Don't be bad at the game \n \n \n Click SPACEBAR to start playing");
        instructionsLabel.setScaleX(1); instructionsLabel.setScaleY(1);

        root = new Group();

    }

    public boolean isGameActive() {
        return gameActive;
    }
    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
    }
    public int getLivesLeft() {
        return livesLeft;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getActiveBricks() {
        return activeBricks;
    }
    public void setActiveBricks(int activeBricks) {
        this.activeBricks = activeBricks;
    }
    public Rectangle getScoreboard() {
        return scoreboard;
    }
    public void setScoreboard(Rectangle scoreboard) {
        this.scoreboard = scoreboard;
    }
    public Node getView() {
        root.getChildren().addAll(scoreboard, livesLeftLabel, scoreLabel, levelLabel, instructions, instructionsLabel);
        return root;
    }


    public void updateLives(int lives) {
            this.livesLeft = lives;
            livesLeftLabel.setText("Lives left: " + this.livesLeft);
    }
    public void incrementScore() {
        this.score += 1;
        scoreLabel.setText("Score: " + this.score);
    }

    public Node removeInstructions() {
        root.getChildren().removeAll(instructions, instructionsLabel);
        return root;
    }

    public List<Brick> loadLevel(int level, List<Brick> myBricks) {
        this.level = level;
        levelLabel.setText("Level: " + this.level);

        List<Object> rowsObj = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/aryankothari/Desktop/CS308/breakout_ak616/src/main/resources/level_0" + level + ".txt"))) {
            rowsObj = Arrays.asList(br.lines().toArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int lines = 0; lines < rowsObj.size(); lines++) {
            String temp = rowsObj.get(lines).toString();
            int[] results = convertToIntArray(temp);
            for (int j = 0; j < results.length; j++) {
                if(results[j] != 0) {
                    myBricks.add(new Brick(j*(Constants.BRICK_DEFAULT_WIDTH+10)+40,
                            +lines*(Constants.BRICK_DEFAULT_HEIGHT+10) + 50,
                            Constants.BRICK_DEFAULT_WIDTH, Constants.BRICK_DEFAULT_HEIGHT, results[j]));
                    activeBricks++;
                }
            }
        }
        return myBricks;
    }
    public Node showGameOver(String winStatus) {
        root.getChildren().removeAll(scoreboard, livesLeftLabel, scoreLabel, levelLabel);
        gameOver = new Rectangle(Constants.SIZE / 2 - 300 / 2, Constants.SIZE / 2 - 300 / 2, 300, 300);
        gameOver.setFill(Color.RED);
        gameOverLabel = new Text(gameOver.getX() + gameOver.getWidth()/2-25, gameOver.getY() + gameOver.getHeight()/2, "YOU " + winStatus);
        scoreLabel = new Text(gameOverLabel.getX(), gameOverLabel.getY() + 50, "SCORE: " + score);
        gameOverLabel.setScaleX(3); gameOverLabel.setScaleY(3);
        scoreLabel.setScaleX(3); scoreLabel.setScaleY(3);
        root.getChildren().addAll(gameOver, gameOverLabel, scoreLabel);

        return root;
    }
    private int[] convertToIntArray(String test) {
        String[] items = test.split(" ");

        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
                System.out.println("ERROR");
                //NOTE: write something here if you need to recover from formatting errors
            }
        }
        return results;
    }
}
