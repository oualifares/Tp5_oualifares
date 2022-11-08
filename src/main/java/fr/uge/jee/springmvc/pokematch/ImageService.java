package fr.uge.jee.springmvc.pokematch;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

@Component
public class ImageService {

    private final HashMap<String,BufferedImage> pokemonsImg= new HashMap<String, BufferedImage>();
    private final PokemonList pokList ;

    public ImageService(PokemonList pokemonList){
        this.pokList = pokemonList;

        for (var pok : this.pokList.Getpokemonlist()) {
            pokemonsImg.put(pok.getName(), null);
        }

    }


    public BufferedImage existImg(String pokemon, WebClient webClient) throws IOException {
       if(pokemonsImg.get(pokemon)!=null ){//check if not empty
            return pokemonsImg.get(pokemon);
       }else{
           var urlPokemon = pokList.getPokemon(pokemon).getUrl();
           var url = PokemonStorage.mappingImgUrl(urlPokemon,webClient).GetImg() ;
           File imgPath = new File(url);
           BufferedImage bufferedImage = ImageIO.read(new URL(url));
           pokemonsImg.put(pokemon,bufferedImage);
           return bufferedImage ;
        }
    }


}
