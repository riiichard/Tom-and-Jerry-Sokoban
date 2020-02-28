package Model;

import Observer.JerryObserver;

import javax.swing.*;
import java.awt.*;

public class Jerry extends Cell implements JerryObserver {

    public Jerry(int x, int y) {
        super(x, y);
        ConstructIcons ci = new ConstructIcons(this,"res/left Jerry1.jpg");
    }

    public void update(Jerry jerry) {
        ImageIcon icon = new ImageIcon("res/is caught1.jpg");
        Image image = icon.getImage();
        jerry.setImage(image);
    }
}