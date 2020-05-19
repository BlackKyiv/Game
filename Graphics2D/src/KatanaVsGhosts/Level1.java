package KatanaVsGhosts;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.SortedMap;

public class Level1 extends BasicGameState {
    private int score = 0;
    private Graphics graphics;
    private final float coolDownTime = 1f;
    private Rectangle floor;
    private Hero hero;
    private Timer hit;
    private Timer shiftCooldown;
    private Rectangle hitArea;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Random random= new Random();
    private Rectangle coolDownGUI;
    private boolean dead = false;
    private Music music;
    private Music sound;

    @Override
    public void init(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        floor = new Rectangle(0, TestGameWorld.height - TestGameWorld.height/8, TestGameWorld.width, TestGameWorld.height/8);
        hero = new Hero(TestGameWorld.width/2, TestGameWorld.height - TestGameWorld.height/8-50);
        hit = new Timer(60);
        coolDownGUI = new Rectangle(TestGameWorld.width-1000,TestGameWorld.height-20,1000,20);
        hit.setTimer(0.1f);
        shiftCooldown = new Timer(60);
        shiftCooldown.setTimer(coolDownTime);
        hitArea = new Rectangle(hero.getCenterX() - 50, hero.getCenterY() - 30, 50, 50);
        spawnEnemies();
        music = new Music("sound\\music.wav");
        sound = new Music("sound\\blade.wav");
        music.loop();
        music.setVolume(0.5f);
        sound.setVolume(0.2f);

    }

    private void spawnEnemies(){
        for(int i = 0; i< 9; i++){
            enemies.add(new Enemy(random.nextInt(TestGameWorld.width), 50));
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        this.graphics = graphics;
        renderScore();
        renderInterior();


        if(!dead) {
            graphics.setColor(Color.red);
            graphics.fill(hero);
        }

        if(hit.isRunning()) hitRender(container);

        checkForIntersection();

        if(dead) gameOver(container);

        renderInterface();

    }

    private void renderInterface(){
        graphics.setColor(Color.red);
        graphics.fill(coolDownGUI);
        graphics.draw(new Rectangle(TestGameWorld.width-1000,TestGameWorld.height-20,1000,20));
        graphics.drawString("Shift: ", 0,TestGameWorld.height-20);
    }

    private void renderInterior(){
        graphics.setColor(Color.white);
        graphics.fill(floor);
    }

    private void renderScore(){
        graphics.setColor(Color.red);
        if(!dead)graphics.drawString("Ghosts killed: "+score, 50 ,50);
    }

    private void hitRender(GameContainer container){
        if(container.getInput().getMouseX()> hero.getMaxX()) {
            hitArea.setCenterX(hero.getCenterX() + 50);
            hitArea.setCenterY(hero.getCenterY() - 30);
        }
        else{
            hitArea.setCenterX(hero.getCenterX() - 50);
            hitArea.setCenterY(hero.getCenterY() - 30);
        }
        sound.play();
        graphics.setColor(Color.yellow);
        graphics.fill(hitArea);
    }

    private void checkForIntersection(){
        graphics.setColor(Color.blue);
        for(int a = 0; a<enemies.size();a++){
            graphics.fill(enemies.get(a));
            if(hitArea!=null && enemies.get(a).intersects(hitArea)){
                enemies.remove(a);
                score++;
            }

        }

        for(int i = 0; i<enemies.size(); i++){
            if(enemies.get(i) != null && enemies.get(i).intersects(hero)) dead = true;
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame stateBasedGame, int delta) throws SlickException {
        heroControl(container);
        hit.update();
        shiftCooldown.update();
        coolDownGUI.setWidth(shiftCooldown.getPassedTime()*(1000f/coolDownTime));

        for(int a = 0; a<enemies.size();a++){
            enemies.get(a).moveTo(hero);
        }
        if(!hit.isRunning()){
            hitArea.setCenterX(-100);
            hitArea.setCenterY(-100);
        }




    }

    private void heroControl(GameContainer container){
        if(!dead) {
            if (container.getInput().isKeyDown(Input.KEY_D)) {
                hero.moveRight();
            } else if (container.getInput().isKeyDown(Input.KEY_A)) {
                hero.moveLeft();
            }
            if (container.getInput().isKeyPressed(Input.KEY_Q) &&
                    !shiftCooldown.isRunning()) {

                hero.shiftLeft();
                shiftCooldown.restart();
                shiftCooldown.run();
            } else if (container.getInput().isKeyDown(Input.KEY_E) &&
                    !shiftCooldown.isRunning()) {

                hero.shiftRight();
                shiftCooldown.restart();
                shiftCooldown.run();
            }
            if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
                hero.jump();
            }

            if (container.getInput().isMousePressed(0)) {
                hit.restart();
                hit.run();
            }
        }

    }

    private void gameOver(GameContainer container) throws SlickException {
        graphics.drawString("Press R to Restart\n Your score is:"+score,TestGameWorld.width/2, TestGameWorld.height/2);
        for(int i = 0; i<enemies.size(); i++){
            enemies.get(i).stop();
        }
        if (container.getInput().isKeyPressed(Input.KEY_R)) {

            score = 0;
            enemies = new ArrayList<>();
            random= new Random();
            dead = false;
            spawnEnemies();
        }
    }

    @Override
    public int getID() {
        return 0;
    }
}
