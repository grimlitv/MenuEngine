package util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteButton extends MouseButton {

    private final BufferedImage startImage;
    private final BufferedImage hoverImage;
    private final BufferedImage pressImage;

    public SpriteButton(Rectangle rect, BufferedImage startImage,
                       BufferedImage hoverImage, BufferedImage pressImage, Scene sceneLocation) {
        super(sceneLocation, rect);

        this.startImage = startImage;
        this.hoverImage = hoverImage;
        this.pressImage = pressImage;
    }

    public BufferedImage update() {
        BufferedImage output;
        if (isHoverOver()) {
            output = hoverImage;
            if (Globals.ml.isButtonPressed(1)) {
                output = pressImage;
                buttonPressed = true;
            } else if (buttonPressed && !Globals.ml.isButtonPressed(1)) {
                buttonPressed = false;
                Globals.ph.switchScene(sceneLocation);
            }
        } else {
            buttonPressed = false;
            output = startImage;
        }
        return output;
    }
}
