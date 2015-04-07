/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.creature;

import game.main.Dungeon;
import game.object.GameObject;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import utils.Timer;

/**
 *
 * @author Юрий Whizzpered
 */
public class Creature extends GameObject{
    public Dungeon dung;
    public Creature focus;
    public abstract int getWidth();
    public abstract int getHeight();
    public int hp, maxhp, dmg, gold;
    ArrayList<Timer> timers = new ArrayList<>();
    ArrayList<String> timnames = new ArrayList<>();
    public boolean focused;

    public Creature()       
    {
        super(0,0);
    }
    
    public void setTimer(String name, int tim) {
        timers.add(new Timer(tim));
        timnames.add(name);
    }

    public Timer getTimer(String name) {
        return timers.get(timnames.indexOf(name));
    }

    public void init(Object... args) {
        x = (Double) args[0];
        y = (Double) args[1];
    }

    public void die() {

    }

    public void text() {

    }

    public void baseTick() {
        checkTimers();
        x += vx;
        y += vy;
        if (hp <= 0) {
            die();
        }
        if(x < 0)
            x = 0;
        if(y < 0)
            y = 0;
        if(x + getWidth() > dung.getWidth())
            x = dung.getWidth() - getWidth();
        if(y + getHeight() > dung.getHeight())
            y = dung.getWidth() - getHeight();
    }

    public void tick() {

    }

    public void move(double ex, double ey) {
        if (ex != 0 && ey != 0) {
            if (Math.abs(ex - x) > 4) {
                vx = (ex - x) / Math.abs(ex - x) * 2;
            } else {
                vx = 0;
            }
            if (Math.abs(ey - y) > 4) {
                vy = (ey - y) / Math.abs(ey - y) * 2;
            } else {
                vy = 0;
            }
        }
    }

    public void checkTimers() {
        for (Timer t : timers) {
            if (!t.is()) {
                t.tick();
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(-10, -10, 20, 20);
    }

}
