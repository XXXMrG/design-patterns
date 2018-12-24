package xie;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MainComposite extends JComponent {
    private static final int DEFAULT_WIDTH = 1600;
    private static final int DEFAULT_HEIGHT = 1000;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public MainComposite(){
        MyComponent myComponent = new MyComponent(new Point2D.Double(100, 100), 100, 100);
        add(myComponent);
        myComponent.setBounds(10, 10, 1200, 800);

    }

}
