package aui.lab1.springboot.pokemon.repository;

import aui.lab1.springboot.pokemon.entity.Pokemon;
import aui.lab1.springboot.pokemon.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, String> {

    Optional<Trainer> findById(String id);
    List<Trainer> findAll();
}
