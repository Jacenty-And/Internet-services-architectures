package aui.lab1.springboot.configuration;

import aui.lab1.springboot.pokemon.entity.Pokemon;
import aui.lab1.springboot.pokemon.entity.Trainer;
import aui.lab1.springboot.pokemon.service.PokemonService;
import aui.lab1.springboot.pokemon.service.TrainerService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final PokemonService pokemonService;
    private final TrainerService trainerService;

    public InitializedData(PokemonService pokemonService, TrainerService trainerService) {
        this.pokemonService = pokemonService;
        this.trainerService = trainerService;
    }

    @PostConstruct
    private synchronized void init() {
        Trainer red = new Trainer("Red", 12);
        Trainer leaf = new Trainer("Leaf", 13);
        Trainer ethan = new Trainer("Ethan", 14);

        trainerService.create(red);
        trainerService.create(leaf);
        trainerService.create(ethan);

        Pokemon bulbasaur = new Pokemon("Bulbasaur", 5, leaf);
        Pokemon ivysaur = new Pokemon("Ivysaur", 15, leaf);
        Pokemon venusaur = new Pokemon("Venusaur", 25, leaf);
        Pokemon charmander = new Pokemon("Charmander", 5, red);
        Pokemon charmeleon = new Pokemon("Charmeleon", 15, red);
        Pokemon charizard = new Pokemon("Charizard", 25, red);
        Pokemon squirtle = new Pokemon("Squirtle", 5, ethan);
        Pokemon wartortle = new Pokemon("Wartortle", 15, ethan);
        Pokemon blastoise = new Pokemon("Blastoise", 25, ethan);

        pokemonService.create(bulbasaur);
        pokemonService.create(ivysaur);
        pokemonService.create(venusaur);
        pokemonService.create(charmander);
        pokemonService.create(charmeleon);
        pokemonService.create(charizard);
        pokemonService.create(squirtle);
        pokemonService.create(wartortle);
        pokemonService.create(blastoise);
    }
}
