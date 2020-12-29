package common.boTransformer;

import common.bo.ContenirBo;
import common.entities.Contenir;

public class ContenirBoTransformer {
	
	/**
	 * 
	 * @param contenirBo
	 * @return entity contenir
	 */
	public Contenir toEntity(ContenirBo bo)	{
		Contenir entity = new Contenir();
		
		entity.setIdMotCle(bo.getIdMotCle());
		entity.setIdQuestion(bo.getIdQuestion());
		entity.setIdReponse(bo.getIdReponse());
		
		return entity;
	}
	
	/**
	 * 
	 * @param contenir
	 * @return contenirBo
	 */
	public ContenirBo toBo(Contenir entity)	{
		ContenirBo bo = new ContenirBo();
		
		bo.setIdMotCle(entity.getIdMotCle());
		bo.setIdQuestion(entity.getIdQuestion());
		bo.setIdReponse(entity.getIdReponse());
		
		return bo;
	}
}
