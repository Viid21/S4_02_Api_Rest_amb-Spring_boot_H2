package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.controllers;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.dto.FruitRequest;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.dto.FruitResponse;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.mapper.FruitControllerMapper;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.services.FruitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits")
public class FruitController {
    private final FruitService service;
    private final FruitControllerMapper mapper;

    public FruitController(FruitService service, FruitControllerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public Fruit addFruit(@RequestBody FruitRequest request) {
        return service.addFruit(mapper.toFruit(request));
    }

    @GetMapping
    public List<FruitResponse> getAllFruits() {
        return service.getAllFruits().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public FruitResponse getFruitById(@PathVariable Long id) {
        return mapper.toResponse(service.getFruitById(id));
    }

    @PutMapping("/{id}")
    public FruitResponse updateFruit(@PathVariable Long id, @RequestBody Fruit fruit) {
        return mapper.toResponse(service.updateFruit(id, fruit));
    }

    @DeleteMapping("/{id}")
    public void deleteFruit(@PathVariable Long id) {
        service.deleteFruit(id);
    }
}
