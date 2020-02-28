package Model;

import Exceptions.*;
import Input.Button;
import Input.MouseManager;
import Interface.GameState;
import Main.Main;
import Observer.Subject;
import Observer.JerryObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_R;

public class Board extends JPanel implements GameState, Subject {

    private final int OFFSET = 130;
    private final int SPACE = 40;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;

    private ArrayList walls = new ArrayList();
    private ArrayList Jerrs = new ArrayList();
    private ArrayList traps = new ArrayList();
    private Tom tom;
    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private ArrayList<Button> buttons = new ArrayList<Button>();
    private String PlayerName = "";

    private String map =
            "######\n"
                    + "#   .#\n"
                    + "# ## #\n"
                    + "#    #\n"
                    + "# ## #\n"
                    + "# ## #######\n"
                    + "#.$ @      #\n"
                    + "# ## ## ## #\n"
                    + "#      $   #\n"
                    + "# ## #######\n"
                    + "# ## #\n"
                    + "#    #\n"
                    + "######\n";

    public Board() {

        addKeyListener(new TAdapter());
        addMouseListener(new MouseManager(this));
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    private final void initWorld() {

        int x = OFFSET;
        int y = OFFSET;

        Wall wall;
        Jerry jerry;
        Traps trap;

        for (int i = 0; i < map.length(); i++) {

            char item = map.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '#') {
                wall = new Wall(x, y);
                walls.add(wall);
                x += SPACE;
            }else if (item == '$') {
                jerry = new Jerry(x, y);
                Jerrs.add(jerry);
                addObserver(jerry);
                x += SPACE;
            } else if (item == '.') {
                trap = new Traps(x, y);
                traps.add(trap);
                x += SPACE;
            } else if (item == '@') {
                tom = new Tom(x, y);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }
    }

    public void setPlayerName(String name){
        this.PlayerName = name;
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(108, 250, 122));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("TimesRoman", Font.ITALIC, 40));
        g.drawString("Hey "+ PlayerName +", catch these Jerry!",140,70);

        g.setColor(new Color(0,0,0));
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("Hi "+ PlayerName +", you can drive these Jerry to traps by keyboard arrows.",110,700);

        g.setColor(new Color(0,0,0));
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.drawString("Click 'R' to Restart.",270,750);

        Button exit = new Button("EXIT", 700, 500, () -> System.exit(1));
        Button back = new Button("BACK", 700, 300, () -> {
            Main sokoban = new Main(false,"Tom");
            sokoban.setVisible(true);
        });
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        exit.render(g);
        back.render(g);
        buttons.add(exit);
        buttons.add(back);

        ArrayList world = new ArrayList();
        world.addAll(walls);
        world.addAll(traps);
        world.addAll(Jerrs);
        world.add(tom);

        for (int i = 0; i < world.size(); i++) {

            Cell item = (Cell) world.get(i);

            if ((item instanceof Tom)
                    || (item instanceof Jerry)) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(250, 250, 250));
                g.setFont(new Font("TimesRoman", Font.ROMAN_BASELINE, 60));
                g.drawString("YOU WIN!", 440, 650);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            try {
                moveByKeyPressed(key);
            } catch (CompletedKeException e1) {
                System.out.println("You've got it.");
            }
            catch (CollisionKeException el) {
                System.out.println("Collision! Cannot move in this direction.");
            }
            catch (UnexpectedKeException e1) {
                System.out.println("Invalid KeyEvent!");
            } finally {
                repaint();
            }
        }
    }

    private void moveByKeyPressed(int key) throws CompletedKeException, CollisionKeException, UnexpectedKeException {
        if (completed) {
            throw new CompletedKeException();
        }

        if (key == KeyEvent.VK_LEFT) {
            if (checkWallCollision(tom, LEFT_COLLISION)) {
                throw new CollisionKeException();
            }
            if (checkBagCollision(LEFT_COLLISION)) {
                return;
            }
            tom.move(-SPACE, 0);

        } else if (key == KeyEvent.VK_RIGHT) {

            if (checkWallCollision(tom, RIGHT_COLLISION)) {
                throw new CollisionKeException();
            }
            if (checkBagCollision(RIGHT_COLLISION)) {
                return;
            }
            tom.move(SPACE, 0);

        } else if (key == KeyEvent.VK_UP) {

            if (checkWallCollision(tom, TOP_COLLISION)) {
                throw new CollisionKeException();
            }
            if (checkBagCollision(TOP_COLLISION)) {
                return;
            }
            tom.move(0, -SPACE);

        } else if (key == KeyEvent.VK_DOWN) {

            if (checkWallCollision(tom, BOTTOM_COLLISION)) {
                throw new CollisionKeException();
            }
            if (checkBagCollision(BOTTOM_COLLISION)) {
                return;
            }
            tom.move(0, SPACE);

        } else if (key == VK_R) {
            restart();
        }
        else{
            throw new UnexpectedKeException();
        }
    }

    private boolean checkWallCollision(Cell cell, int type) {

        if (type == LEFT_COLLISION) {
            return ShouldStopMovingLeft(cell);

        } else if (type == RIGHT_COLLISION) {
            return ShouldStopMovingRight(cell);

        } else if (type == TOP_COLLISION) {
            return ShouldStopMovingUp(cell);

        } else if (type == BOTTOM_COLLISION) {
            return ShouldStopMovingDown(cell);
        }
        return false;
    }

    private boolean ShouldStopMovingDown(Cell cell) {
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = (Wall) walls.get(i);
            if (cell.checkNotDown(wall)) {
                return true;
            }
        }
        return false;
    }

    private boolean ShouldStopMovingUp(Cell cell) {
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = (Wall) walls.get(i);
            if (cell.checkNotUp(wall)) {
                return true;
            }
        }
        return false;
    }

    private boolean ShouldStopMovingRight(Cell cell) {
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = (Wall) walls.get(i);
            if (cell.checkNotRight(wall)) {
                return true;
            }
        }
        return false;
    }

    private boolean ShouldStopMovingLeft(Cell cell) {
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = (Wall) walls.get(i);
            if (cell.checkNotLeft(wall)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkBagCollision(int type) {

        if (type == LEFT_COLLISION) {

            for (int i = 0; i < Jerrs.size(); i++) {

                Jerry jerry = (Jerry) Jerrs.get(i);
                if (tom.checkNotLeft(jerry)) {

                    for (int j=0; j < Jerrs.size(); j++) {
                        Jerry jer = (Jerry) Jerrs.get(j);
                        if (!jerry.equals(jer)) {
                            if (jerry.checkNotLeft(jer)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(jerry,
                                LEFT_COLLISION)) {
                            return true;
                        }
                    }
                    jerry.move(-SPACE, 0);
                    setState(jerry);
                    checkState();
                }
            }
            return false;

        } else if (type == RIGHT_COLLISION) {

            for (int i = 0; i < Jerrs.size(); i++) {

                Jerry jerry = (Jerry) Jerrs.get(i);
                if (tom.checkNotRight(jerry)) {
                    for (int j=0; j < Jerrs.size(); j++) {

                        Jerry jer = (Jerry) Jerrs.get(j);
                        if (!jerry.equals(jer)) {
                            if (jerry.checkNotRight(jer)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(jerry,
                                RIGHT_COLLISION)) {
                            return true;
                        }
                    }
                    jerry.move(SPACE, 0);
                    setState(jerry);
                    checkState();
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {

            for (int i = 0; i < Jerrs.size(); i++) {

                Jerry jerry = (Jerry) Jerrs.get(i);
                if (tom.checkNotUp(jerry)) {
                    for (int j = 0; j < Jerrs.size(); j++) {

                        Jerry jer = (Jerry) Jerrs.get(j);
                        if (!jerry.equals(jer)) {
                            if (jerry.checkNotUp(jer)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(jerry,
                                TOP_COLLISION)) {
                            return true;
                        }
                    }
                    jerry.move(0, -SPACE);
                    setState(jerry);
                    checkState();
                }
            }

            return false;

        } else if (type == BOTTOM_COLLISION) {

            for (int i = 0; i < Jerrs.size(); i++) {

                Jerry jerry = (Jerry) Jerrs.get(i);
                if (tom.checkNotDown(jerry)) {
                    for (int j = 0; j < Jerrs.size(); j++) {

                        Jerry jer = (Jerry) Jerrs.get(j);
                        if (!jerry.equals(jer)) {
                            if (jerry.checkNotDown(jer)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(jerry,
                                BOTTOM_COLLISION)) {
                            return true;
                        }
                    }
                    jerry.move(0, SPACE);
                    setState(jerry);
                    checkState();
                }
            }
        }

        return false;
    }

    public void update() {
        for(int i = 0; i < buttons.size(); i++)
            buttons.get(i).update();
    }

    private void setState(Jerry jerry) {
        for (int j = 0; j < 2; j++) {
            Traps trap = (Traps) traps.get(j);
            if (jerry.x() == trap.x() && jerry.y() == trap.y()) {
                notifyObservers(jerry);
                break;
            }
            else{reset(jerry);}
        }
    }

    private void reset(Jerry jerry){
        ImageIcon icon = new ImageIcon("res/left Jerry1.jpg");
        Image image = icon.getImage();
        jerry.setImage(image);
    }

    public void checkState(){
        int num = Jerrs.size();
        int compl = 0;

        for (int i = 0; i < num; i++) {
            Jerry jer = (Jerry) Jerrs.get(i);
            for (int j = 0; j < num; j++) {
                Traps trap = (Traps) traps.get(j);
                if (jer.x() == trap.x()
                        && jer.y() == trap.y()) {
                    compl += 1;
                }
            }
        }

        if (compl == num) {
            completed = true;
            repaint();
        }
    }

    public void restart() {

        traps.clear();
        Jerrs.clear();
        walls.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }

    public void addObserver(JerryObserver JerryObserver){
        if (!observers.contains(JerryObserver)){
            observers.add(JerryObserver);
        }
    }

    public void notifyObservers(Jerry jerry){
        for (JerryObserver observer : observers){
            observer.update(jerry);
        }
    }
}
