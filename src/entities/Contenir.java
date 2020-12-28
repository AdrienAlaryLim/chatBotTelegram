package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTENIR")
public class Contenir {
	
	@Id
	@Column(name = "ID_QUESTION")
	private int idQuestion;
	
	@Id
	@Column(name = "ID_MOT_CLE")
	private int idMotCle;
	
	@Id
	@Column(name = "ID_REPONSE")
	private int idReponse;

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public int getIdMotCle() {
		return idMotCle;
	}

	public void setIdMotCle(int idMotCle) {
		this.idMotCle = idMotCle;
	}

	public int getIdReponse() {
		return idReponse;
	}

	public void setIdReponse(int idReponse) {
		this.idReponse = idReponse;
	}
}
