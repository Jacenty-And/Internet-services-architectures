package aui.lab1.springboot.pokemon.repository;

import aui.lab1.springboot.datastore.DataStore;
import aui.lab1.springboot.pokemon.entity.Trainer;
import aui.lab1.springboot.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class TrainerRepository implements Repository<Trainer, String> {

    private DataStore store;

    public TrainerRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Trainer> find(String id) {
        return store.findTrainer(id);
    }

    @Override
    public List<Trainer> findAll() {
        return store.findAllTrainers();
    }

    @Override
    public void create(Trainer entity) {
        store.createTrainer(entity);
    }

    @Override
    public void delete(Trainer entity) {
        store.deleteTrainer(entity.getName());
    }

    @Override
    public void update(Trainer entity) {
        throw new UnsupportedOperationException("Operation not implemented");
    }
}
