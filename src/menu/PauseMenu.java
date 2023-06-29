package menu;

import util.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseMenu extends Displayable {
    private static Displayable previousScene = null;
    public PauseMenu() {

    }
    public PauseMenu(Displayable previousScene) {
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
        } else if (Globals.kl.isKeyPressed(KeyEvent.VK_0)) {
            Globals.ph.switchScene(Scene.MAIN);
        } else if (Globals.kl.isKeyPressed(KeyEvent.VK_1)) {
            Globals.ph.switchScene(Scene.OPTIONS);
        } else if (Globals.kl.isKeyPressed(KeyEvent.VK_2)) {
            Globals.ph.switchScene(Scene.CONFIG);
        } else if (Globals.kl.isKeyPressed(KeyEvent.VK_3)) {
            Globals.ph.switchScene(Scene.GAME);
        } else if (Globals.kl.isKeyPressed(KeyEvent.VK_4)) {
            Globals.ph.switchScene(Scene.EXIT);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(Globals.font);
        g.drawString("Pause Menu", 20, g.getFont().getSize() + 20);
    }
}
