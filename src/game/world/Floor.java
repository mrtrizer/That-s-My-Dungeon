/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Юрий
 */
public class Floor {

    public int w = 16, h = 16;
    public Block[][] dung = new Block[w][h];

    public void init() throws SlickException {
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                dung[x][y] = new Block();
                dung[x][y].init();
            }
        }
    }

    public void render(Graphics g) {
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                dung[x][y].render(g, x, y);
            }
        }
    }

}
