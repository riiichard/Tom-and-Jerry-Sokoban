package Model;

public class Wall extends Cell {

    public Wall(int x, int y) {
        super(x, y);
        ConstructIcons ci = new ConstructIcons(this,"res/wall1.jpg");
    }
}
