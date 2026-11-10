package frontiere;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		boolean b = controlAcheterProduit.verifierIdentite(nomAcheteur);
		int choixUtilisateur;
		Gaulois[] gaulois;
		
		if (!b) {
			StringBuilder message = new StringBuilder();
			message.append("Je suis désolée ");
			message.append(nomAcheteur);
			message.append(" mais il faut être un habitant de notre village pour commercer ici.");

			System.out.println(message);
		} else {
			do {
				String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
				gaulois = controlAcheterProduit.trouverVendeurs(produit);
				StringBuilder question = new StringBuilder();
				for(int i = 0; i < gaulois.length; i++) {
					question.append(i + 1).append(' ').append(gaulois[i].getNom()).append("\n");
				}
				choixUtilisateur = Clavier.entrerEntier(question.toString());
				
				if (choixUtilisateur > 0 && choixUtilisateur < gaulois.length + 1) {
					
					do {
						System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + gaulois[choixUtilisateur].getNom());
						System.out.println("Bonjour " + nomAcheteur);
						StringBuilder question2 = new StringBuilder();
						question2.append("Combien de ");
						question2.append(produit);
						question2.append(" voulez-vous acheter ?");
						choixUtilisateur = Clavier.entrerEntier(question.toString());
					} while (choixUtilisateur < 0);
					controlAcheterProduit.acheterProduit(gaulois[choixUtilisateur].getNom(), choixUtilisateur);
				}
			} while (choixUtilisateur < 0 && choixUtilisateur > gaulois.length + 1);
		}
	}
}
