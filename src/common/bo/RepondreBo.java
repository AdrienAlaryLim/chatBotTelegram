package common.bo;

import java.util.Date;

public class RepondreBo {
	private int idQuestion;
	
	private int idReponse;
	
	private String confiance;
	
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
