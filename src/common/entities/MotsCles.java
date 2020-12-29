package common.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MOTS_CLES")
public class MotsCles {
	
	@Id
	@Column(name = "ID_MOT_CLE")
	private int idMotCle;
	
	@Column(name = "MOT", nullable = false)
	private String mot;
	
	public Integer getIdMotCle() {
		return idMotCle;
	}
	
	public String getMot() {
		return mot;
	}
	
	public void setIdMotCle(Integer idMotCle) {
		this.idMotCle = idMotCle;
	}
	
	public void setMot(String mot) {
		this.mot = mot;
	}
}
