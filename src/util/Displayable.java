package util;

import java.awt.Graphics;

public abstract class Displayable {
    public abstract void update(double dt);
    public abstract void draw(Graphics g);
}
