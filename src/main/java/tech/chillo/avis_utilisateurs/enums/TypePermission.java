package tech.chillo.avis_utilisateurs.enums;

	public enum TypePermission {
	
		  ADMINISTRATEUR_CREATE("Administrateur - Create"),
		  ADMINISTRATEUR_READ("Administrateur - Read"),
		  ADMINISTRATEUR_UPDATE("Administrateur - Update"),
		  ADMINISTRATEUR_DELETE("Administrateur - Delete"),
		    
		  MANAGER_CREATE("Manager - Create"),
		  MANAGER_READ("Manager - Read"),
		  MANAGER_UPDATE("Manager - Update"),
		  MANAGER_DELETE_AVIS("Manager - Delete Avis"),
		    
		  UTILISATEUR_CREATE_AVIS("Utilisateur - Create Avis");
		
		private String libelle;
		
	    // Constructor
	    TypePermission(String libelle) {
	        this.libelle = libelle;
	    }

	    // Getter
	    public String getLibelle() {
	        return libelle;
	    }
	}
