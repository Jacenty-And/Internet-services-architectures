package aui.lab1.springboot.trainer.service;

import aui.lab1.springboot.trainer.entity.Trainer;
import aui.lab1.springboot.trainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("TrainerTrainerService")
public class TrainerService {

    private TrainerRepository repository;

    @Autowired
    public TrainerService(TrainerRepository repository) {
        this.repository = repository;
    }

    public Optional<Trainer> find(String id) {
        return repository.findById(id);
    }

    public List<Trainer> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Trainer create(Trainer trainer) {
        return repository.save(trainer);
    }

    @Transactional
    public void delete(Trainer trainer) { repository.delete(trainer); }

}
