package aui.lab1.springboot.pokemon.service;

import aui.lab1.springboot.pokemon.entity.Pokemon;
import aui.lab1.springboot.pokemon.entity.Trainer;
import aui.lab1.springboot.pokemon.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private TrainerRepository repository;

    @Autowired
    public TrainerService(TrainerRepository repository) {
        this.repository = repository;
    }

    public Optional<Trainer> find(String id) {
        return repository.find(id);
    }

    public List<Trainer> findAll() {
        return repository.findAll();
    }

    public void create(Trainer trainer) {
        repository.create(trainer);
    }

    public void delete(String trainer) {
        repository.delete(repository.find(trainer).orElseThrow());
    }
}
