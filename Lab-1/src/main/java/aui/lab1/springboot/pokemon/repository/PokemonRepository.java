package aui.lab1.springboot.pokemon.repository;

import aui.lab1.springboot.datastore.DataStore;
import aui.lab1.springboot.pokemon.entity.Pokemon;
import aui.lab1.springboot.pokemon.entity.Trainer;
import aui.lab1.springboot.repository.Repository;
import aui.lab1.springboot.serialization.CloningUtility;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class PokemonRepository implements Repository<Pokemon, Long> {

    private DataStore store;

    @Autowired
    public PokemonRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Pokemon> find(Long id) {
        return store.findPokemon(id);
    }

    @Override
    public List<Pokemon> findAll() {
        return store.findAllPokemons();
    }

    @Override
    public void create(Pokemon entity) {
        store.createPokemon(entity);
    }

    @Override
    public void delete(Pokemon entity) {
        store.deletePokemon(entity.getId());
    }

    @Override
    public void update(Pokemon entity) {
        throw new UnsupportedOperationException("Operation not implemented");
    }

    public List<Pokemon> findAllByTrainer(Trainer trainer) {
        return store.getPokemonStream()
                .filter(pokemon -> pokemon.getTrainer().equals(trainer))
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public void deleteAllByTrainer(Trainer trainer) {
        findAllByTrainer(trainer).forEach(pokemon -> store.deletePokemon(pokemon.getId()));
    }
}
