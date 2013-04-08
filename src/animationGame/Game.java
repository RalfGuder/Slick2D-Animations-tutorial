package animationGame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {

    private Animation hero, movementUp, movementDown, movementLeft, movementRight, stillUp, stillDown, stillLeft, stillRight;
    private Image background;
    private char lastDirection;
    private float x, y;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final float SPEED = 0.1f;
    private static final int ANIMATIONSPEED = 500;

    public Game() {
        super("Slick2D Animations");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        background = new Image("images/snow.png");

        /**
         * Set the Image arrays needed for the animations.
         */
        Image[] animationUp = {new Image("images/hero20.png"), new Image("images/hero22.png")};
        Image[] animationDown = {new Image("images/hero00.png"), new Image("images/hero02.png")};
        Image[] animationLeft = {new Image("images/hero30.png"), new Image("images/hero32.png")};
        Image[] animationRight = {new Image("images/hero10.png"), new Image("images/hero12.png")};
        Image[] up = {new Image("images/hero21.png")};
        Image[] down = {new Image("images/hero01.png")};
        Image[] left = {new Image("images/hero31.png")};
        Image[] right = {new Image("images/hero11.png")};

        /**
         * Set the Animations needed.
         */
        stillUp = new Animation(up, ANIMATIONSPEED);
        stillDown = new Animation(down, ANIMATIONSPEED);
        stillLeft = new Animation(left, ANIMATIONSPEED);
        stillRight = new Animation(right, ANIMATIONSPEED);
        movementUp = new Animation(animationUp, ANIMATIONSPEED);
        movementDown = new Animation(animationDown, ANIMATIONSPEED);
        movementLeft = new Animation(animationLeft, ANIMATIONSPEED);
        movementRight = new Animation(animationRight, ANIMATIONSPEED);

        // The hero is looking down by default and is located in the center of the screen.
        hero = stillDown;
        x = WIDTH / 2;
        y = HEIGHT / 2;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        
        /**
         * If the hero is moving we have to deal with changing hero's position and his movement animations.
         */
        if (input.isKeyDown(Input.KEY_UP)) {
            y -= delta * SPEED;
            hero = movementUp;
            hero.update(delta);
            lastDirection = 'u';
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            y += delta * SPEED;
            hero = movementDown;
            hero.update(delta);
            lastDirection = 'd';
        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            x -= delta * SPEED;
            hero = movementLeft;
            hero.update(delta);
            lastDirection = 'l';

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            x += delta * SPEED;
            hero = movementRight;
            hero.update(delta);
            lastDirection = 'r';
        } 
        
        /**
         * If hero isn't moving he must be still so we have to change the animations.
         */
        else {
            switch (lastDirection) {
                case 'd':
                    hero = stillDown;
                    break;
                case 'u':
                    hero = stillUp;
                    break;
                case 'l':
                    hero = stillLeft;
                    break;
                case 'r':
                    hero = stillRight;
                    break;
            }
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw(0, 0);
        hero.draw((int) x, (int) y);
    }

    public static void main(String[] arguments) {
        try {
            AppGameContainer app = new AppGameContainer(new Game());
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}