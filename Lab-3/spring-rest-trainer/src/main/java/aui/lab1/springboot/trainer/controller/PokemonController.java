package aui.lab1.springboot.trainer.controller;

import aui.lab1.springboot.trainer.dto.GetPokemonResponse;
import aui.lab1.springboot.trainer.dto.GetPokemonsResponse;
import aui.lab1.springboot.trainer.dto.PostPokemonRequest;
import aui.lab1.springboot.trainer.dto.PutPokemonRequest;
import aui.lab1.springboot.trainer.entity.Pokemon;
import aui.lab1.springboot.trainer.repository.PokemonRepository;
import aui.lab1.springboot.trainer.service.PokemonService;
import aui.lab1.springboot.trainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/pokemons")
public class PokemonController {

    private PokemonService pokemonService;
    private TrainerService trainerService;

    @Autowired
    public PokemonController(PokemonService pokemonService, TrainerService trainerService) {
        this.pokemonService = pokemonService;
        this.trainerService = trainerService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetPokemonsResponse> getPokemons() {
        return ResponseEntity
                .ok(GetPokemonsResponse.entityToDtoMapper().apply(pokemonService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetPokemonResponse> getPokemon(@PathVariable("id") long id) {
        return pokemonService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetPokemonResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<Void> postPokemon(@RequestBody PostPokemonRequest request, UriComponentsBuilder builder) {
        Pokemon pokemon = PostPokemonRequest
                .dtoToEntityMapper(name -> trainerService.find(name).orElseThrow())
                .apply(request);
        pokemon = pokemonService.create(pokemon);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "pokemons", "{id}")
                        .buildAndExpand(pokemon.getId()).toUri())
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable("id") long id) {
        Optional<Pokemon> pokemon = pokemonService.find(id);
        if (pokemon.isPresent()) {
            pokemonService.delete(pokemon.get().getId());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putPokemon(@RequestBody PutPokemonRequest request, @PathVariable("id") long id) {
        Optional<Pokemon> pokemon = pokemonService.find(id);
        if (pokemon.isPresent()) {
            PutPokemonRequest.dtoToEntityUpdater().apply(pokemon.get(), request);
            pokemonService.update(pokemon.get());
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
