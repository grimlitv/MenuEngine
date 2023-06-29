import util.*;

public class Main {
    public static void main(String[] args) {
        ResourceManager.setFont();
        Globals.ph = new PanelHandler();
        FixedSizeGame fixedSizeGame = new FixedSizeGame(Options.SCREEN_WIDTH, Options.SCREEN_HEIGHT, Options.SCREEN_TITLE);
        Globals.ge = new GameEngine(fixedSizeGame);
        Globals.ge.start();
    }
}