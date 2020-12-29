package common.boTransformer;

import common.bo.ReponsesBo;
import common.entities.Reponses;

public class ReponsesBoTransformer {

	/**
	 * 
	 * @param ReponsesBo
	 * @return entity Reponses
	 */
	public Reponses toEntity(ReponsesBo bo)	{
		Reponses entity = new Reponses();
		
		entity.setIdReponse(bo.getIdReponse());
		entity.setReponse(bo.getReponse());
		
		return entity;
	}
	
	/**
	 * 
	 * @param Reponses
	 * @return ReponseBo
	 */
	public ReponsesBo toBo(Reponses entity)	{
		ReponsesBo bo = new ReponsesBo();
		
		bo.setIdReponse(entity.getIdReponse());
		bo.setReponse(entity.getReponse());
		
		return bo;
	}
}
