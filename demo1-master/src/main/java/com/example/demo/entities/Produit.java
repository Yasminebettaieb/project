package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Table(name = "produit")
@Entity
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomProduit;
    private int quantiteProduit;
    private Boolean disponible;
    private Timestamp dateCreation;
    private Timestamp dateModification;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Categorie")
    @JsonIgnore
    private Categorie categorie;

    public Produit(String nomProduit, int quantiteProduit) {
        this.nomProduit = nomProduit;
        this.quantiteProduit = quantiteProduit;
    }

    public Produit() {
    }
}