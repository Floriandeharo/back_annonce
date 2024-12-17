package tech.chillo.avis_utilisateurs.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "jwt")
public class Jwt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String valeur;
    private boolean desactive;
    private boolean expire;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Constructeur par défaut requis par JPA
    public Jwt() {}

    // Constructeur privé (utilisé uniquement par le Builder)
    private Jwt(Builder builder) {
        this.id = builder.id;
        this.valeur = builder.valeur;
        this.desactive = builder.desactive;
        this.expire = builder.expire;
        this.utilisateur = builder.utilisateur;
    }

    // Méthode builder statique pour faciliter l'accès au Builder
    public static Builder builder() {
        return new Builder();
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public boolean isDesactive() {
        return desactive;
    }

    public void setDesactive(boolean desactive) {
        this.desactive = desactive;
    }

    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    // Classe statique Builder
    public static class Builder {
        private int id;
        private String valeur;
        private boolean desactive;
        private boolean expire;
        private Utilisateur utilisateur;

        public Builder() {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder valeur(String valeur) {
            this.valeur = valeur;
            return this;
        }

        public Builder desactive(boolean desactive) {
            this.desactive = desactive;
            return this;
        }

        public Builder expire(boolean expire) {
            this.expire = expire;
            return this;
        }

        public Builder utilisateur(Utilisateur utilisateur) {
            this.utilisateur = utilisateur;
            return this;
        }

        public Jwt build() {
            return new Jwt(this);
        }
    }
}

