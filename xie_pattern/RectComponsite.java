package xie;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RectComponsite extends MyComponent implements Observable {
    private ArrayList<Observer> observers = new ArrayList<>();
    private Rectangle2D rectangle;
    private Line2D line;
    private ArrayList<Point2D> pointS;
    private ComponentFactory factory;
    private int size;
    public RectComponsite(Point2D startPoint, double width, double height){
        super(startPoint, width, height);
        rectangle = super.getRectangle();
        pointS = new ArrayList<>();
        factory = new ComponentFactory();
        addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    protected void paintComponent(Graphics g) {
        dataUpdate();
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        line = (Line2D) factory.createComponent("line");
        line.setLine(new Point2D.Double(rectangle.getX(), rectangle.getY() + rectangle.getHeight()),
                new Point2D.Double(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY()));
        graphics2D.setColor(Color.red);
        graphics2D.draw(line);
        size = pointS.size();
        for (Point2D p : pointS){
            Ellipse2D e = (Ellipse2D)factory.createComponent("elli");
            e.setFrame(p.getX() - 5, p.getY() - 5, 10, 10);
            graphics2D.fill(e);
        }
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
    public void notifyObservers(MyComponent component, int size) {
        for(Observer o : observers){
            o.update(component, size);
        }
    }

    private void addPoints(Point2D p){
        pointS.add(p);
    }

    private void dataUpdate(){
        String sql = "SELECT * FROM `xie` WHERE deep < " + rectangle.getHeight() + ";";
        ResultSet rs = null;
        Dbconnect con = new Dbconnect();
        con.conn(); //连接数据库
        rs = con.executeQuery(sql);
        double k = 10;
        double x, y;
        try{
            while (rs.next()){
                Point2D p = (Point2D) factory.createComponent("point");
                x = rectangle.getX() + k;
                y = (rectangle.getHeight() * k * 2)/ rectangle.getWidth();
                y = rectangle.getY() + (rectangle.getHeight() - y);
                if (y >= rectangle.getY()){
                    p.setLocation(x, y);
                    addPoints(p);
                }
                k += 10;
            }
            con.closeDate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private class MouseMotionHandler implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            pointS.clear();

                repaint();
            notifyObservers(RectComponsite.this, size);
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}
