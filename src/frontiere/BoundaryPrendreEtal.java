package frontiere;

import java.util.Scanner;

import controleur.ControlPrendreEtal;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	// Codé entièrement
	public void prendreEtal(String nomVendeur) {
		boolean b = controlPrendreEtal.verifierIdentite(nomVendeur);

		if (!b) {
			StringBuilder message = new StringBuilder();
			message.append("Je suis désolée ");
			message.append(nomVendeur);
			message.append(" mais il faut être un habitant de notre village pour commercer ici.");

			System.out.println(message);
		} else {
			if (controlPrendreEtal.resteEtals()) {
				installerVendeur(nomVendeur);
			} else {
				StringBuilder message = new StringBuilder();
				message.append("Désolée ").append(nomVendeur).append(" je n'ai plus d'étal qui ne soit pas occupé.");

				System.out.println(message);
			}
		}
	}

	// Codé entièrement
	private void installerVendeur(String nomVendeur) {
		Scanner clavier = new Scanner(System.in);

		System.out.println("C'est parfait, il me reste un étal pour vous !");
		System.out.println("Il me faudrait quelques renseignements :");
		System.out.println("Quel produit souhaitez-vous vendre ?");
		String produit = clavier.nextLine();
		System.out.println("Combien souhaitez-vous en vendre ?");
		int nbProduit = clavier.nextInt();

		int numeroEtal = controlPrendreEtal.prendreEtal(nomVendeur, produit, nbProduit);

		if (numeroEtal != -1) {
			StringBuilder message = new StringBuilder();
			message.append("Le vendeur ");
			message.append(nomVendeur);
			message.append(" s'est installé à l'étal n°");
			message.append(numeroEtal);

			System.out.println(message);
		}
	}
}
