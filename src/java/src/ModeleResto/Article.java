package ModeleResto;
public class Article {
  public Article(){
  }
	
	public static ResultSet getArticle(String nomArticle, float prixArticle, String specialite, String typeArticle) {
		String requete = new String("SELECT * from article where");
		if (nomArticle != null= {
			requete += "article.nom = " + nomArticle;
		}
		if (prixArticle != -1) {
				if (nomArticle != null) {	
					requete += " and ";
				}
			requete += " article.prix == " + prixArticle;
		}
		if (specialite != null) {
				if (nomArticle != null or prixArticle != -1) {	
					requete += " and ";
				}
			requete += "and article.specialite = " + specialite;
		}
		if (type != null) {
			requete += "having article.nomarticle in (SELECT * from " + type + " )");
		}	
		requete += ";");
		}

	public static ResultSet /* pas sur*/ ajoutArticle(String nomArticle, int quantite, int numerReservation) {
			String requete += String("Insert into sontcommandes Values");
			requete += "(" += nomArticle;
			requete += ", " + quantite;
			requete += ", " + numeroReservation;
	}
}
