package menu;

import util.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainMenu extends Displayable {

    private final SpriteButton startButton;
    private final SpriteButton optionsButton;
    private final SpriteButton exitButton;
    private BufferedImage title, start, options, exit,
            startpress, optionspress, exitpress,
            starthover, optionshover, exithover;

    private Rectangle titleRect, startRect, optionsRect, exitRect;

    private BufferedImage startCurrentImage, optionsCurrentImage, exitCurrentImage;

    public MainMenu() {
        try {
            BufferedImage menuSprites = ImageIO.read(new File("resources/smoptions.png"));
            title = menuSprites.getSubimage(0, 0, 173, 30);
            start = menuSprites.getSubimage(0, 31, 73, 30);
            options = menuSprites.getSubimage(0, 61, 107, 31);
            exit = menuSprites.getSubimage(0, 94, 50, 30);
            startpress = menuSprites.getSubimage(118, 31, 73, 30);
            optionspress = menuSprites.getSubimage(118, 61, 107, 31);
            exitpress = menuSprites.getSubimage(118, 94, 50, 30);
            starthover = menuSprites.getSubimage(239, 31, 73, 30);
            optionshover = menuSprites.getSubimage(239, 61, 107, 31);
            exithover = menuSprites.getSubimage(239, 94, 50, 30);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // where are the menu elements
        titleRect = new Rectangle(200, 200, title.getWidth(), title.getHeight());
        startRect = new Rectangle(titleRect.x, titleRect.y + titleRect.height + 50, start.getWidth(), start.getHeight());
        optionsRect = new Rectangle(startRect.x, startRect.y + startRect.height + 20, options.getWidth(), options.getHeight());
        exitRect = new Rectangle(optionsRect.x, optionsRect.y + optionsRect.height + 20, exit.getWidth(), exit.getHeight());

        //add function to elements
        startButton = new SpriteButton(startRect, start, starthover, startpress, Scene.GAME);
        optionsButton = new SpriteButton(optionsRect, options, optionshover, optionspress, Scene.OPTIONS);
        exitButton = new SpriteButton(exitRect, exit, exithover, exitpress, Scene.EXIT);
    }

    @Override
    public void update(double dt) {
        startCurrentImage = startButton.update();
        optionsCurrentImage = optionsButton.update();
        exitCurrentImage = exitButton.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(title, titleRect.x, titleRect.y, titleRect.width, titleRect.height, null);
        g.drawImage(startCurrentImage, startRect.x, startRect.y, startRect.width, startRect.height, null);
        g.drawImage(optionsCurrentImage, optionsRect.x, optionsRect.y, optionsRect.width, optionsRect.height, null);
        g.drawImage(exitCurrentImage, exitRect.x, exitRect.y, exitRect.width, exitRect.height, null);


        //put menu objects on screen

    }
}
