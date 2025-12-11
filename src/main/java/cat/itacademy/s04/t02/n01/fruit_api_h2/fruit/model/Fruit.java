package cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int weightInKilos;

    public Fruit(String name, int weightInKilos) {
        this.name = name;
        this.weightInKilos = weightInKilos;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeightInKilos() {
        return weightInKilos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeightInKilos(int weightInKilos) {
        this.weightInKilos = weightInKilos;
    }
}
