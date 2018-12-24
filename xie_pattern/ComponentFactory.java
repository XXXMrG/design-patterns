package xie;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;

public class ComponentFactory extends AbstractComponentFactory {

    @Override
    public Object createComponent(String str) {

        switch (str){
            case "rect":{
                return new Rectangle2D.Double();
            }
            case "point":{
                return new Point2D.Double();
            }
            case "elli":{
                return new Ellipse2D.Double();
            }
        }

        return null;
    }

    public JComponent createComponent(String str, String type){

        switch (str){
            case "point":{
                return new PointComponent();
            }
        }

        return null;
    }
}
