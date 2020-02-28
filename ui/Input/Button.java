package Input;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import Model.Text;

public class Button{

    private String text;
    private int x, y;
    private FontMetrics fm;
    private Rectangle bounds;
    private Click click;

    public Button(String text, int x, int y, Click click){
        this.text = text;
        this.x = x;
        this.y = y;
        this.click = click;
    }

    public void update(){
        if(bounds != null && bounds.contains(MouseManager.x, MouseManager.y)){
            if(MouseManager.left)
                click.onClick();
        }
    }

    public void render(Graphics g){
        fm = g.getFontMetrics();
        Text.drawString(g, text, x, y, true, Color.BLUE);
        bounds = new Rectangle(x - fm.stringWidth(text)/2, y - fm.getHeight()/2, fm.stringWidth(text), fm.getHeight());
    }

}
