package util;

import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.BitSet;

public class ML extends MouseAdapter implements MouseMotionListener {
    private static final int numberOfButtons = MouseInfo.getNumberOfButtons();
    private BitSet buttonPressed = new BitSet(numberOfButtons);
    private double x = 0.0, y = 0.0; //TODO track drags? xd = 0.0, yd = 0.0;
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        buttonPressed.set(mouseEvent.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        buttonPressed.clear(mouseEvent.getButton());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        // System.out.println("X: " + x + " Y: " + y);
    }


    //TODO This is a bit dirty, may need to handle these events separately
    @Override
    public void mouseDragged(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public double getX() { return this.x; }
    public double getY() { return this.y; }
//    public double getXD() { return this.xd; }
//    public double getYD() { return this.yd; }
    public boolean isButtonPressed(int keyCode) {
        return (keyCode <= numberOfButtons) && buttonPressed.get(keyCode);
    }
}