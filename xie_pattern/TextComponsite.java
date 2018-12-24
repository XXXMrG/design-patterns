package xie;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class TextComponsite extends MyComponent implements Observer{

    private Rectangle2D rectangle;
    private EditorPanel dialog = null;
    String messag;
    Font f;
    private Frame ParentFrame;

    public TextComponsite(Point2D startPoint, double width, double height, Frame frame){
        super(startPoint, width, height);
        ParentFrame = frame;
        message = "Hello, World!";
        f = new Font("Serif", Font.BOLD, 10);
        this.rectangle = super.getRectangle();
        addMouseListener(new MouseHandler());
    }


    @Override
    protected void paintComponent(Graphics g) {
        int stringMessageX = (int) (rectangle.getX());
        int stringMessageY = (int) (rectangle.getY() + f.getSize());
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setFont(f);
        graphics2D.drawString(message, stringMessageX, stringMessageY);
    }

    @Override
    public void update(Point2D point) {
        super.conerS.clear();
        rectangle.setFrame(point.getX(), point.getY(), super.getMyWidth(), super.getMyHeight());
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
            if(rectangle.contains(e.getPoint())){
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
