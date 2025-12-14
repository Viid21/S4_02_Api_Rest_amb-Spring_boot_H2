package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.services;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.exception.FruitNotFound;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.repository.FruitRepository;

public class FruitServiceImpl implements FruitService {
    private final FruitRepository repo;

    public FruitServiceImpl(FruitRepository repo) {
        this.repo = repo;
    }

    @Override
    public Fruit addFruit(Fruit fruit) {
        return repo.save(fruit);
    }

    @Override
    public Iterable<Fruit> getAllFruits() {
        return repo.findAll();
    }

    @Override
    public Fruit getFruitById(long id) {
        return repo.findById(id)
                .orElseThrow(FruitNotFound::new);
    }

    @Override
    public Fruit updateFruit(long id, Fruit fruit) {
        Fruit existing = getFruitById(id);
        existing.setName(fruit.getName());
        existing.setWeightInKilos(fruit.getWeightInKilos());

        return repo.save(existing);
    }

    @Override
    public void deleteFruit(long id) {
        repo.deleteById(getFruitById(id).getId());
    }
}
