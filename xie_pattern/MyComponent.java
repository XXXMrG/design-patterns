package xie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MyComponent extends JComponent {
    private Rectangle2D rectangle;
    private Point2D startPoint, leftUpPoint, leftDownPoint, rightUpPoint, rightDownPoint;
    private Ellipse2D leftUpCorner, leftDownCorner, rightUpCorner, rightDownCorner;
    private double width, height;
    private ComponentFactory factory;
    private int stage = 0;
    private int click = 0;
    public ArrayList<Ellipse2D> conerS;
    public MyComponent(){}

    public MyComponent(Point2D startPoint, double width, double height){
        conerS = new ArrayList<>();
        factory = new ComponentFactory();
        this.startPoint = startPoint;
        this.width = width;
        this.height = height;
        rectangle = (Rectangle2D) factory.createComponent("rect");
        rectangle.setFrame(startPoint.getX(), startPoint.getY(), width, height);
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    public void setMySize(double width, double height ) {
        this.width = width;
        this.height = height;
    }


    public double getMyHeight() {
        return height;
    }

    public double getMyWidth() {
        return width;
    }

    public Point2D getLeftUpPoint(){
        return leftUpPoint;
    }

    public Rectangle2D getRectangle(){
        return rectangle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        initUi();
        graphics2D.draw(rectangle);
        if(click == 1){
            for(Ellipse2D e : conerS){
                graphics2D.fill(e);
            }
        }
    }


    private void initUi(){

        // create a point
        leftUpPoint = (Point2D) factory.createComponent("point");
        leftUpPoint.setLocation(rectangle.getX(), rectangle.getY());
        // use the point to create corner
        leftUpCorner = createCorners(leftUpPoint);
        conerS.add(leftUpCorner);
        // repeat
        leftDownPoint = (Point2D) factory.createComponent("point");
        leftDownPoint.setLocation(leftUpPoint.getX(), leftUpPoint.getY() + rectangle.getHeight());
        //
        leftDownCorner = createCorners(leftDownPoint);
        conerS.add(leftDownCorner);
        //
        rightUpPoint = (Point2D) factory.createComponent("point");
        rightUpPoint.setLocation(leftUpPoint.getX() + rectangle.getWidth(), leftUpPoint.getY());
        rightUpCorner = createCorners(rightUpPoint);
        conerS.add(rightUpCorner);
        //
        rightDownPoint = (Point2D)factory.createComponent("point");
        rightDownPoint.setLocation(leftUpPoint.getX() + rectangle.getWidth(), leftUpPoint.getY() + rectangle.getHeight());
        rightDownCorner = createCorners(rightDownPoint);
        conerS.add(rightDownCorner);
    }

    private Ellipse2D createCorners(Point2D p){
        Ellipse2D e = (Ellipse2D)factory.createComponent("elli");
        e.setFrame(p.getX() - 5, p.getY() - 5, 10 ,10);
        return e;
    }

    public Ellipse2D findPoints(Point2D p){
        for(Ellipse2D r : conerS){
            if(r.contains(p)){
                return r;
            }
        }
        return null;
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(rectangle.contains(e.getPoint())){
                if(e.getClickCount() == 1){
                    click = 1;
                    repaint();
                }
            }
            else{
                click = 0;
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }


    private class MouseMotionHandler implements MouseMotionListener {

        @Override
        public void mouseMoved(MouseEvent e) {
            if(rectangle.contains(e.getPoint())){
                stage = 1;
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else if(findPoints(e.getPoint()) != null){
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                if(leftUpCorner.contains(e.getPoint()))
                    stage = 2;
                if (leftDownCorner.contains(e.getPoint()))
                    stage = 4;
                if (rightUpCorner.contains(e.getPoint()))
                    stage = 6;
                if (rightDownCorner.contains(e.getPoint()))
                    stage = 8;
            }
            else{
                stage = 0;
                setCursor(Cursor.getDefaultCursor());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            conerS.clear();
            switch (stage){
                case 1:{
                    rectangle.setFrame(e.getX() - width / 2, e.getY() - height / 2, width, height);
                    break;
                }
                case 2:{
                    rectangle.setFrameFromDiagonal(e.getPoint(), rightDownPoint);
                    setMySize(rectangle.getWidth(), rectangle.getHeight());
                    break;
                }
                case 4:{
                    rectangle.setFrameFromDiagonal(e.getPoint(), rightUpPoint);
                    setMySize(rectangle.getWidth(), rectangle.getHeight());
                    break;
                }
                case 6:{
                    rectangle.setFrameFromDiagonal(e.getPoint(), leftDownPoint);
                    setMySize(rectangle.getWidth(), rectangle.getHeight());
                    break;
                }
                case 8: {
                    rectangle.setFrameFromDiagonal(e.getPoint(), leftUpPoint);
                    setMySize(rectangle.getWidth(), rectangle.getHeight());
                    break;
                }
            }
            repaint();
        }
    }


}
