package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.controllers;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.dto.FruitRequest;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.dto.FruitResponse;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.mapper.FruitControllerMapper;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.services.FruitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<FruitResponse> addFruit(@RequestBody FruitRequest request) {
        Fruit saved = service.addFruit(mapper.toFruit(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<FruitResponse>> getAllFruits() {
        List<FruitResponse> responses = service.getAllFruits().stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity
                .ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FruitResponse> getFruitById(@PathVariable Long id) {
        Fruit fruit = service.getFruitById(id);
        return ResponseEntity
                .ok(mapper.toResponse(fruit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FruitResponse> updateFruit(@PathVariable Long id, @RequestBody FruitRequest request) {
        Fruit updated = service.updateFruit(id, mapper.toFruit(request));
        return ResponseEntity
                .ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFruit(@PathVariable Long id) {
        service.deleteFruit(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}