package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.services;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.exception.FruitsListEmptyException;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImpl implements FruitService{
    private final FruitRepository repo;

    public FruitServiceImpl(FruitRepository repo) {
        this.repo = repo;
    }

    @Override
    public Fruit addFruit(Fruit fruit) {
        return repo.save(fruit);
    }

    @Override
    public List<Fruit> getAllFruits() {
        List<Fruit> fruits = repo.findAll();
        if(!fruits.isEmpty()){
            return fruits;
        }else{
            throw new FruitsListEmptyException();
        }
    }

    @Override
    public Fruit getFruitById(long id) {
        return repo.findById(id)
                .orElseThrow(FruitNotFoundException::new);
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
