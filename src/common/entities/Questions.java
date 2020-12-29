package common.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTIONS")
public class Questions {
	
	@Id
	@Column(name = "ID_QUESTION")
	private int idQuestion;
	
	@Column(name = "QUESTION")
	private String question;
	
	@Column(name = "DATE_QUESTION")
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
