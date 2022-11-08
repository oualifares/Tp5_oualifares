package fr.uge.jee.springmvc.reststudents;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestStudentController {

    private final Map<Long , Student> students = Map.of(1L,new Student("nico","steve")
            , 2L,new Student("fares","ouali")
            ,3L,new Student("fred","nick")
    );
    //pockedex creation d'une methode static pour configurer
    // factory(web client , limit)


   @GetMapping("/students")
   public List<Student> getStudent( )   {

       var studentInfo = new ArrayList<>(students.values());//depuis la hashmap

       if(studentInfo == null){

           throw new ResponseStatusException(HttpStatus.NOT_FOUND,("no student with "));

       }else{
           return studentInfo;
       }

   }


}
