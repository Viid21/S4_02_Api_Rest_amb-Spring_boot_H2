package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.exception;

public class FruitNotFound extends RuntimeException {
    public FruitNotFound() {
        super("The fruit can not be found.");
    }
}
