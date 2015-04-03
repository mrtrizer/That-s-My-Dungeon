/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package game.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.sparkle.jcfg.JCFG;
import org.sparkle.jcfg.Parser;
import org.sparkle.jcfg.Writer;
import utils.FPScounter;

/**
 *
 * @author Юрий Whizzpered
 */
public class Game {

    public static Menu menu = new Menu();
    public static Dungeon dungeon = new Dungeon();
    static JCFG conf = new JCFG();
    public static int display_width, display_height, times = 0;
    public static FontRender fontRender;
    public static boolean paused;
    public static Scene currScene = menu;

    public static void exit() {
        times++;
        File cfg = new File("conf.cfg");
        conf.set("w", Display.getWidth());
        conf.set("h", Display.getHeight());
        conf.set("x", Display.getX());
        conf.set("y", Display.getY());
        conf.set("times", times);
        try {
            Writer.writeToFile(conf, cfg);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        System.exit(0);
    }

    public static void sceneTimer() {
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!paused) {
                    currScene.tick();
                }
            }
        }, 0, 10);
    }

    public static void init() throws SlickException {
        currScene.init();
        sceneTimer();
        fontRender = FontRender.getTextRender("Sans", 0, 16);
    }

    public static void config() throws LWJGLException, FileNotFoundException {
        int w = 800, h = 600;
        File cfg = new File("conf.cfg");
        if (cfg.exists()) {
            conf = Parser.parse(cfg);
            w = conf.get("w").getValueAsInteger();
            h = conf.get("h").getValueAsInteger();
            times = conf.get("times").getValueAsInteger();
        } else {
            conf.set("music", true);
        }

        
    }

    public static void main() {
        Thread mainThread;
        mainThread = new Thread("Main Running Thread") {
            @Override
            public void run() {
                try {
                    Graphics g = new Graphics();
                    config();
                    Display.setTitle("I'll be a Hero");
                    Display.setResizable(false);
                    Display.create(new PixelFormat(0, 4, 0, 4));

                    display_width = Display.getWidth();
                    display_height = Display.getHeight();
                    /*{
                     display_width = Display.getWidth();
                     display_height = Display.getHeight();
                     GL11.glClearColor(1, 1, 1, 1);
                     GL11.glEnable(GL11.GL_TEXTURE_2D);
                     GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                     GL11.glEnable(GL11.GL_BLEND);
                     GL11.glMatrixMode(GL11.GL_PROJECTION);
                     GL11.glLoadIdentity();
                     GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
                     GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
                     GL11.glMatrixMode(GL11.GL_MODELVIEW);
                     GL11.glLoadIdentity();
                     //Here is render for pre-loading
                     Display.update();
                     }*/
                    //here is a pre-loading
                    init();
                    
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glEnable(GL11.GL_BLEND); //Юра, если что, это нужно перенести под MatrixMode

                    while (!Display.isCloseRequested()) {
                        if (Display.isCloseRequested()) {
                            exit();
                        }
                        display_width = Display.getWidth();
                        display_height = Display.getHeight();
                        GL11.glClearColor(0, 0, 0, 1);
                        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
                        GL11.glMatrixMode(GL11.GL_PROJECTION);
                        GL11.glLoadIdentity();
                        GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
                        GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
                        GL11.glMatrixMode(GL11.GL_MODELVIEW);
                        GL11.glLoadIdentity();
                        //Here is a block for render game
                        currScene.render(g);
                        FPScounter.render();
                        Display.update();
                    }
                    exit();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        mainThread.setPriority(Thread.MAX_PRIORITY);
        mainThread.start();
    }

    public static void setUpNatives() {
        if (!new File("natives").exists()) {
            JOptionPane.showMessageDialog(null, "Error!\nNative libraries not found!");
            System.exit(1);
        }
        try {

            System.setProperty("java.library.path", new File("natives").getAbsolutePath());

            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);

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

    public static void main(String... args) {
        setUpNatives();
        main();
    }
}
