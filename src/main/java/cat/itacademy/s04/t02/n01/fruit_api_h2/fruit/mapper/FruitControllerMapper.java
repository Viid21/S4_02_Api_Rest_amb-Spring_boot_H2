package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.mapper;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.dto.FruitRequest;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.dto.FruitResponse;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model.Fruit;

public class FruitControllerMapper {
    public Fruit toFruit(FruitRequest request){
        return new Fruit(request.name(), request.weightInKilos());
    }

    public FruitResponse toResponse(Fruit fruit){
        return new FruitResponse(fruit.getId(), fruit.getName(), fruit.getWeightInKilos());
    }
}
