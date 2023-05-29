package aui.lab1.springboot.pokemon.entity;

import java.io.Serializable;
import java.util.Objects;

public class Pokemon implements Serializable {

    private Long id;
    private String name;
    private int level;
    private Trainer trainer;

    public Pokemon(String name, int level, Trainer trainer) {
        this.name = name;
        this.level = level;
        this.trainer = trainer;
    }

    public Pokemon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return level == pokemon.level && id.equals(pokemon.id) && name.equals(pokemon.name) && trainer.equals(pokemon.trainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, trainer);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", trainer=" + trainer +
                '}';
    }
}
