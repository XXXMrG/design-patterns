package xie;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
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
            case "line":{
                return new Line2D.Double();
            }
        }

        return null;
    }
}
