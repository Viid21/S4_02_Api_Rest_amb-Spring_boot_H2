package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.exception;

public class FruitsListEmptyException extends RuntimeException {
    public FruitsListEmptyException() {
        super("The are no existing fruits.");
    }
}
