package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REPONDRE")
public class Repondre {
	
	@Id
	@Column(name = "ID_QUESTION")
	private int idQuestion;
	
	@Id
	@Column(name = "ID_REPONSE")
	private int idReponse;
	
	@Column(name = "CONFIANCE")
	private String confiance;
	
	@Column(name = "DATE_REPONSE")
	private Date dateReponse;

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public int getIdReponse() {
		return idReponse;
	}

	public void setIdReponse(int idReponse) {
		this.idReponse = idReponse;
	}

	public String getConfiance() {
		return confiance;
	}

	public void setConfiance(String confiance) {
		this.confiance = confiance;
	}

	public Date getDateReponse() {
		return dateReponse;
	}

	public void setDateReponse(Date dateReponse) {
		this.dateReponse = dateReponse;
	}
}
