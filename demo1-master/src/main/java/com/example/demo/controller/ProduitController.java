package com.example.demo.controller;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.services.ProduitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class ProduitController {
    @Autowired
    public ProduitController(ProduitRepository produitRepository, CategorieRepository categorieRepository, ProduitServiceImpl produitService) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.produitService = produitService;
    }

    private final  ProduitRepository produitRepository;
    private  final  CategorieRepository categorieRepository;
    private final ProduitServiceImpl produitService;

    @GetMapping("/produits")
    public List<Produit> getAllProducts() {
        return produitRepository.findAll();
    }

    @PostMapping("/produits/{id}")
    public Produit ajoutProduct(@RequestBody Produit produit, @PathVariable(value = "id") long idcat) {
        if (categorieRepository.findById(idcat).isPresent()) {
            Categorie categorie = categorieRepository.findById(idcat).get();
            produit.setDateCreation(new Timestamp(System.currentTimeMillis()));
            List<Produit> p = categorie.getProduits();
            p.add(produit);
            produit.setCategorie(categorie);
            return produitRepository.save(produit);
        } else return null;
    }

    @GetMapping("/produits/{id}")
    public Produit getProduitbyId(@PathVariable(value = "id") long id) {
        return produitService.getProduit(id);
    }

    @DeleteMapping("/produits/{id}")
    public void deleteProduit(@PathVariable(value = "id") long id) {
        System.err.println(id);
        produitRepository.findById(id).get().setCategorie(null);
        produitService.deleteProduitById(id);

    }


    @PutMapping("/produits/{id}")
    public Produit updateProduit(@RequestBody Produit p1, @PathVariable long id) {
        if (produitRepository.findById(id).isPresent()) {
            Produit p = produitRepository.findById(id).get();
            if (p1.getNomProduit() != null)
                p.setNomProduit(p1.getNomProduit());

            if (p1.getQuantiteProduit() != 0)
                p.setQuantiteProduit(p1.getQuantiteProduit());
            if (p1.getDisponible() != null)
                p.setDisponible(p1.getDisponible());
            p.setDateModification(new Timestamp(System.currentTimeMillis()));
            return produitRepository.save(p);
        }
        else return  null;
    }
}
