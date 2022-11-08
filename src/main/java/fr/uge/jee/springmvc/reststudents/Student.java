package fr.uge.jee.springmvc.reststudents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    @JsonIgnore
    private int id ;

    @JsonProperty("first_name")
    private String firstName ;
    @JsonProperty ("last_name")
    private String lastName ;

    public Student(String firstname ,String lastname){

        this.firstName= firstname ;
        this.lastName = lastname ;

    }
    public Student(){

    }

    @Override
    public String toString() {
        return firstName+ "  " + lastName;
    }
}
