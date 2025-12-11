package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.services;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model.Fruit;

import java.util.List;

public interface FruitService {
    Fruit addFruit(Fruit fruit);
    List<Fruit> getAllFruits();
    Fruit getFruitById(long id);
    Fruit updateFruit(long id ,Fruit fruit);
    void deleteFruit(long id);
}
