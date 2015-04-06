/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.main;

import static game.main.Game.fontRender;
import static game.main.Menu.sprite;
//import static game.main.Menu.sprite;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Юрий Whizzpered
 */
public abstract class Button {

    Game game = new Game();
    int x, y, w, wp = 1;
    String text;
    Color color;
    boolean bp;

    public Button(int x, int y, int w, String text, Color c) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.text = text;
        this.color = c;
    }

    public void render(Graphics g) {
        int x = this.x + Display.getWidth() / 2;
        int y = this.y;
        int w = this.w + wp;
        fontRender.drawString(text, x - fontRender.getWidth(text) / 2, y + 3, Color.black);
        int mx = Mouse.getX();
        int my = Display.getHeight() - Mouse.getY();

        sprite[0].setImageColor(color.r, color.g, color.b);
        sprite[1].setImageColor(color.r, color.g, color.b);
        sprite[2].setImageColor(color.r, color.g, color.b);
        sprite[0].draw(x - w / 2 - 16, y);
        sprite[1].draw(x - w / 2, y, w, 50);
        sprite[2].draw(x + w / 2, y);
        fontRender.drawString(text, x - fontRender.getWidth(text) / 2, y + 3, Color.white);

        if (Math.abs(mx - x) < (w + wp) / 2 && Math.abs(y + 25 - my) < 25) {
            if (wp < 16) {
                wp *= 2;
            }
            if (bp && !Mouse.isButtonDown(0)) {
                click();
            }
        } else {
            if (wp > 1) {
                wp /= 2;
            }
        }
        bp = Mouse.isButtonDown(0);
    }

    abstract public void click();
}
