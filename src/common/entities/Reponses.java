package common.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTENIR")
public class Reponses {
	
	@Id
	@Column(name = "ID_REPONSE")
	private int idReponse;

	@Column(name = "RESPONSE")
	private String reponse;

	public int getIdReponse() {
		return idReponse;
	}

	public void setIdReponse(int idReponse) {
		this.idReponse = idReponse;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
}