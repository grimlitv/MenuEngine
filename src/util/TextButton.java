package util;

import java.awt.*;

public class TextButton extends MouseButton {

    private final Color startColor;
    private final Color hoverColor;
    private final Color pressColor;

    public TextButton(Rectangle rect, Color startColor,
                      Color hoverColor, Color pressColor, Scene sceneLocation) {
        super(sceneLocation, rect);
        this.startColor = startColor;
        this.hoverColor = hoverColor;
        this.pressColor = pressColor;
    }

    public static Rectangle getRect(String buttonText, int x, int y, Font font) {
        FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        int ascent = fontMetrics.getAscent();
        int descent = fontMetrics.getDescent();
        int width = fontMetrics.stringWidth(buttonText);
        int height = ascent + descent;
        return new Rectangle(x, (y - ascent), width, height);
    }

    public Color update() {
        Color output;
        if (isHoverOver()) {
            output = hoverColor;
            if (Globals.ml.isButtonPressed(1)) {
                output = pressColor;
                buttonPressed = true;
            } else if (buttonPressed && !Globals.ml.isButtonPressed(1)) {
                buttonPressed = false;
                Globals.ph.switchScene(sceneLocation);
            }
        } else {
            buttonPressed = false;
            output = startColor;
        }
        return output;
    }
}
