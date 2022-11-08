package fr.uge.jee.springmvc.pokematch;

import javax.validation.constraints.Pattern;
import java.util.List;


public class User {

    @Pattern(regexp = "[a-zA-Z]*",message = "firstname must  has only lowercase or uppercase letters.")
    private String firstname ;
    @Pattern(regexp = "[a-zA-Z]*",message = "lastname must  has only lowercase or uppercase letters.")
    private String lastname ;

    public User(){}

    public User( String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;

    }


    public Pokemon PokemonFetiche(List<Pokemon> pokemonList){

        Pokemon fetiche = pokemonList.get(0); ;
        for(int i = 1 ;i< pokemonList.size();i++){
            if(Math.abs(pokemonList.get(i).hash() - this.hash())< Math.abs(fetiche.hash() - this.hash())){
                fetiche = pokemonList.get(i);
            }
        }
        return fetiche ;
    }


    public List<Pokemon> incrementeFetiche(List<Pokemon> pokemonList, int size, PokemonHashMapService PokemonHash){

        var pokemonFetiche = PokemonFetiche(pokemonList) ;
        PokemonHash.incrementCounter(pokemonFetiche);
        return PokemonHash.sortHash(size) ;

    }

    public float hash(){

        return lastname.hashCode() + firstname.hashCode() ;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return " User: "  + firstname  + " &&& " + lastname ;
    }
}
