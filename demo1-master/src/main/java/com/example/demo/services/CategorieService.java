package com.example.demo.services;

import com.example.demo.entities.Categorie;

import java.util.List;

public interface CategorieService {

    void deleteCategorie(long id);


    List<Categorie> getAllCatergories();

}


