package common.boTransformer;

import common.bo.MotsClesBo;
import common.entities.MotsCles;

public class MotsClesBoTransformer {
	
	/**
	 * 
	 * @param bo
	 * @return entity Mots cles
	 */
	public MotsCles toEntity(MotsClesBo bo) {
		MotsCles entity = new MotsCles();
		
		entity.setIdMotCle(bo.getIdMotCle());
		entity.setMot(bo.getMot());
		
		return entity;
	}
	
	/**
	 * 
	 * @param entity
	 * @return Mots cles Bo
	 */
	public MotsClesBo toBo(MotsCles entity) {
		MotsClesBo bo = new MotsClesBo();
		
		bo.setIdMotCle(entity.getIdMotCle());
		bo.setMot(entity.getMot());
		
		return bo;
	}
}
