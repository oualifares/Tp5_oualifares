package fr.uge.jee.springmvc.pokematch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Base64;

@Controller
public class ControllerRegister {
    private final WebClient webClient ;
    private final PokemonList listPokemon ;
    private PokemonHashMapService Pokehash  ;
    //private final  ImageService imgService ;


    public ControllerRegister(WebClient webClient,PokemonList pokemonList ,ImageService imageService) {
        this.webClient = webClient;
        this.listPokemon = pokemonList;
        //this.imgService = imageService;
        Pokehash = new PokemonHashMapService(listPokemon.Getpokemonlist());
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
            String fetichePokemonName = fetichPokemon.getName();


            var pokemonlist = param.incrementeFetiche(listPokemon.Getpokemonlist(),size,Pokehash) ;

            model.addAttribute("pokemon",fetichePokemonName);

            /********** premiere methode pour la creation du cache pour les images (sans controller)
            var bufferByte = imgService.existImg(fetichePokemonName,webClient);
            try
            {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(bufferByte, "png", os);
                var imageInByte = os.toByteArray();
                var urlImage =  Base64.getEncoder().encodeToString(imageInByte);

                model.addAttribute("pokemonImg",urlImage);
            }
            catch (final IOException ioe)
            {
                throw new UncheckedIOException(ioe);
            }**********/

            model.addAttribute("pokemonlist",pokemonlist);

            return"registerAttribute";
        }
    }





}
