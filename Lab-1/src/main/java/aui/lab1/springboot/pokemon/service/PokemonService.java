package aui.lab1.springboot.pokemon.service;

import aui.lab1.springboot.pokemon.entity.Pokemon;
import aui.lab1.springboot.pokemon.entity.Trainer;
import aui.lab1.springboot.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private PokemonRepository repository;

    @Autowired
    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    public Optional<Pokemon> find(Long id) {
        return repository.find(id);
    }

    public List<Pokemon> findAll() { return repository.findAll(); }

    public List<Pokemon> findAll(Trainer trainer) {
        return repository.findAllByTrainer(trainer);
    }

    public void create(Pokemon pokemon) {
        repository.create(pokemon);
    }

    public void delete(Long pokemon) {
        repository.delete(repository.find(pokemon).orElseThrow());
    }

    public void delete(Trainer trainer) { repository.deleteAllByTrainer(trainer); }
}
