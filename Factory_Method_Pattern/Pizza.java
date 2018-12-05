/**
 * pizza 现在利用相关的工厂生产原料。所生产的原料依赖所用的工厂。
 * PIZZA 现在和区域原料之间解耦
 */

package Factory_Method_Pattern;

import java.util.ArrayList;

public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList toppings = new ArrayList();

    abstract void prepare();

    void bake(){

    }

    void cut(){

    }

    void box(){

    }

    public String getName() {
        return name;
    }
}

class NYStyleCheesePizza extends Pizza{
    PizzaIngredientFactory factory;
    public NYStyleCheesePizza(PizzaIngredientFactory factory){
        this.factory = factory;
    }

    @Override
    void prepare() {
        dough = factory.createDough();
        sauce = factory.createSauce();
    }
}

class ChicagoStyleCheesePizza extends Pizza{
    PizzaIngredientFactory factory;
    public ChicagoStyleCheesePizza(PizzaIngredientFactory factory){
        this.factory = factory;
    }

    @Override
    void prepare() {
        dough = factory.createDough();
        sauce = factory.createSauce();
    }

    @Override
    void box() {
        super.box();
    }
}
