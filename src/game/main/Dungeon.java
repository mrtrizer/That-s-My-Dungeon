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
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Юрий Whizzpered
 */
public class Dungeon extends Scene {

    public ArrayList<Creature> creatures = new ArrayList<>();

    Random r = new Random();
    int lvl;

    public Creature[] getArrEnts() {
        Creature[] u = new Creature[creatures.size()];
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
        buttons.add(new Button(60, Display.getHeight() - 50, 70, "Menu", Color.green) {
            @Override
            public void click() {
                Game.currScene = Game.menu;
                player.y=120;
            }
        });

    }

    @Override
    public void spawn(Creature cr, Object... args) {
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
        int mx = Mouse.getX();
        int my = Display.getHeight() - Mouse.getY();
        if (Mouse.isButtonDown(1)) {

        }
        if (Mouse.isButtonDown(0)) {

        }
    }

    @Override
    public void render(Graphics g) {

        player.render(g);

        for (Creature ent : getArrEnts()) {
            ent.render(g);
        }

        for (Button bt : buttons) {
            bt.render(g);
        }
    }
}
