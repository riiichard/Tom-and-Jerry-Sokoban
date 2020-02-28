package Model;

import javax.swing.*;
import java.awt.*;

public class ConstructIcons {

    public Cell cell;

    public ConstructIcons(Cell cell,String name){
        this.cell = cell;
        ImageIcon icon = new ImageIcon(name);
        Image image = icon.getImage();
        cell.setImage(image);
    }
}
