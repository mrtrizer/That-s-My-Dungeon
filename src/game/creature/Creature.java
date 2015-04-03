/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.creature;

import game.main.Dungeon;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import utils.Timer;

/**
 *
 * @author Юрий Whizzpered
 */
public class Creature {

    public Dungeon dung;
    public Creature focus;
    public double x, y, vx, vy;
    public int hp, maxhp, dmg, gold, ex, ey;
    ArrayList<Timer> timers = new ArrayList<>();
    ArrayList<String> timnames = new ArrayList<>();
    public boolean focused;

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

    public void baseTick() {
        checkTimers();
        x += vx;
        y += vy;
        if (hp <= 0) {
            die();
        }
    }

    public void tick() {

    }

    public void move(int ex, int ey) {
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
