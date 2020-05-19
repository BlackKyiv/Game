package Platformer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SetupClass extends StateBasedGame {

    public final static int width = 1280;
    public final static int height = 720;
    public final static int fps = 60;

    public SetupClass(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new LevelOne());
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer a = new AppGameContainer(new SetupClass("Platformer"));

        a.setDisplayMode(width, height, false);
        a.setTargetFrameRate(fps);
        a.start();
    }
}
