package aui.lab1.springboot.datastore;

import aui.lab1.springboot.pokemon.entity.Pokemon;
import aui.lab1.springboot.pokemon.entity.Trainer;
import aui.lab1.springboot.serialization.CloningUtility;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log
@Component
public class DataStore {

    private Set<Pokemon> pokemons = new HashSet<>();

    private Set<Trainer> trainers = new HashSet<>();

    public synchronized List<Pokemon> findAllPokemons() {
        return pokemons.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized Optional<Pokemon> findPokemon(Long id) {
        return pokemons.stream()
                .filter(pokemon -> pokemon.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createPokemon(Pokemon pokemon) throws IllegalArgumentException {
        pokemon.setId(findAllPokemons().stream().mapToLong(Pokemon::getId).max().orElse(0) + 1);
        pokemons.add(pokemon);
    }

    public synchronized void deletePokemon(Long id) throws IllegalArgumentException {
        findPokemon(id).ifPresentOrElse(
                original -> pokemons.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The pokemon with id \"%d\" does not exist", id)
                    );
                }
        );
    }

    public synchronized List<Trainer> findAllTrainers() {
        return new ArrayList<>(trainers);
    }

    public synchronized Optional<Trainer> findTrainer(String name) {
        return trainers.stream()
                .filter(trainer -> trainer.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createTrainer(Trainer trainer) throws IllegalArgumentException {
        findTrainer(trainer.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The trainer name \"%s\" in not unique", trainer.getName())
                    );
                },
                () -> trainers.add(trainer)
        );
    }

    public synchronized void deleteTrainer(String name) throws IllegalArgumentException {
        findTrainer(name).ifPresentOrElse(
                original -> trainers.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The trainer with name \"%s\" does not exist", name)
                    );
                }
        );
    }

    public Stream<Pokemon> getPokemonStream() {
        return pokemons.stream();
    }
}
