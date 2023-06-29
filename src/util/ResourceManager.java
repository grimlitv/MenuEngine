package util;

import java.awt.*;
import java.io.InputStream;

public class ResourceManager {
    public ResourceManager() {

    }

    public static void setFont() {
        try {
            InputStream stream = ResourceManager.class
                    .getResourceAsStream("/ARCADECLASSIC.TTF");
            Font font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);
            Globals.font = font;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
