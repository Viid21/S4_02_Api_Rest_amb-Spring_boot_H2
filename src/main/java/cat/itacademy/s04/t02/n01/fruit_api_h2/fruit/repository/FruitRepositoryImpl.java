package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.repository;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model.Fruit;

import java.util.List;
import java.util.Optional;

public class FruitRepositoryImpl implements FruitRepository{
    @Override
    public Fruit save(Fruit fruit) {
        return null;
    }

    @Override
    public List<Fruit> getAll() {
        return List.of();
    }

    @Override
    public Optional<Fruit> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {

    }
}
