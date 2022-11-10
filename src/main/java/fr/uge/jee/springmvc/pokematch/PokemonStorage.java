package fr.uge.jee.springmvc.pokematch;

import org.springframework.web.reactive.function.client.WebClient;

public class PokemonStorage {

    private final static String api = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=40" ;

    //private  final WebClient webClient ;
    public static PokemonList createListPokemons(WebClient webClient ){

        var pokemonsList = webClient.get()
                .uri(api)
                .retrieve()
                .bodyToMono(PokemonList.class)
                .block();

        return pokemonsList;
    }



}
