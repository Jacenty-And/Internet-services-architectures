package aui.lab1.springboot;

import aui.lab1.springboot.pokemon.entity.Pokemon;
import aui.lab1.springboot.pokemon.entity.Trainer;
import aui.lab1.springboot.pokemon.service.PokemonService;
import aui.lab1.springboot.pokemon.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private PokemonService pokemonService;
    private TrainerService trainerService;

    @Autowired
    public CommandLine(PokemonService pokemonService, TrainerService trainerService) {
        this.pokemonService = pokemonService;
        this.trainerService = trainerService;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        while(exit != true) {
            System.out.println("      -=Menu=-    ");
            System.out.println("1. Add new Trainer");
            System.out.println("2. Print all Trainers");
            System.out.println("3. Print Trainer");
            System.out.println("4. Delete Trainer");
            System.out.println("5. Add new Pokemon");
            System.out.println("6. Print all Pokemon");
            System.out.println("7. Print Pokemon");
            System.out.println("8. Delete Pokemon");
            System.out.println("9. Exit");
            System.out.print("Your choice: ");
            int number = input.nextInt();
            String name;
            Long id;
            int age, level;
            switch(number) {
                case 1: // 1. Add new Trainer
                    System.out.print("Enter trainers name: ");
                    name = input.next();
                    System.out.print("Enter trainers age: ");
                    age = input.nextInt();
                    Trainer newTrainer = new Trainer(name, age);
                    try {
                        trainerService.create(newTrainer);
                    }
                    catch(IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2: // 2. Print all Trainers
                    trainerService.findAll().forEach(
                            trainer -> {
                                System.out.println(trainer);
                                pokemonService.findAll(trainer).forEach(pokemon -> System.out.println("-" + pokemon));
                            }
                    );
                    break;
                case 3: // 3. Print Trainer
                    System.out.print("Enter trainers name: ");
                    name = input.next();
                    trainerService.find(name).ifPresentOrElse(
                            trainer -> {
                                System.out.println(trainer);
                                pokemonService.findAll(trainer).forEach(pokemon -> System.out.println("-" + pokemon));
                            },
                            () -> System.out.println("The trainer named \"" + name + "\" does not exist")
                    );
                    break;
                case 4: // 4. Delete Trainer
                    System.out.print("Enter trainers name: ");
                    name = input.next();
                    try {
                        trainerService.find(name).ifPresent(trainer -> pokemonService.delete(trainer));
                        trainerService.delete(name);
                    }
                    catch(Exception e) {
                        System.out.println("The trainer named \"" + name + "\" does not exist");
                    }
                    break;
                case 5: // 5. Add new Pokemon
                    System.out.print("Enter pokemon name: ");
                    name = input.next();
                    System.out.print("Enter pokemon level: ");
                    level = input.nextInt();
                    System.out.print("Enter trainers name: ");
                    String trainersName = input.next();
                    trainerService.find(trainersName).ifPresentOrElse(
                            trainer -> {
                                Pokemon newPokemon = new Pokemon(name, level, trainer);
                                pokemonService.create(newPokemon);
                            },
                            () -> System.out.println("The trainer named \"" + trainersName + "\" does not exist")
                    );
                    break;
                case 6: // 6. Print all Pokemon
                    pokemonService.findAll().forEach(System.out::println);
                    break;
                case 7: // 7. Print Pokemon
                    System.out.print("Enter pokemon id: ");
                    id = input.nextLong();
                    pokemonService.find(id).ifPresentOrElse(
                            pokemon -> System.out.println(pokemon),
                            () -> System.out.println("The pokemon with id \"" + id + "\" does not exist")
                    );
                    break;
                case 8: // 8. Delete Pokemon
                    System.out.print("Enter pokemon id: ");
                    id = input.nextLong();
                    try {
                        pokemonService.delete(id);
                    }
                    catch(Exception e) {
                            System.out.println("The pokemon with id \"" + id + "\" does not exist");
                    }
                    break;
                case 9: // 9. Exit
                    exit = true;
                    break;
                default:
                    System.out.println("Illegal value: " + number);
            }
        }
    }
}
