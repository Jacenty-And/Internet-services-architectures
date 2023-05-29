package aui.lab1.springboot.trainer.repository;

import aui.lab1.springboot.trainer.entity.Pokemon;
import aui.lab1.springboot.trainer.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    Optional<Pokemon> findById(Long id);
    List<Pokemon> findAll();
    List<Pokemon> findAllByTrainer(Trainer trainer);
}
