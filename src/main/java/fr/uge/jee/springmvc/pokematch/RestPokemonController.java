package fr.uge.jee.springmvc.pokematch;


import fr.uge.jee.springmvc.reststudents.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*@RestController
public class RestPokemonController {

   private final PokemonList listPokemon ;
   public RestPokemonController(PokemonList listPokemon){
       this.listPokemon = listPokemon;
   }

    @GetMapping ("/pokemons")
    public List<Pokemon> PostPokemon(){
        return listPokemon.Getpokemonlist() ;
    }


}*/
