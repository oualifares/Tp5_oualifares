package fr.uge.jee.springmvc.pokematch;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Base64;

@Controller
public class DisplayImagesController {

    private final WebClient webClient ;
    private final ImageService imageService;

    private final PokemonList listPokemon ;

    public DisplayImagesController(ImageService imageService,WebClient webClient ,PokemonList listPokemon) {
        this.webClient = webClient ;
        this.imageService = imageService;
        this.listPokemon = listPokemon;
    }

    @GetMapping("/images/{name}")
    public void getImages(@PathVariable("name") String name, HttpServletResponse response) throws IOException {

        try
        {
            var bufferByte = imageService.existImg(name,webClient);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferByte, "png", os);

            var imageInByte = os.toByteArray();
            response.setContentType("image/png");
            response.getOutputStream().write(imageInByte);
        }
        catch ( IOException ioe)
        {
            throw new UncheckedIOException(ioe);
        }

    }
}
