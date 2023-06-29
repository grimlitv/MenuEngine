package util;

import game.Snake;
import menu.KeyboardConfigMenu;
import menu.MainMenu;
import menu.OptionsMenu;
import menu.PauseMenu;

import javax.swing.*;
import java.awt.*;

public class PanelHandler extends JPanel {

    private Displayable currentScene;
    private Scene currentState;
    public PanelHandler() {
        switchScene(Scene.MAIN);
    }

    public void switchScene(Displayable oldScene) {
        if (oldScene != null) {
            currentScene = oldScene;
        }
    }
    public void switchScene(Scene whichScene) {
        currentState = whichScene;
        switch (whichScene) {
            case MAIN -> currentScene = new MainMenu();
            case OPTIONS -> currentScene = new OptionsMenu();
            case CONFIG -> currentScene = new KeyboardConfigMenu();
            case GAME -> currentScene = new Snake();
            case EXIT -> Globals.ge.setRunning(false);
            case PAUSE -> currentScene = new PauseMenu();
            default -> System.out.println("Wrong scene? Error.");
        }
    }

    public void pauseScene(Displayable oldScene) {
        currentState = Scene.PAUSE;
        currentScene = new PauseMenu(oldScene);
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0, 0, this);

        currentScene.update(dt);
    }

    private void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        currentScene.draw(g2);
    }

}
