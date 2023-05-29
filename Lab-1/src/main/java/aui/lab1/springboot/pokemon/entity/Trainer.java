package aui.lab1.springboot.pokemon.entity;

import java.io.Serializable;
import java.util.Objects;

public class Trainer implements Serializable {

    private String name;
    private int age;

    public Trainer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Trainer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return age == trainer.age && name.equals(trainer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
