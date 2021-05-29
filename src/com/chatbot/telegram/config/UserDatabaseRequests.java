package com.chatbot.telegram.config;

public class UserDatabaseRequests {
	
	private static final String SELECT_QUESTION_BY_ID = "SELECT * FROM questions WHERE id_question = ";
	
	private static final String SELECT_QUESTION_BY_WHOLE_WORDS = "SELECT * FROM questions WHERE question = '";
	
	private static final String INSERT_QUESTION = "INSERT INTO questions (question, date_question) VALUES ";
	
	private static final String INSERT_ANSWERING = "INSERT INTO repondre (id_question, id_reponse, date_reponse, confiance, mots_cles_associes) VALUES ";
	
	private static final String SELECT_RESPONSE_BY_QUESTION_ID = "SELECT * FROM reponses r "
			+ "INNER JOIN repondre ON  r.id_reponse = repondre.id_reponse "
			+ "INNER JOIN questions q ON q.id_question = repondre.id_question "
			+ "WHERE q.id_question = ";
	
	private static final String SELECT_RESPONSE_BY_RESPONSE_ID = "SELECT response FROM reponses WHERE reponses.id_reponse = ";
	
	private static final String SELECT_MOTS_CLE_IN = "SELECT * FROM mots_cles WHERE mot ";
	
	private static final String SELECT_MOTS_CLE_BY_MOT = "SELECT * FROM mots_cles WHERE ";
	
	private static final String SELECT_QUESTIONS_BY_MOTS_CLES_IN = "SELECT * FROM questions q "
			+ "INNER JOIN contenir ON  q.id_question = contenir.id_question "
			+ "INNER JOIN mots_cles ON mots_cles.id_mot_cle = contenir.id_mot_cle "
			+ "WHERE mots_cles.mot = ";
	
	private static final String SELECT_REPONSE_BY_MOTS_CLES_IN = "SELECT * FROM reponses r "
			+ "INNER JOIN repondre ON  repondre.id_reponse = r.id_reponse "
			+ "INNER JOIN questions ON  questions.id_question = repondre.id_question "
			+ "INNER JOIN contenir ON  contenir.id_question = questions.id_question "
			+ "INNER JOIN mots_cles ON mots_cles.id_mot_cle = contenir.id_mot_cle "
			+ "WHERE mots_cles.mot = ";
	
	private static final String SELECT_QUESTIONS_UNANSWERED = "SELECT question FROM questions q "
			+ "WHERE q.id_question NOT IN (SELECT repondre.id_question FROM repondre)";
	
	private static final String COLUMN_QUESTION = "question";
	
	private static final String COLUMN_MOT = "mot";
	
	private static final String COLUMN_ID_MOT_CLE = "id_mot_cle";
	
	private static final String COLUMN_ID_QUESTION = "id_question";
	
	private static final String COLUMN_RESPONSE = "response";
	
	private static final String COLUMN_ID_RESPONSE = "id_reponse";
	
	/**
	 * Build the request to find question by the given id
	 * @param idQuestion the question id
	 * @return String selectQuestionById
	 */
	public static String buildSelectQuestionById(int idQuestion) 
	{
		return SELECT_QUESTION_BY_ID + String.valueOf(idQuestion);
	}
	
	/**
	 * Build the request to find question by the given id
	 * @param question the question's words
	 * @return String selectQuestionByWords
	 */
	public static String buildSelectQuestionByWholeWords(String question) 
	{
		return SELECT_QUESTION_BY_WHOLE_WORDS + question + "'";
	}
	
	/**
	 * Build the insert question values request
	 * @param idQuestion the question id
	 * @return String selectQuestionById
	 */
	public static String buildInsertQuestionValues(String values) 
	{
		return INSERT_QUESTION + values ;
	}
	
	/**
	 * Build the insert answering values request
	 * @param idQuestion the question id
	 * @return String selectQuestionById
	 */
	public static String buildInsertAnsweringValues(String values) 
	{
		return INSERT_ANSWERING + values ;
	}
	
	/**
	 * Build the request to find the response of the given question id
	 * @param idQuestion the question id
	 * @return String selectReponseByQuestionId
	 */
	public static String buildSelectReponseByQuestionId(String idQuestion) 
	{
		return SELECT_RESPONSE_BY_QUESTION_ID + idQuestion;
	}

	/**
	 * Build the request to find the response of the given question id
	 * @param idQuestion the question id
	 * @return String selectReponseByQuestionId
	 */
	public static String buildSelectReponseByResponseId(String idResponse) 
	{
		return SELECT_RESPONSE_BY_RESPONSE_ID + idResponse;
	}

	/**
	 * Build the request "select in mots cles IN" with the given array
	 * @param stringFilterArray the filtering array
	 * @return String selectMotsClesIn
	 */
	public static String buildSelectMotsClesIn(String stringFilterArray) {
		return SELECT_MOTS_CLE_IN + stringFilterArray;
	}
	
	/**
	 * Build the request "select id_mot_cle by mot" with the given array
	 * @param stringFilterArray the filtering array
	 * @return String selectMotsClesIn
	 */
	public static String buildSelectIdMotsClesByMotCle(String stringFilterArray) {
		return SELECT_MOTS_CLE_BY_MOT + stringFilterArray;
	}
	
	/**
	 * Build the request "select questions by mots cles IN" with the given array
	 * @param stringFilterArray the filtering array
	 * @return String selectQuestionsByMotsClesIn
	 */
	public static String buildSelectQuestionsByMotsClesIn(String stringFilterArray) {
		return SELECT_QUESTIONS_BY_MOTS_CLES_IN + stringFilterArray;
	}
	
	/**
	 * Build the request "select question by mots cles" with the given array
	 * @param stringFilterArray the filtering array
	 * @return String selectQuestionsByMotsClesIn
	 */
	public static String buildSelectQuestionByMotsCles(String stringFilterArray) {
		
		stringFilterArray = "'" + stringFilterArray + "'";
			
		return SELECT_QUESTIONS_BY_MOTS_CLES_IN + stringFilterArray;
	}
	
	/**
	 * Build the request "select response by mots cles" with the given array
	 * @param stringFilterArray the filtering array
	 * @return String selectQuestionsByMotsClesIn
	 */
	public static String buildSelectReponseByMotsCles(String stringFilterArray) {
		
		stringFilterArray = "'" + stringFilterArray + "'";
			
		return SELECT_REPONSE_BY_MOTS_CLES_IN + stringFilterArray;
		
	}
	
	/**
	 * Build the request "select questions without responses"
	 * @return listOfQuestions
	 */
	public static String buildSelectQuestionsUnanswered() {
		
		return SELECT_QUESTIONS_UNANSWERED ;
		
	}
	
	/**
	 * Return the string for column "COLUMN_QUESTION"
	 * @return
	 */
	public static String getColumnQuestion() {
		return COLUMN_QUESTION;
	}

	/**
	 * Return the string for column "MOT"
	 * @return String
	 */
	public static String getColumnMot() {
		return COLUMN_MOT;
	}
	
	/**
	 * Return the string for column "ID_MOT_CLE"
	 * @return String
	 */
	public static String getColumnIdMotCle() {
		return COLUMN_ID_MOT_CLE;
	}

	/**
	 * Return the string for column "ID_QUESTION"
	 * @return String
	 */
	public static String getColumnIdQuestion() {
		return COLUMN_ID_QUESTION;
	}

	/**
	 * Return the string for column "RESPONSE"
	 * @return String
	 */
	public static String getColumnResponse() {
		return COLUMN_RESPONSE;
	}
	
	/**
	 * Return the string for column "ID_RESPONSE"
	 * @return String
	 */
	public static String getColumnIdResponse() {
		return COLUMN_ID_RESPONSE;
	}
}