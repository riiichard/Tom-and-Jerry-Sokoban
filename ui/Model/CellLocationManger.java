package Model;

public class  CellLocationManger {
    private final int SPACE = 40;
    protected int x;
    protected int y;

    public CellLocationManger(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean checkNotLeft(Cell cell) {
        if (((this.x() - SPACE) == cell.x()) && (this.y() == cell.y())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNotRight(Cell cell) {
        if (((this.x() + SPACE) == cell.x()) && (this.y() == cell.y())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNotUp(Cell cell) {
        if (((this.y() - SPACE) == cell.y()) && (this.x() == cell.x())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNotDown(Cell cell) {
        if (((this.y() + SPACE) == cell.y()) && (this.x() == cell.x())) {
            return true;
        } else {
            return false;
        }
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }
}
