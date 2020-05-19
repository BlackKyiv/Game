package Platformer;


import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static org.newdawn.slick.Input.*;

public class LevelOne extends BasicGameState {
    private Guy guy;
    private float timeCoeff = 1;
    private Rectangle terrain;
    private Rectangle platform;
    private Rectangle platform1;
    private Rectangle wall1;
    private Rectangle wall2;
    private Rectangle platform3;

    private Music musicbg;


    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        guy = new Guy(30, 100, 30, 50);
        platform = new Rectangle(0, SetupClass.height-100, SetupClass.width,200);
        platform1 = new Rectangle(SetupClass.width/2, SetupClass.height - 500, 100, 300);
        wall1 = new Rectangle(-100, 0, 100, SetupClass.height);
        wall2 = new Rectangle(SetupClass.width, 0, 100, SetupClass.height);
        platform3 = new Rectangle(0, platform.getY()-100, 100, 100);
        try {


        musicbg = new Music("C:\\Users\\atcat\\Documents\\Game\\Graphics2D\\src\\sound\\hotline.wav");

        musicbg.loop();
            musicbg.setVolume(0.1f);
         }catch (Exception e){}
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

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
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int deltaSeconds) throws SlickException {
        guy.update(timeCoeff);
        guy.checkForCollision(platform);
        guy.checkForCollision(platform1);
        guy.checkForCollision(wall1);
        guy.checkForCollision(wall2);
        guy.checkForCollision(platform3);

        if(gameContainer.getInput().isKeyDown(KEY_LSHIFT)){
            timeCoeff = 0.4f;
        }
        else timeCoeff =1;

        guy.controls(gameContainer);
    }
}
