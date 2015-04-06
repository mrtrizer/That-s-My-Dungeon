/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.main;

import game.creature.*;
import game.world.Block;
import game.world.Floor;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Юрий Whizzpered
 */
public class Dungeon extends Scene {

    public ArrayList<Raider> creatures = new ArrayList<>();

    public Flag flag;
    Random r = new Random();
    Floor floor = new Floor();
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
        try {
            floor.init();
        } catch (SlickException ex) {
            Logger.getLogger(Dungeon.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        buttons();
    }

    public void buttons() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            player.vy = -3;
            player.reset();
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            player.vy = 3;
            player.reset();
        } else if (flag == null) {
            player.vy = 0;

        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            player.vx = -3;
            player.reset();
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            player.vx = 3;
            player.reset();
        } else if (flag == null) {
            player.vx = 0;
        }

    }

    public void mouseNavigation() {
        int w = Display.getWidth(), h = Display.getHeight();
        int mx = Mouse.getX();
        int my = h - Mouse.getY();
        plcamx = w / 2 - mx;
        plcamy = h / 2 - my;

        if (Mouse.isButtonDown(1)) {
            player.ex = (player.x - plcamx);
            player.ey = (player.y - plcamy);
            flag = new Flag(player.ex, player.ey);
        }
        if (Mouse.isButtonDown(0)) {
            for (Raider raid : creatures) {
                if(Math.abs(raid.x-(player.x - plcamx))<100 && Math.abs(raid.y-(player.y - plcamy))<100)
                    player.focussmth(raid);
            }
        }
    }

    @Override
    public void render(Graphics g) {

        double py = plcamy / 2, px = plcamx / 2;
        double cx = player.x - Display.getWidth() / 2, cy = player.y - Display.getHeight() / 2;

        GL11.glTranslated(px, py, 0);
        GL11.glTranslated(-cx, -cy, 0);

        floor.render(g);
        for (Raider ent : getArrEnts()) {
            ent.render(g);
        }
        if (flag != null) {
            flag.render(g);
        }

        GL11.glTranslated(cx, cy, 0);
        player.gameRender(g);
        GL11.glTranslated(-px, -py, 0);

        player.text();
        for (Raider ent : getArrEnts()) {
            ent.text();
        }

        for (Button bt : buttons) {
            bt.render(g);
        }
    }
}
