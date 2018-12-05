/**
 * Factory method in head first
 * edit by keith
 * 2018/12/5
 */
package Factory_Method_Pattern;

public abstract class PizzaStore {
        public Pizza orderPizza(String type){
            Pizza pizza;
            pizza = createPizza(type);
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            return pizza;
        }
        // 工厂方法，由子类重新实现。工厂方法将客户和实际创建具体产品的代码解耦。
        protected abstract Pizza createPizza(String type);
}

class NYPizzaStore extends PizzaStore{
    @Override
    protected Pizza createPizza(String type) {
        if(type.equals("xxx")){
            //do something and return a type pizza
        }
        // ......
        return null;
    }
}


class xxxPizzaStore extends PizzaStore{
    @Override
    protected Pizza createPizza(String type) {
        PizzaIngredientFactory factory = new NYPizzaIngredientFactory();
        // use the factory to create types of pizza and return
        return null;
    }
}
