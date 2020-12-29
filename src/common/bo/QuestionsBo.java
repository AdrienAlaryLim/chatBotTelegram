package common.bo;

import java.util.Date;

public class QuestionsBo {
	private int idQuestion;
	
	private String question;
	
	private Date dateQuestion;

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Date getDateQuestion() {
		return dateQuestion;
	}

	public void setDateQuestion(Date dateQuestion) {
		this.dateQuestion = dateQuestion;
	}
}
