/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.creature;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Юрий
 */
public class Flag {

    public double x, y;

    public Flag(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.drawRect((int) x, (int) y, 10, 20);
    }
}
