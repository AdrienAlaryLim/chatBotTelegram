package common.boTransformer;

import common.bo.QuestionsBo;
import common.entities.Questions;

public class QuestionsBoTransformer {
	
	/**
	 * 
	 * @param QuestionsBo
	 * @return entity Questions
	 */
	public Questions toEntity(QuestionsBo bo)	{
		Questions entity = new Questions();
		
		entity.setIdQuestion(bo.getIdQuestion());
		entity.setQuestion(bo.getQuestion());
		entity.setDateQuestion(bo.getDateQuestion());
		
		return entity;
	}
	
	/**
	 * 
	 * @param Questions
	 * @return QuestionsBo
	 */
	public QuestionsBo toBo(Questions entity)	{
		QuestionsBo bo = new QuestionsBo();
		
		bo.setIdQuestion(entity.getIdQuestion());
		bo.setQuestion(entity.getQuestion());
		bo.setDateQuestion(entity.getDateQuestion());
		
		return bo;
	}
}
