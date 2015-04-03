/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.main;

/**
 *
 * @author Юрий Whizzpered
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.sparkle.jcfg.JCFG;
import org.sparkle.jcfg.Parser;
import utils.FPScounter;

public class SlickGame extends BasicGame {

    Graphics g = new Graphics();
    static JCFG conf = new JCFG();
    public static int display_width, display_height, times = 0;
    public static boolean paused;

    public static AppGameContainer app;

    public SlickGame() {
        super("");
    }

    public static void main(String[] arguments) throws SlickException {
        setUpNatives();
        app = new AppGameContainer(new SlickGame());
        Display.setResizable(true);
        setSources();
        app.setDisplayMode(display_width, display_height, false);
        app.setDefaultMouseCursor();
        app.setAlwaysRender(true);
        app.setShowFPS(false);
        app.start();
    }

    public static void setSources() throws SlickException {
        try {
            int w = 800, h = 600;
            File cfg = new File("conf.cfg");
            if (cfg.exists()) {
                conf = Parser.parse(cfg);
                w = conf.get("w").getValueAsInteger();
                h = conf.get("h").getValueAsInteger();
                Game.times = conf.get("times").getValueAsInteger();
            } else {
                conf.set("music", true);
            }
            display_width = w;
            display_height = h;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SlickGame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        Game.currScene.init();
        Game.sceneTimer();
        Game.fontRender = FontRender.getTextRender("Sans", 0, 16);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (Display.isCloseRequested()) {
            Game.exit();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        Game.currScene.render(g);
        FPScounter.render();
    }

    public static void setUpNatives() {
        if (!new File("natives").exists()) {
            JOptionPane.showMessageDialog(null, "Error!\nNative libraries not found!");
            System.exit(1);
        }
        try {
            System.setProperty("java.library.path", new File("natives").getAbsolutePath());

            Field fieldSysPath = ClassLoader.class
                    .getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(
                    true);

            try {
                fieldSysPath.set(null, null);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Error!\n" + ex.toString());
                System.exit(1);
            } catch (IllegalAccessException ex) {
                JOptionPane.showMessageDialog(null, "Error!\n" + ex.toString());
                System.exit(1);
            }
        } catch (NoSuchFieldException ex) {
            JOptionPane.showMessageDialog(null, "Error!\n" + ex.toString());
            System.exit(1);
        } catch (SecurityException ex) {
            JOptionPane.showMessageDialog(null, "Error!\n" + ex.toString());
            System.exit(1);
        }
    }
}
