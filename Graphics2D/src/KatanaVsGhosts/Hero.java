package KatanaVsGhosts;

import org.newdawn.slick.geom.Rectangle;

public class Hero extends Rectangle {
    private int speed = 10;

    public Hero(float x, float y) {
        super(x, y, 50, 50);
    }
    public void moveRight(){
        this.setCenterX(this.getCenterX()+speed);
    }
    public void moveLeft(){
        this.setCenterX(this.getCenterX()-speed);
    }
    public void jump(){
        //this.setCenterY(this.getCenterY()-100);
    }
    public void shiftLeft(){
        move(-300f, 0f);
    }
    public void shiftRight(){
         move(300f, 0f);
    }
    private void move(float x, float y){
        this.setCenterX(getCenterX() + x);
        this.setCenterY(getCenterY() + y);
    }



}
