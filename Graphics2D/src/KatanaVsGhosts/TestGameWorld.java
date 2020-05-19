package KatanaVsGhosts;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class TestGameWorld extends StateBasedGame {
    public final static int width = 1280;
    public final static int height = 720;

    public TestGameWorld(String name) {
        super(name);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer a = new AppGameContainer(new TestGameWorld("Katana vs Ghosts"));

        a.setDisplayMode(width, height, false);
        a.setTargetFrameRate(60);
        a.start();
    }

    public void initStatesList(GameContainer container) throws SlickException{
        this.addState(new Level1());

    }


}
