package com.example.demo.controller;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.services.CategorieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class CategorieController {
    private final CategorieRepository categorieRepository;
    private final CategorieServiceImpl categorieService;
    private final ProduitController produitController;

    @Autowired
    public CategorieController(CategorieRepository categorieRepository, CategorieServiceImpl categorieService, ProduitController produitController) {
        this.categorieRepository = categorieRepository;
        this.categorieService = categorieService;
        this.produitController = produitController;
    }


    @GetMapping("/categories")
    public List<Categorie> getAllategories() {
        return categorieRepository.findAll();
    }

    @PostMapping("/categorieajout")
    public Categorie Ajoutategorie(@Validated @RequestBody Categorie cat) {
        cat.setDate_Creation(new Timestamp(System.currentTimeMillis()));

        if (cat.getProduits() != null) {
            List<Produit> produits = cat.getProduits();
            for (Produit item : produits) {
                item.setDateCreation(new Timestamp(System.currentTimeMillis()));
                produitController.ajoutProduct(item, cat.getId());
            }}
        return categorieRepository.save(cat);
    }


    @GetMapping("/categories/{id}")
    public Categorie GetCategoryById(@PathVariable(value = "id") long catId) {
        return categorieService.getCat(catId);
    }

    @DeleteMapping("/categories/{id}")
    public void DeleteCategorie(@PathVariable(value = "id") long cat) {
        if (categorieRepository.findById(cat).isPresent()) {
            var c = categorieRepository.findById(cat).get();
            categorieRepository.delete(c);
        }

    }

    @PutMapping("/categories/{id}")
    public Categorie updateCategorie(@RequestBody Categorie cat1, @PathVariable long id) {
        if (categorieRepository.findById(id).isPresent()) {
            Categorie c = categorieRepository.findById(id).get();
            if (cat1.getNomCategorie() != null)
                c.setNomCategorie(cat1.getNomCategorie());
            if (cat1.getQuantiteCategorie() != 0)
                c.setQuantiteCategorie(cat1.getQuantiteCategorie());
            c.setDate_Modification(new Timestamp(System.currentTimeMillis()));
            return categorieRepository.save(c);

        } else return null;
    }
}