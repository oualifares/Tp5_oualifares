package fr.uge.jee.springmvc.pokematch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class ControllerRegister {
    private final WebClient webClient ;
    private final PokemonList listPokemon ;
    private PokemonHashMapService Pokehash  ;
    private ImageService imgService ;
    private final ByteArrayOutputStream os = new ByteArrayOutputStream();

    public ControllerRegister(WebClient webClient,PokemonList pokemonList) {
        this.webClient = webClient;
        this.listPokemon = pokemonList;
        Pokehash = new PokemonHashMapService(listPokemon.Getpokemonlist());
        //imgService = new ImageService(listPokemon.Getpokemonlist(), pokList);
    }

    @Value("${size}")
    private int size ;

    @GetMapping("/register")
    public String RegisterForm(@Valid @ModelAttribute("User") User param){

        return "registerAttribute";

    }

    @PostMapping("/register")
    public String ReponseRegister(@Valid @ModelAttribute("User") User param,BindingResult result,Model model ) throws IOException {
        if (result.hasErrors()){
            return "registerAttribute";
        }
        else{
            Pokemon fetichPokemon = param.PokemonFetiche(listPokemon.Getpokemonlist());
            //String fetichePokemonUrl = fetichPokemon.getUrl();
            String fetichePokemonName = fetichPokemon.getName();


            var pokemonlist = param.incrementeFetiche(listPokemon.Getpokemonlist(),size,Pokehash) ;
            //var pokemon = PokemonStorage.mappingImgUrl(fetichePokemonUrl,webClient);

            model.addAttribute("pokemon",fetichePokemonName);

           // model.addAttribute("pokemonImg",pokemon.GetImg());
            /*var bufferByte = imgService.existImg(fetichPokemon,webClient);
            try
            {
                ImageIO.write(bufferByte, "png", os);
                var imageInByte = os.toByteArray();
                var urlImage =  Base64.getEncoder().encodeToString(imageInByte);

                model.addAttribute("pokemonImg",urlImage);
            }
            catch (final IOException ioe)
            {
                throw new UncheckedIOException(ioe);
            }*/

            model.addAttribute("pokemonlist",pokemonlist);

            return"registerAttribute";
        }
    }





}
