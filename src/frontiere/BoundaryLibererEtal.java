package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		if (!controlLibererEtal.isVendeur(nomVendeur)) {
			System.out.println("Mais vous n'êtes pas inscrit sur notre marché aujourd'hui !");
		} else {
			String[] donneesEtal = controlLibererEtal.libererEtal(nomVendeur);

			boolean etalOccupe = Boolean.parseBoolean(donneesEtal[0]);

			if (etalOccupe) {
				String produit = donneesEtal[2];
				int quantiteInitiale = Integer.parseInt(donneesEtal[3]);
				int quantiteVendue = Integer.parseInt(donneesEtal[4]);

				System.out.println(
						"Vous avez vendu " + quantiteVendue + " sur " + quantiteInitiale + " " + produit + ".");
			}

			System.out.println("En revoir " + nomVendeur + ", passez une bonne journée");
		}
	}

}
