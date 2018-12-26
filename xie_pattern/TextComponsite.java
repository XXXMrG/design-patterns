package xie;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class TextComponsite extends MyComponent implements Observer{

    private Rectangle2D rectangle, rectangleUp, rectangleDown;
    private EditorPanel dialog = null;
    String message;
    Font f;
    private Frame ParentFrame;
    private ComponentFactory factory;
    private double mywidth, myheight;

    public TextComponsite(Point2D startPoint, double width, double height, Frame frame){
        super(startPoint, width, height);
        factory = new ComponentFactory();
        ParentFrame = frame;
        message = "Hello, World!";
        f = new Font("Serif", Font.BOLD, 25);
        this.rectangle = super.getRectangle();
        mywidth = 100;
        myheight = f.getSize();
        rectangleUp = (Rectangle2D) factory.createComponent("rect");
        rectangleUp.setFrame(0, 0, mywidth, myheight);
        rectangleDown = (Rectangle2D) factory.createComponent("rect");
        rectangleDown.setFrame(0, 0, mywidth, myheight);
        addMouseListener(new MouseHandler());
    }


    @Override
    protected void paintComponent(Graphics g) {
        int stringMessageX = (int) (rectangleUp.getX());
        int stringMessageY = (int) (rectangleUp.getY() + f.getSize());
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.draw(rectangleUp);
        graphics2D.setFont(f);
        graphics2D.drawString(message, stringMessageX, stringMessageY);
        stringMessageX = (int) (rectangleDown.getX());
        stringMessageY = (int) (rectangleDown.getY() + f.getSize());
        graphics2D.draw(rectangleDown);
        graphics2D.setFont(f);
        graphics2D.drawString("0 米", stringMessageX, stringMessageY);
    }

    @Override
    public void update(MyComponent component, int size) {
        super.conerS.clear();
        rectangleUp.setFrame(component.getRectangle().getX(), component.getRectangle().getY(), mywidth, myheight);
        rectangleDown.setFrame(component.getRectangle().getX(), component.getRectangle().getY() + component.getRectangle().getHeight(), mywidth, myheight);
        message = component.getRectangle().getHeight() + "米";
        repaint();
    }


    private class MouseHandler implements MouseListener {
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(rectangleUp.contains(e.getPoint()) || rectangleDown.contains(e.getPoint())){
                if(e.getClickCount() >= 2)
                    isShowDialog();
            }
        }


    }

    public void isShowDialog() {
        if (dialog == null)
            dialog = new EditorPanel();

        dialog.setEditor(message);

        // pop up dialog
        if (dialog.showDialog(ParentFrame, "属性编辑"))
        {
            // if accepted, retrieve user input
            Editor editor = dialog.getEditor();
            message = editor.getContent();
            if (editor.getFontStyle().equals("PLAIN"))
                f = new Font("Serif", Font.PLAIN, editor.getFontSize());
            else if (editor.getFontStyle().equals("BOLD"))
                f = new Font("Serif", Font.BOLD, editor.getFontSize());
            else if (editor.getFontStyle().equals("ITALIC"))
                f = new Font("Serif", Font.ITALIC, editor.getFontSize());
            else if (editor.getFontStyle().equals("CENTER_BASELINE"))
                f = new Font("Serif", Font.CENTER_BASELINE, editor.getFontSize());

            repaint();
        }
    }


}
