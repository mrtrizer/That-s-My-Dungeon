/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Юрий Whizzpered
 */
public class Block {

    public static int w = 64, h = 64;
    Image sprite;

    public void init() throws SlickException {
        sprite = new Image("res/textures/floor.png");
    }
    
    public void render(Graphics g, int cx, int cy) {
        sprite.draw(cx * w, cy * h);
    }
}
