package game.main;
/*
 * Did by Whizzpered. 
 * All code is mine.
 */

import game.creature.Creature;
import game.creature.Player;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Юрий Whizzpered
 */
public abstract class Scene {

    ArrayList<Button> buttons = new ArrayList<>();
    Player player;

    public void init(Object... args) throws SlickException {

    }

    public void spawn(Creature ent, Object... args) {

    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

}
