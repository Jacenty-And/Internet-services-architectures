package aui.lab1.springboot.pokemon.service;

import aui.lab1.springboot.pokemon.entity.Pokemon;
import aui.lab1.springboot.pokemon.entity.Trainer;
import aui.lab1.springboot.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return repository.findById(id);
    }

    public List<Pokemon> findAll() { return repository.findAll(); }

    public List<Pokemon> findAll(Trainer trainer) {
        return repository.findAllByTrainer(trainer);
    }

    @Transactional
    public Pokemon create(Pokemon pokemon) {
        return repository.save(pokemon);
    }

    @Transactional
    public void update(Pokemon pokemon) {
        repository.save(pokemon);
    }

    @Transactional
    public void delete(Long pokemon) {
        repository.deleteById(pokemon);
    }

}
