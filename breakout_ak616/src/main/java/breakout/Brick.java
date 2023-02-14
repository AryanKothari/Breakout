package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {
    private boolean active;
    private Color color;

    public Brick(int x, int y, int width, int height, int ID) {
        super(x, y, width, height);
        this.active = true;
        this.setFill(Color.WHITE);
        this.setArcHeight(20);
        this.setArcWidth(20);
        this.setColor(ID);
        this.setStroke(Color.WHITE);
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Rectangle getView() {
        return this;
    }
    public void setColor(int type) {
        switch (type) {
            case 1 -> this.setFill(Color.BLUE);
            case 2 -> this.setFill(Color.RED);
            case 3 -> this.setFill(Color.WHITE);
            case 4 -> this.setFill(Color.ORANGE);
            case 5 -> this.setFill(Color.GREEN);
        }
    }


    
}
