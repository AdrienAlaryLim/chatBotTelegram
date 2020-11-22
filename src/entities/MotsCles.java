package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MOTS_CLES")
public class MotsCles {
	private Integer idMotCle;
	private String mot;
	
	@Id
	@Column(name = "ID_MOT_CLE")
	public Integer getIdMotCle() {
		return idMotCle;
	}
	public void setIdMotCle(Integer idMotCle) {
		this.idMotCle = idMotCle;
	}
	
	@Column(name = "MOT", nullable = false)
	public String getMot() {
		return mot;
	}
	public void setMot(String mot) {
		this.mot = mot;
	}
}
