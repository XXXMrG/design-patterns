package xie;


import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        EventQueue.invokeLater(() ->{
            JFrame frame = new MainFrame();
            frame.setTitle("fuck");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}


class MainFrame extends JFrame{

    public MainFrame(){
        add(new MainComposite(this));
        pack();
    }

}
