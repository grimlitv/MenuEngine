package util;

import java.awt.*;
public abstract class MouseButton {
    protected boolean buttonPressed = false;
    protected final Scene sceneLocation;
    protected final Rectangle rect;

    public MouseButton (Scene sceneLocation, Rectangle rect) {
        this.sceneLocation = sceneLocation;
        this.rect = rect;
    }

    protected boolean isHoverOver() {
        return Globals.ml.getX() >= rect.x && Globals.ml.getX() <= rect.x + rect.width &&
               Globals.ml.getY() >= rect.y && Globals.ml.getY() <= rect.y + rect.height;
    }

}
