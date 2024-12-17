package tech.chillo.avis_utilisateurs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import tech.chillo.avis_utilisateurs.enums.TypeDeRole;

@Table(name="role")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private TypeDeRole libelle;
    
    // Constructeur par défaut requis par JPA
    public Role() {}
    
    // Constructeur privé pour le Builder
    private Role(Builder builder) {
        this.id = builder.id;
        this.libelle = builder.libelle;
    }

    // Méthode statique pour accéder au Builder
    public static Builder builder() {
        return new Builder();
    }

    // Builder interne
    public static class Builder {
        private int id;
        private TypeDeRole libelle;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder libelle(TypeDeRole libelle) {
            this.libelle = libelle;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeDeRole getLibelle() {
        return libelle;
    }

    public void setLibelle(TypeDeRole libelle) {
        this.libelle = libelle;
    }
}

