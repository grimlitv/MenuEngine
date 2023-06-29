package menu;

import util.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionsMenu extends Displayable {

    private final TextButton kbConfigButton;
    private Color kbConfigColor = Color.BLUE;

    public OptionsMenu() {
        Rectangle kbConfigRect = TextButton.getRect("Keyboard Configuration", 20, 580, Globals.font);

        kbConfigButton = new TextButton(kbConfigRect, Globals.niceBlue, Color.WHITE, Color.GRAY, Scene.CONFIG);

        //generate menu objects
    }

    @Override
    public void update(double dt) {
        if (Globals.kl.isKeyReleased(KeyEvent.VK_ESCAPE)) {
            Globals.ph.switchScene(Scene.MAIN);
        }

        kbConfigColor = kbConfigButton.update();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(Globals.font);
        g.drawString("Options Menu", 20, g.getFont().getSize() + 20);

        g.setColor(kbConfigColor);
        g.drawString("Keyboard Configuration", 20, 580);

        //put menu objects on screen
    }
}
