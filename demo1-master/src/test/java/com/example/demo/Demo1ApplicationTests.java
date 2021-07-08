package com.example.demo;

import com.example.demo.controller.CategorieController;
import com.example.demo.controller.ProduitController;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class Demo1ApplicationTests {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ProduitController produitController;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private CategorieController categorieController;


    @Test
    void ajoutCategorie() {
        Categorie categorie = new Categorie("uniiiit", 4);
        categorieController.ajoutCategorie(categorie);
        assertTrue(categorieRepository.findById(categorie.getId()).isPresent());
    }

    @Test
    void ajoutProduit() {
        Produit produit = new Produit("uniiiit", 4);
        produitController.ajoutProduct(produit, 20);
        assertTrue(produitRepository.findById(produit.getId()).isPresent());
    }

    @Test
    void updateCat() {
        Categorie c = new Categorie("updatetest", 5);
        categorieController.updateCategorie(c, 20);
        assertTrue(categorieRepository.findById(20L).get().getNomCategorie().equals(c.getNomCategorie()));
    }

    @Test
    void updateProd() {
        Produit p = new Produit("updatetestprod", 5);
        produitController.updateProduit(p, 8);
        assertTrue(produitRepository.findById(8L).get().getNomProduit().equals(p.getNomProduit()));
    }


}
