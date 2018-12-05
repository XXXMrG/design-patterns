package Factory_Method_Pattern;

public class PizzaTestDrive {
    public static void main (String[] args){
        PizzaStore nystore = new NYPizzaStore();
        Pizza pizza = nystore.orderPizza("xxx");
        pizza.getName();
    }
}
