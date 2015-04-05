/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.main;

import game.creature.*;
import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Юрий Whizzpered
 */
public class Dungeon extends Scene {

    public ArrayList<Raider> creatures = new ArrayList<>();

    Flag flag;
    Random r = new Random();
    int lvl;
    public static double plcamx, plcamy;
    public boolean inited;

    public Raider[] getArrEnts() {
        Raider[] u = new Raider[creatures.size()];
        try {
            for (int i = 0; i < creatures.size(); i++) {
                u[i] = creatures.get(i);
            }
        } catch (Exception e) {
        }
        return u;
    }

    @Override
    public void init(Object... args) {
        inited = true;
        spawnCr();
        initButtons();
    }

    public void spawnCr() {
        spawn(new Raider(), 120.0, 120.0, 70, 3);
    }

    public void initButtons() {
        buttons.add(new Button(-50, Display.getHeight() - 50, 100, "Menu", Color.green) {
            @Override
            public void click() {
                Game.currScene = Game.menu;
                player.y = 120;
                plcamx = 0;
                plcamy = 0;
            }
        });

    }

    @Override
    public void spawn(Raider cr, Object... args) {
        cr.init(args);
        cr.dung = this;
        creatures.add(cr);
    }

    @Override
    public void tick() {
        for (Creature ent : getArrEnts()) {
            ent.tick();
        }
        player.tick();
        mouseNavigation();
    }

    public void mouseNavigation() {
        int w = Display.getWidth(), h = Display.getHeight();
        int mx = Mouse.getX();
        int my = h - Mouse.getY();
        plcamx = w / 2 - mx;
        plcamy = h / 2 - my;

        if (Mouse.isButtonDown(1)) {
            player.ex = (int) (mx - w / 2 + player.x);
            player.ey = (int) (my - h / 2 + player.y);
            flag = new Flag(player.ex, player.ey);
        }
        if (Mouse.isButtonDown(0)) {

        }
    }

    @Override
    public void render(Graphics g) {

        GL11.glTranslated(plcamx/2, plcamy/2, 0);

        player.gameRender(g);

        GL11.glTranslated(-player.x + Display.getWidth() / 2, -player.y + Display.getHeight() / 2, 0);
        for (Raider ent : getArrEnts()) {
            ent.render(g);
        }
        if (flag != null) {
            flag.render(g);
        }
        GL11.glTranslated(player.x - Display.getWidth() / 2, player.y - Display.getHeight() / 2, 0);

        GL11.glTranslated(-plcamx/2, -plcamy/2, 0);

        player.text();
        for (Raider ent : getArrEnts()) {
            ent.text();
        }

        for (Button bt : buttons) {
            bt.render(g);
        }
    }
}
