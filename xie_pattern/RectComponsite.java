package xie;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class RectComponsite extends MyComponent implements Observable {
    private ArrayList<Observer> observers = new ArrayList<>();
    private Rectangle2D rectangle;

    public RectComponsite(Point2D startPoint, double width, double height){
        super(startPoint, width, height);
        rectangle = super.getRectangle();
        addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Point2D point) {
        for(Observer o : observers){
            o.update(point);
        }
    }

    private class MouseMotionHandler implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            notifyObservers(new Point2D.Double(rectangle.getX(), rectangle.getY()));

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}
