package gui;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SubmitButton implements KeyListener {


    
    public SubmitButton() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            FormPanel fp = new FormPanel();
            fp.Checker();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
