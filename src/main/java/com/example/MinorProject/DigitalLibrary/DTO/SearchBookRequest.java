package com.example.MinorProject.DigitalLibrary.DTO;

import com.example.MinorProject.DigitalLibrary.Entity.Genre;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SearchBookRequest {
    @NotBlank
    private String searchKey;

    @NotBlank
    private  String searchValue;

    @NotBlank
    private  String operator;

    private boolean available;

    private static Set<String> allowedKeys = new HashSet<>();
    private  static Map<String , List<String>> allowedOperatorsMap = new HashMap<>();

    SearchBookRequest (){
        allowedKeys.addAll(Arrays.asList("name", "author_name", "genre", "pages", "id"));

        allowedOperatorsMap.put("name", Arrays.asList("=", "like"));
        allowedOperatorsMap.put("author_name", Arrays.asList("="));
        allowedOperatorsMap.put("pages", Arrays.asList("<", "<=", ">", ">=", "="));
        allowedOperatorsMap.put("genre", Arrays.asList("="));
        allowedOperatorsMap.put("id", Arrays.asList("="));

    }

//    public Boolean validate(){
//
//        if(!allowedKeys.contains(searchKey)){
//            return  false;
//        }
//
//        List<String> validOperator = allowedOperatorsMap.get(searchKey);
//        if(!validOperator.contains(operator)){
//            return  false;
//        }
//
//        return  true;
//    }

}