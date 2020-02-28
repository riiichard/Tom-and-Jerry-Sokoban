package Model;


public class Traps extends Cell{

    public Traps(int x, int y) {
        super(x, y);
        ConstructIcons ci = new ConstructIcons(this,"res/trap1.jpg");
    }
}
