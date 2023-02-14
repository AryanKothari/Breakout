package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.awt.*;
import javafx.geometry.Point2D;
import java.lang.Object;
import java.util.Random;


public class Ball extends Circle {

    private Point2D velocity;

    public Ball() {
        super(Constants.BALL_DEFAULT_POSX, Constants.BALL_DEFAULT_POSY, Constants.BALL_SIZE,Color.LIGHTSTEELBLUE);
        this.velocity = Constants.BALL_DEFAULT_VELOCITY;
    }

    public Point2D getVelocity() {
        return velocity;
    }
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }
    public Circle getView() {
        return this;
    }

    public void move (double elapsedTime) {
        this.setCenterX(this.getCenterX() + velocity.getX() * elapsedTime);
        this.setCenterY(this.getCenterY() + velocity.getY() * elapsedTime);
    }

    public void checkWallCollision( double screenWidth, double screenHeight, double screenboardHeight) {
        if (this.getCenterX() - this.getRadius() <= 0 || this.getCenterX() + this.getRadius() >= screenWidth) {
            velocity = new Point2D(-velocity.getX(), velocity.getY());
        }
        if (this.getCenterY() - this.getRadius() <= screenboardHeight) {
            velocity = new Point2D(velocity.getX(), -velocity.getY());
        }
    }

    public boolean bottomWallCollision() {
        return (this.getCenterY() - this.getRadius() >= Constants.SIZE);
    }

    public void paddleCollision(Paddle paddle) {
        // inspiration taken from https://www.youtube.com/watch?v=ITkuUIMT0jI
        double theta = calculateNewVelocityAngle(paddle);
        double newVx = (-Math.cos(theta)) * Constants.BALL_SPEED;
        double newVy = Math.abs(Math.sin(theta)) * Constants.BALL_SPEED;

        double oldSign = Math.signum(velocity.getY());
        velocity = new Point2D(newVx, newVy * (-1.0 * oldSign));
    }



    private double calculateNewVelocityAngle(Paddle paddle) {
        double relativeIntersectX = (paddle.getX() + (paddle.getWidth()/2)) - (this.getCenterX() - this.getRadius());
        double normalIntersectX = relativeIntersectX / (paddle.getHeight()/2);
        double theta = normalIntersectX * Constants.MAX_ANGLE;
        return Math.toRadians(theta);
    }
    public void reset() {
        this.setCenterX(Constants.SIZE/2);
        this.setCenterY(Constants.SIZE/2);
        velocity = Constants.BALL_DEFAULT_VELOCITY;
    }


}
