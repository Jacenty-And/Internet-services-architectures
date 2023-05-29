package aui.lab1.springboot.trainer.event.repository;

import aui.lab1.springboot.trainer.entity.Trainer;
import aui.lab1.springboot.trainer.event.dto.PostTrainerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class TrainerEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public TrainerEventRepository(@Value("http://localhost:8082/api/") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Trainer trainer) {
        restTemplate.delete("trainers/{name}", trainer.getName());
    }

    public void create(Trainer trainer) {
        restTemplate.postForLocation("/trainers", PostTrainerRequest.entityToDtoMapper().apply(trainer));
    }
}
