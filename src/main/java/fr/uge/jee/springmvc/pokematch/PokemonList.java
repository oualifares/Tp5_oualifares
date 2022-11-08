package fr.uge.jee.springmvc.pokematch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PokemonList {

    private Long count ;


    private List<Pokemon> results = new ArrayList<Pokemon>();


    public List<Pokemon> Getpokemonlist(){return results;}

    public Pokemon getPokemon(String name){
        for(var pok:results){
            if(pok.getName().equals(name)){
                return pok;
            }
        }
        return null;

    }


    public void setCount(Long count) {
        this.count = count;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }
}
