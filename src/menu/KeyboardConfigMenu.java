package menu;

import util.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardConfigMenu extends Displayable {
    private Displayable previousScene;

    public KeyboardConfigMenu() {

        //generate menu objects
    }

    public KeyboardConfigMenu(Displayable previousScene) {
        this.previousScene = previousScene;
    }

    @Override
    public void update(double dt) {
        if (previousScene != null) {
            if (Globals.kl.isKeyReleased(KeyEvent.VK_ESCAPE)) {
                Globals.ph.switchScene(previousScene);
                previousScene = null;
            }
        } else if (Globals.kl.isKeyReleased(KeyEvent.VK_ESCAPE)) {
            Globals.ph.switchScene(Scene.MAIN);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.setFont(Globals.font);
        g.drawString("Keyboard Config Menu", 20, g.getFont().getSize() + 20);

        //put menu objects on screen
    }
}
