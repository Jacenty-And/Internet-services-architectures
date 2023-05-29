package aui.lab1.springboot.pokemon.controller;

import aui.lab1.springboot.pokemon.dto.GetPokemonsResponse;
import aui.lab1.springboot.pokemon.entity.Trainer;
import aui.lab1.springboot.pokemon.service.PokemonService;
import aui.lab1.springboot.pokemon.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/trainers/{name}/pokemons")
public class TrainerPokemonController {

    private PokemonService pokemonService;
    private TrainerService trainerService;

    @Autowired
    public TrainerPokemonController(PokemonService pokemonService, TrainerService trainerService) {
        this.pokemonService = pokemonService;
        this.trainerService = trainerService;
    }
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetPokemonsResponse> getPokemons(@PathVariable("name") String id) {
        Optional<Trainer> trainer = trainerService.find(id);
        return trainer.map(value -> ResponseEntity.ok(GetPokemonsResponse.entityToDtoMapper().apply(pokemonService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
