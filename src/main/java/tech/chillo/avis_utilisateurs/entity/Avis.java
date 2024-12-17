package tech.chillo.avis_utilisateurs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    private String statut;

    // Constructeur privé pour le Builder
    private Avis(Builder builder) {
        this.id = builder.id;
        this.message = builder.message;
        this.statut = builder.statut;
    }

    // Méthode statique pour accéder au Builder
    public static Builder builder() {
        return new Builder();
    }

    // Builder interne
    public static class Builder {
        private int id;
        private String message;
        private String statut;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder statut(String statut) {
            this.statut = statut;
            return this;
        }

        public Avis build() {
            return new Avis(this);
        }
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}

