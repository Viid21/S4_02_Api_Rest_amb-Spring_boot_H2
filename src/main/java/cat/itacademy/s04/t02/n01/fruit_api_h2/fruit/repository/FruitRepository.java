package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.repository;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model.Fruit;

import java.util.List;
import java.util.Optional;

public interface FruitRepository {
    Fruit save(Fruit fruit);
    List<Fruit> getAll();
    Optional<Fruit> findById(long id);
    void deleteById(long id);
}
