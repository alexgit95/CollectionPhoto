package collection.services;

import java.util.List;

import collection.model.PhotosRepo;

public interface DaoServices {

	/**
	 * Permet de maquer que l'id renseigne est ou non present sur le telephone 
	 * @param id
	 * @param setTel
	 */
	void setTel(String id, boolean setTel);
	/**
	 * Permet d'inserer un nouveau repertoire
	 * @param toCreate
	 */
	void insert(PhotosRepo toCreate);
	
	/**
	 * Permet de maquer que l'id renseigne est ou non present sur le serveur 
	 * @param id
	 * @param setter
	 */
	void setServeur(String id, boolean setter);
	/**
	 * Permet de maquer que l'id renseigne est ou non present sur hubic 
	 * @param id
	 * @param setter
	 */
	void setHubic(String id, boolean setter);

	/**
	 * Permet de maquer que l'id renseigne est ou non present sur le AWS Glacier 
	 * @param id
	 * @param setter
	 */
	void setGlacier(String id, boolean setter);

	/**
	 * Permet de recuperer les infos de tous les repertoires enregistre
	 * @return
	 */
	List<PhotosRepo> getAllRepos();
}
