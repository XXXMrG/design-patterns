package xie;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MainComposite extends JComponent {
    private static final int DEFAULT_WIDTH = 1600;
    private static final int DEFAULT_HEIGHT = 1000;
    private Frame ParentFrame;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public MainComposite(Frame frame){
        initUI(frame);
    }

    private void initUI(Frame frame){
        RectComponsite myComponent = new RectComponsite(new Point2D.Double(100, 100), 100, 100);
        add(myComponent);
        myComponent.setBounds(400, 0, 600, 1000);
        TextComponsite textComponsite = new TextComponsite(new Point2D.Double(10, 10), 50, 50, frame);
        add(textComponsite);
        textComponsite.setBounds(10, 0, 400, 1000);
        myComponent.addObserver(textComponsite);
        TuliComponsite tuliComponsite = new TuliComponsite(new Point2D.Double(10, 10), 400, 600);
        add(tuliComponsite);
        tuliComponsite.setBounds(1000, 0, 800, 1000);
        myComponent.addObserver(tuliComponsite);
    }

}
