package KatanaVsGhosts;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.util.Random;

public class Enemy extends Circle {
    private int speed = 2;
    private boolean stopped = false;
    private Random r = new Random();

    public Enemy(float centerPointX, float centerPointY) {
        super(centerPointX, centerPointY, 0);
        speed = r.nextInt(5)+2;
        this.setRadius(10+speed*2);
    }

    public void moveTo(Shape shape){
        if(!stopped) {
            if (shape.getCenterX() < getCenterX()) {
                move(-speed, 0);
            } else {
                move(speed, 0);
            }

            if (shape.getCenterY() > getCenterY()) {
                move(0, speed);
            }
        }
    }

    private void move(float x, float y){
        this.setCenterX(getCenterX() + x);
        this.setCenterY(getCenterY() + y);
    }
    public void stop(){
        speed = 0;
        stopped = true;
    }



}
