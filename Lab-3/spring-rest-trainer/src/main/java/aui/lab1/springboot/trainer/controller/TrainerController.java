package aui.lab1.springboot.trainer.controller;

import aui.lab1.springboot.trainer.dto.GetTrainerResponse;
import aui.lab1.springboot.trainer.dto.GetTrainersResponse;
import aui.lab1.springboot.trainer.dto.PostTrainerRequest;
import aui.lab1.springboot.trainer.entity.Trainer;
import aui.lab1.springboot.trainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/trainers")
public class TrainerController {

    private TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetTrainersResponse> getTrainers() {
        return ResponseEntity
                .ok(GetTrainersResponse.entityToDtoMapper().apply(trainerService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetTrainerResponse> getTrainer(@PathVariable("id") String id) {
        return trainerService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetTrainerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping()
    public ResponseEntity<Void> postTrainer(@RequestBody PostTrainerRequest request, UriComponentsBuilder builder) {
        Trainer trainer = PostTrainerRequest.dtoToEntityMapper().apply(request);
        trainerService.create(trainer);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "trainers", "{name}")
                        .buildAndExpand(trainer.getName()).toUri())
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable("id") String id) {
        Optional<Trainer> trainer = trainerService.find(id);
        if (trainer.isPresent()) {
            trainerService.delete(trainer.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
