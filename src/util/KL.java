package util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

public class KL extends KeyAdapter implements KeyListener {
    private BitSet keyPressed = new BitSet(128);
    private BitSet keyReleased = new BitSet(128);

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyPressed.set(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyPressed.clear(keyEvent.getKeyCode());
        keyReleased.set(keyEvent.getKeyCode());
    }

    public boolean isKeyPressed(int keyCode) {
        if (keyCode < 128)
            return keyPressed.get(keyCode);
        else
            return false;
    }

    public boolean isKeyReleased (int keyCode) {
        if ((keyCode < 128) && (keyReleased.get(keyCode))) {
            keyReleased.clear(keyCode);
            return true;
        } else
            return false;
    }

    // TODO Do we need a setter for pretend key inputs?
}