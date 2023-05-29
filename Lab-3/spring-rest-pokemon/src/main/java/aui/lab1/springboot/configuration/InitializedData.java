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
//        Trainer red = new Trainer("Red", 12);
        Trainer red = Trainer.builder().name("Red").age(12).build();
//        Trainer leaf = new Trainer("Leaf", 13);
        Trainer leaf = Trainer.builder().name("Leaf").age(13).build();
//        Trainer ethan = new Trainer("Ethan", 14);
        Trainer ethan = Trainer.builder().name("Ethan").age(14).build();

        trainerService.create(red);
        trainerService.create(leaf);
        trainerService.create(ethan);

        Pokemon bulbasaur = Pokemon.builder()
                .name("Bulbasaur")
                .level(5)
                .trainer(leaf)
                .build();
        Pokemon ivysaur = Pokemon.builder()
                .name("Ivysaur")
                .level(15)
                .trainer(leaf)
                .build();
        Pokemon venusaur = Pokemon.builder()
                .name("Venusaur")
                .level(25)
                .trainer(leaf)
                .build();
        Pokemon charmander = Pokemon.builder()
                .name("Charmander")
                .level(5)
                .trainer(red)
                .build();
//        Pokemon charmeleon = new Pokemon("Charmeleon", 15, red);
//        Pokemon charizard = new Pokemon("Charizard", 25, red);
//        Pokemon squirtle = new Pokemon("Squirtle", 5, ethan);
//        Pokemon wartortle = new Pokemon("Wartortle", 15, ethan);
//        Pokemon blastoise = new Pokemon("Blastoise", 25, ethan);

        pokemonService.create(bulbasaur);
        pokemonService.create(ivysaur);
        pokemonService.create(venusaur);
        pokemonService.create(charmander);
//        pokemonService.create(charmeleon);
//        pokemonService.create(charizard);
//        pokemonService.create(squirtle);
//        pokemonService.create(wartortle);
//        pokemonService.create(blastoise);
    }
}
