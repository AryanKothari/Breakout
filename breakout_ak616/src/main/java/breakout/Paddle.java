package breakout;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {

    private int speed;
    private boolean disableLeft;
    private boolean disableRight;

    public Paddle() {
        super(Constants.PADDLE_STARTING_POSX, Constants.PADDLE_STARTING_POSY,
                Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);

        this.speed = Constants.PADDLE_SPEED;
        this.setFill(Color.LIGHTSTEELBLUE);
        this.disableLeft = false;
        this.disableRight = false;
        this.setArcHeight(10);
        this.setArcWidth(10);
    }

    public Rectangle getView() {
        return this;
    }
    public void handleKeyInput(KeyCode code) {
            switch (code) {
                case RIGHT -> this.setX(this.getX() + speed);
                case LEFT -> this.setX(this.getX() - speed);
            }
    }
}

