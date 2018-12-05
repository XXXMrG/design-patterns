/**
 * 抽象工厂方法
 * edit by keith
 * 2018/12/5
 */

package Factory_Method_Pattern;

public interface PizzaIngredientFactory {
    String createDough();
    String createSauce();
    String createCheese();
}


class NYPizzaIngredientFactory implements PizzaIngredientFactory{
    @Override
    public String createCheese() {
        return null;
    }

    @Override
    public String createDough() {
        return null;
    }

    @Override
    public String createSauce() {
        return null;
    }

}
