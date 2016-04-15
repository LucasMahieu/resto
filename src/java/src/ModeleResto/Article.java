package ModeleResto;
public class Article {
  public Article(){
  }
	
	public static /*type de retour*/ afficher(String nomArticle, int prixMax, int prixMin, String specialite, String type) {
		String requete = new String("SELECT * from article");
		if (nomArticle != null= {
			requete += "where article.nom = " + nomArticle;
		} else {
			requete += "where article.nom = *";
		}
		if (prixMax != -1) {
			requete += "and article.prix <= " + prixMax;
		}
		if (prixMin != -1) {
			requete += "and article.prix <= " + prixMin;
		}
		if (specialite != null) {
			requete += "and article.specialite = " + specialite;
		}
		if (type != null) {
			requete += "having article.nomarticle in (SELECT * from " + type + " );");
		} else {
			requete += "having article.nomarticle in (SELECT * from article );"); //pas sur que ca marche
		}
	}
}
