package fr.uge.jee.springmvc.pokematch;

import java.util.*;

public class PokemonHashMapService {

    private final Object o = new Object();

    private HashMap<Pokemon ,Integer> countFetichPokemon = new HashMap<Pokemon,Integer>();

    public PokemonHashMapService(List<Pokemon> pokemonList){
        for(var pok : pokemonList){
            countFetichPokemon.put(pok,0);
        }
    }

    public void incrementCounter(Pokemon pokemon){

        if (countFetichPokemon.containsKey(pokemon)) {
            synchronized (o) {
                countFetichPokemon.put(pokemon, countFetichPokemon.get(pokemon) + 1);
            }
        } else {
            countFetichPokemon.put(pokemon, 1);
        }

    }

    public List<Pokemon> sortHash(int size){
        ArrayList<Pokemon>resultat = new ArrayList<Pokemon>();

        LinkedHashMap<Pokemon, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (Map.Entry<Pokemon, Integer> entry : countFetichPokemon.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list,Collections.reverseOrder());
        for (int num : list) {
            for (Map.Entry<Pokemon, Integer> entry : countFetichPokemon.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }

        Set<Pokemon> Keyset=sortedMap.keySet();
        Pokemon[] keyArray= Keyset.toArray(new Pokemon[Keyset.size()]);

        for(int i =0;i<size;i++){
            resultat.add( keyArray[i]);
        }

        return resultat;

    }

    public  HashMap<Pokemon, Integer> getCountFetichPokemon() {
        return countFetichPokemon;
    }
}
