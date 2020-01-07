package collection.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="collectionPhoto")
public class PhotosRepo {
	
	private String id;
	private int annee;
	private String coffre;
	private boolean isTelephone;
	private boolean isHubic;
	private boolean isGlacier;
	private boolean isServeur;
	private String nom;
	private String commentaire;
	
	
	
	
	public PhotosRepo() {
		super();
	}


	public PhotosRepo(int annee,  String nom) {
		super();
		this.annee = annee;
		this.nom = nom;
		
	}


	public PhotosRepo(String id, int annee, String coffre, boolean isTelephone, boolean isHubic, boolean isGlacier,
			boolean isServeur, String nom, String commentaire) {
		super();
		this.id=id;
		this.annee = annee;
		this.coffre = coffre;
		this.isTelephone = isTelephone;
		this.isHubic = isHubic;
		this.isGlacier = isGlacier;
		this.isServeur = isServeur;
		this.nom = nom;
		this.commentaire = commentaire;
	}


	@DynamoDBHashKey(attributeName="id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	 @DynamoDBAttribute(attributeName="Annee")  
	public int getAnnee() {
		return annee;
	}


	public void setAnnee(int annee) {
		this.annee = annee;
	}


	@DynamoDBAttribute(attributeName="Coffre")
	public String getCoffre() {
		return coffre;
	}


	public void setCoffre(String coffre) {
		this.coffre = coffre;
	}


	@DynamoDBAttribute(attributeName="isTelephone")
	public boolean isTelephone() {
		return isTelephone;
	}


	public void setTelephone(boolean isTelephone) {
		this.isTelephone = isTelephone;
	}


	@DynamoDBAttribute(attributeName="isHubic")
	public boolean isHubic() {
		return isHubic;
	}


	public void setHubic(boolean isHubic) {
		this.isHubic = isHubic;
	}


	@DynamoDBAttribute(attributeName="isGlacier")
	public boolean isGlacier() {
		return isGlacier;
	}


	public void setGlacier(boolean isGlacier) {
		this.isGlacier = isGlacier;
	}


	@DynamoDBAttribute(attributeName="isServeur")
	public boolean isServeur() {
		return isServeur;
	}


	
	public void setServeur(boolean isServeur) {
		this.isServeur = isServeur;
	}


	@DynamoDBAttribute(attributeName="Nom")
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	@DynamoDBAttribute(attributeName="Commentaire")
	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
	
	
	
	
	
	
	

}