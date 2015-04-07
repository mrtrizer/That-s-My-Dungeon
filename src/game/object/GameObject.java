/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.object;

/**
 *
 * @author trizer
 */
public class GameObject {
    public double x, y, vx, vy, ex, ey;
    public GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.ex = x;
        this.ey = y;
    }
}
