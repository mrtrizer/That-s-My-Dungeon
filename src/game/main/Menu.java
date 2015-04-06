/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.main;

import game.creature.Creature;
import game.creature.Player;
import static game.main.Game.fontRender;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Юрий Whizzpered
 */
public class Menu extends Scene {

    public static Image[] sprite = new Image[4];

    @Override
    public void init(Object... args) throws SlickException {

        sprite[0] = new Image("res/gui/button_left.png");
        sprite[1] = new Image("res/gui/button_center.png");
        sprite[2] = new Image("res/gui/button_right.png");
        sprite[1].setFilter(GL11.GL_NEAREST);
        sprite[3] = new Image("res/gui/back.jpg");
        sprite[3].setFilter(GL11.GL_NEAREST);

        player = new Player();
        player.init(120.0, 120.0, 800, 10);
        player.dung = Game.dungeon;

        int w = Display.getWidth(), h = Display.getHeight();

        buttons.add(new Button(-50, h / 2 + 100, 200, "Exit", Color.red) {
            @Override
            public void click() {
                Game.exit();
            }
        });

        buttons.add(new Button(-50, h / 2 - 25, 200, "Play", Color.green) {
            @Override
            public void click() {
                if (!Game.dungeon.inited) {
                    Game.dungeon.init();
                }
                Game.currScene = Game.dungeon;
                player.vx = 0;
                player.vy = 0;
                player.x = 120;
                Game.dungeon.player = player;
            }
        });

        buttons.add(new Button(-50, h / 2 + 38, 200, "Settings", Color.green) {
            @Override
            public void click() {

            }
        });
    }

    @Override
    public void tick() {
        player.statictick();
    }

    public void start() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite[3], 0, 0);
        sprite[3].draw(0, 0, Display.getWidth(), Display.getHeight());
        fontRender.drawString("Welcome back in " + Game.times + " time!", Display.getWidth() - 200, 0, Color.yellow);
        for (Button but : buttons) {
            but.render(g);
        }
        player.render(g);
    }

}
