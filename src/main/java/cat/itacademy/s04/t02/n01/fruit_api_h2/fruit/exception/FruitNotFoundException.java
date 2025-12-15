package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.exception;

public class FruitNotFoundException extends RuntimeException {
    public FruitNotFoundException() {
        super("The fruit can not be found.");
    }
}
