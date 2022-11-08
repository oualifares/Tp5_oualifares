package fr.uge.jee.springmvc.pokematch;

import java.util.HashMap;

public class Pokemon {

    private String name ;

    private String url ;

    private byte[] image ;
    private Sprites sprites ;

    public String GetImg(){
        return sprites.getFront_default();
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public float hash(){
        return Math.abs(name.hashCode());
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
