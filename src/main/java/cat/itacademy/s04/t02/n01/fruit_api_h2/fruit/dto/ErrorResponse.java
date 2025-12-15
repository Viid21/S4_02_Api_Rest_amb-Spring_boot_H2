package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.dto;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path
) {
}