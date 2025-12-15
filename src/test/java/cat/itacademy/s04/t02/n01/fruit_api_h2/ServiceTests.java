package cat.itacademy.s04.t02.n01.fruit_api_h2;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.repository.FruitRepository;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.services.FruitServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServiceTests {
    @Mock
    private FruitRepository repo;

    @InjectMocks
    private FruitServiceImpl service;

    @Test
    void addFruit_shouldAddFruitIntoPersistence() {
        Fruit fruit = new Fruit("Apple", 1);
        when(repo.save(any(Fruit.class))).thenReturn(fruit);

        Fruit result = service.addFruit(fruit);

        assertEquals("Apple", result.getName());
        verify(repo, times(1)).save(any(Fruit.class));
    }

    @Test
    void getAllFruits_shouldReturnAllExistingTasks() {
        List<Fruit> fruits = List.of(
                new Fruit("Apple", 1),
                new Fruit("Apple2", 1)
        );
        when(repo.findAll()).thenReturn(fruits);

        List<Fruit> result = service.getAllFruits();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Apple", result.get(0).getName());
        assertEquals("Apple2", result.get(1).getName());
        verify(repo, times(1)).findAll();
    }

    @Test
    void getFruitById_shouldLaunchExceptionIfFruitNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.of(new Fruit("Apple", 1)));
        when(repo.findById(2L)).thenReturn(Optional.empty());

        Fruit result = service.getFruitById(1L);

        assertEquals("Apple", result.getName());
        assertThrows(FruitNotFoundException.class, () -> service.getFruitById(2L));
    }

    @Test
    void updateFruit_shouldModifyAnExistingFruit() {
        Fruit existing = new Fruit("Apple", 1);
        Fruit updatedData = new Fruit("Apple2", 5);
        Fruit saved = new Fruit("Apple2", 5);

        when(repo.findById(existing.getId())).thenReturn(Optional.of(existing));
        when(repo.save(any(Fruit.class))).thenReturn(saved);

        Fruit result = service.updateFruit(existing.getId(), updatedData);

        assertEquals("Fruit{id=" + result.getId() + ", name='Apple2', weightInKilos=5}", result.toString());

        verify(repo, times(1)).findById(existing.getId());
        verify(repo, times(1)).save(any(Fruit.class));
    }

    @Test
    void deleteFruit_shouldCreateAndReturnFruit() {
        Fruit existing = new Fruit("Apple", 1);
        when(repo.findById(existing.getId())).thenReturn(Optional.of(existing));

        service.deleteFruit(existing.getId());

        verify(repo, times(1)).findById(existing.getId());
        verify(repo, times(1)).deleteById(existing.getId());
    }
}