package com.example.demo.services;

import com.example.demo.entities.Produit;
import com.example.demo.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }


    @Override
    public Produit updateProduit(Produit p, long id) {
        if (produitRepository.findById(id).isPresent()) {
            Produit p1 = produitRepository.findById(id).get();
            if (p.getNomProduit() != null)
                p1.setNomProduit(p.getNomProduit());
            if (p.getQuantiteProduit() != 0)
                p1.setQuantiteProduit(p.getQuantiteProduit());
            if (p.getDisponible() != null) {
                p1.setDisponible(p.getDisponible());
            }
            p1.setDateModification(new Timestamp(System.currentTimeMillis()));
            return produitRepository.save(p1);
        } else return null;
    }


    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);
    }


    public void deleteProduitById(long id) {

        produitRepository.deleteById(id);
    }


    public Produit getProduit(long id) {
        if (produitRepository.findById(id).isPresent()) {
            return produitRepository.findById(id).get();
        } else
            return null;
    }


}
