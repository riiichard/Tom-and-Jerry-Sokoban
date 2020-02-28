package Model;

import java.awt.Image;

public abstract class Cell{
    private CellImageManger cim = new CellImageManger();
    private CellLocationManger clm;

    public Cell(int x, int y) {
        clm = new CellLocationManger(x,y);
    }

    public void setImage(Image img) {
        cim.setImage(img);
    }

    public Image getImage() {
        return cim.getImage();
    }
    public int x() {
        return clm.x;
    }

    public int y() {
        return clm.y;
    }

    public void setX(int x) {
        clm.setX(x);
    }

    public void setY(int y) {
        clm.setY(y);
    }

    public boolean checkNotLeft(Cell cell) {
       return clm.checkNotLeft(cell);
    }

    public boolean checkNotRight(Cell cell) {
       return clm.checkNotRight(cell);
    }

    public boolean checkNotUp(Cell cell) {
       return clm.checkNotUp(cell);
    }

    public boolean checkNotDown(Cell cell) {
        return clm.checkNotDown(cell);
    }

    public void move(int x, int y) {
        clm.move(x,y);
    }
}