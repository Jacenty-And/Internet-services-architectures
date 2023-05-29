package aui.lab1.springboot.configuration;

import aui.lab1.springboot.trainer.entity.Trainer;
import aui.lab1.springboot.trainer.service.TrainerService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final TrainerService trainerService;

    public InitializedData(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostConstruct
    private synchronized void init() {
        Trainer red = Trainer.builder().name("Red").age(12).build();
        Trainer leaf = Trainer.builder().name("Leaf").age(13).build();
        Trainer ethan = Trainer.builder().name("Ethan").age(14).build();

        trainerService.create(red);
        trainerService.create(leaf);
        trainerService.create(ethan);
    }
}
