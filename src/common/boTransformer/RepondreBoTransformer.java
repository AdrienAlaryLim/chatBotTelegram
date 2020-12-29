package common.boTransformer;

import common.bo.RepondreBo;
import common.entities.Repondre;

public class RepondreBoTransformer {

	/**
	 * 
	 * @param RepondreBo
	 * @return entity Repondre
	 */
	public Repondre toEntity(RepondreBo bo)	{
		Repondre entity = new Repondre();
		
		entity.setIdReponse(bo.getIdReponse());
		entity.setIdQuestion(bo.getIdQuestion());
		entity.setDateReponse(bo.getDateReponse());
		entity.setConfiance(bo.getConfiance());
		
		return entity;
	}
	
	/**
	 * 
	 * @param Repondre
	 * @return RepondreBo
	 */
	public RepondreBo toBo(Repondre entity)	{
		RepondreBo bo = new RepondreBo();
		
		bo.setIdReponse(entity.getIdReponse());
		bo.setIdQuestion(entity.getIdQuestion());
		bo.setDateReponse(entity.getDateReponse());
		bo.setConfiance(entity.getConfiance());
		
		return bo;
	}
}
