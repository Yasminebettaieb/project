package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Table(name = "categorie")
@Entity
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomCategorie;
    private int quantiteCategorie;
    private Timestamp date_Creation;
    private Timestamp date_Modification;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Categorie", referencedColumnName = "id") // we need to duplicate the physical information
    private List<Produit> produits;

    public Categorie(String nomCategorie, int quantiteCategorie) {
        this.nomCategorie = nomCategorie;
        this.quantiteCategorie = quantiteCategorie;
    }

    public Categorie() {

    }


}