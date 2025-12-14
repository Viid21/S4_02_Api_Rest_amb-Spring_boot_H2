package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.repository;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model.Fruit;
import org.springframework.data.repository.CrudRepository;

public interface FruitRepository extends CrudRepository<Fruit, Long> {
}
