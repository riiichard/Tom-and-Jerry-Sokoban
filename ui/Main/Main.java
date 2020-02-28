package Main;

import Model.Board;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends JFrame implements ActionListener{

    private final int OFFSET = 50;
    private JLabel jl;
    private JLabel js;
    private JLabel jsp;
    private JLabel jll;
    private JTextField jt;

    public Main(boolean b, String name) {
        super("Tom & Jerry");
        if(!b){
            welcome();
        }
        else {
            InitUI(name);
            playMusic();
        }
    }

    private void welcome() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 290));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(30, 13, 13, 13) );
        setLayout(new FlowLayout());
        JButton btn = new JButton("PLAY");
        btn.setActionCommand("myButton");
        btn.addActionListener(this);
        jl = new JLabel("               Hi, welcome to my project!                 ");
        jl.setFont(new Font("TimesRoman", Font.HANGING_BASELINE, 30));
        js = new JLabel("                                                                                           ");
        jll = new JLabel("         What is your name?          ");
        jll.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        jsp = new JLabel("                                                                                          ");
        jt = new JTextField(10);
        add(jl);
        add(js);
        add(jll);
        add(jsp);
        add(jt);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("myButton")){
            Main soko = new Main(true,jt.getText());
            soko.setVisible(true);
        }
    }

    public void InitUI(String name) {
        Board board = new Board();
        board.setPlayerName(name);
        add(board);

        playMusic();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board.getBoardWidth() + 4*OFFSET,
                board.getBoardHeight() + 4*OFFSET);
        setLocationRelativeTo(null);
        setTitle("Tom & Jerry");
    }


    public static void main(String[] args) throws MalformedURLException, IOException{
        Main sokoban = new Main(false,"Tom");
        sokoban.setVisible(true);
    }

    private void playMusic(){
        try {
            URL cb;
            File f = new File("res/music.wav");
            cb = f.toURL();
            AudioClip aau;
            aau = Applet.newAudioClip(cb);

            aau.play();
            aau.loop();
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
    }
}
