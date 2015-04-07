/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.object;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author trizer
 */
public class Bullet extends GameObject {
    
    public double angle;
    public double speed;

    public Bullet (int x1, int y1, int x2, int y2, double speed)
    {
        super(x1,y1);
        int dx = x2 - x1;
        int dy = y2 - y1;
        this.angle = Math.atan2(dy, dx);
        this.speed = speed;
    }

    public void move(double ex, double ey) {
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(-5, -5, 10, 10);
    }
}
