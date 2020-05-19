package Platformer;


import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelOne extends BasicGameState {
    private Guy guy;
    private Rectangle terrain;
    private Rectangle platform;
    private Rectangle platform1;
    private Rectangle platform2;
    private Rectangle platform3;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        guy = new Guy(50, 50, 25, 25);
        terrain = new Rectangle(0, SetupClass.height-100, SetupClass.width, 100);
        platform = new Rectangle(0, SetupClass.height-100, SetupClass.width,20);
        platform1 = new Rectangle(SetupClass.width/2, SetupClass.height - 500, 100, 300);
        platform2 = new Rectangle(0, 0, 20, SetupClass.height);
        platform3 = new Rectangle(0, platform.getY()-100, 100, 100);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.darkGray);
        graphics.fill(terrain);

        graphics.setColor(Color.green);
        graphics.fill(platform);
        graphics.setColor(Color.red);
        graphics.fill(platform1);

        graphics.fill(platform3);


        graphics.setColor(Color.white);

        graphics.fill(guy);
        graphics.drawString("Vertical Speed:"+Math.abs(guy.getSpeedY())+" m/s", 50,50);
        graphics.drawString("Landed:"+guy.isLanded(), 50,70);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        guy.update();
        guy.checkForCollision(platform);
        guy.checkForCollision(platform1);
        guy.checkForCollision(platform2);
        guy.checkForCollision(platform3);

        guy.controls(gameContainer);
    }
}
