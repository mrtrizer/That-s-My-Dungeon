/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.creature;

import game.main.Dungeon;
import static game.main.Game.fontRender;
import java.util.Random;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Юрий Whizzpered
 */
public class Player extends Creature {

    boolean hold;
    Random r = new Random();

    @Override
    public void init(Object... args) {
        x = (Double) args[0];
        y = (Double) args[1];
        hp = (int) args[2];
        dmg = (int) args[3];
        setTimer("base", 200);
        setTimer("kick", 60);
    }

    @Override
    public void tick() {
        baseTick();
        //statictick();
        move(ex, ey);
        if (ex == x && ey == y) {
            reset();
        }
    }

    public void reset() {
        dung.flag = null;
        ex = 0;
        ey = 0;
    }

    public void statictick() {
        super.baseTick();
        if (!hold && getTimer("base").is()) {
            ex = r.nextInt(Display.getWidth() - 256) + 128;
            hold = true;
        }

        if (Math.abs(ex - x) < 6 && hold) {
            hold = false;
            getTimer("base").start();
        }

        move(ex, (int) y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) (x), (int) (y), 20, 20);
    }

    public void gameRender(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) (Display.getWidth() / 2), (int) (Display.getHeight() / 2), 20, 20);
    }

    public void focussmth(Raider r) {
        if (focus != null) {
            focus.focused = false;
        }
        focus = r;
        focus.focused = true;
    }

    @Override
    public void text() {
        fontRender.drawString(x + "   " + y, 20, 20, Color.white);
        fontRender.drawString(hp + "   " + dmg, 20, 5, Color.white);
    }
}
