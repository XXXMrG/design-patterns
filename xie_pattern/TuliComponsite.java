package xie;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class TuliComponsite extends MyComponent implements Observer{
    private Rectangle2D frame;
    private Point2D start;
    private int dist = 10;
    private double mywidth, myheight;
    private ComponentFactory factory;
    private ArrayList<Rectangle2D> rectangle2DS;
    public TuliComponsite(Point2D startPoint, double width, double height){
        super(startPoint, width, height);
        rectangle2DS = new ArrayList<>();
        factory = new ComponentFactory();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        g.setColor(Color.red);
        for(Rectangle2D r : rectangle2DS){
            graphics2D.fill(r);
        }
    }

    private void initRect(int count){
        dist = 10;
        frame = super.getRectangle();
        start = (Point2D) factory.createComponent("point");
        start.setLocation(frame.getX() + 100, frame.getY() + dist);
        for (int n = 0; n < count; n++){
            Rectangle2D  rect = (Rectangle2D) factory.createComponent("rect");
            rect.setFrame(start.getX(), start.getY(), 10, 10);
            rectangle2DS.add(rect);
            dist += 20;
            start.setLocation(frame.getX() + 100, frame.getY() + dist);
        }
    }

    @Override
    public void update(MyComponent component, int size) {
        rectangle2DS.clear();
        initRect(size/2);
        repaint();
    }
}
