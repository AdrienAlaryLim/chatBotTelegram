package com.chatbot.telegram.config;

public class UserDatabaseRequests {
	
	//private static final String SELECT_QUESTION_BY_ID = "SELECT * FROM questions WHERE id_question = ";
	
	//private static final String SELECT_QUESTION_BY_WHOLE_WORDS = "SELECT * FROM questions WHERE question = '";
	
	//private static final String INSERT_QUESTION = "INSERT INTO questions (question, date_question) VALUES ";
	
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
	
	private static final String COLUMN_RESPONSE = "reponse";
	
	private static final String COLUMN_ID_RESPONSE = "id_reponse";
	
	private static final String URL_COLUMN_ID_QUESTION = "idQuestion=";
	
	private static final String URL_COLUMN_ID_REPONSE = "idReponse=";
	
	private static final String URL_COLUMN_QUESTION = "question=";
	
	private static final String URL_COLUMN_DATE_QUESTION = "dateQuestion=";
	
	private static final String URL_COLUMN_MOTS_CLES = "motsCles=";
	
	private static final String REQUEST_QUESTION_BY_ID = "getQuestionById";
	
	private static final String REQUEST_QUESTION_BY_WORDS = "getQuestionByWords";
	
	private static final String INSERT_QUESTION = "insertQuestion";
	
	private static final String REQUEST_MOT_CLE_SOUNDS = "motCleSounds";
	
	private static final String REQUEST_GET_REPONSE_BY_MOT_CLE = "getReponseByMotCle";
	
	private static final String REQUEST_INSERT_REPONSE = "insertReponse";
	
	private static final String ULR_COLUMN_CONFIANCE = "confiance=";
	
	private static final String ULR_COLUMN_CONFLITS = "conflits=";
	
	private static final String REQUEST_REPONSE_BY_ID_QUESTION = "getReponseByIdQuestion";

	private static final String REQUEST_QUESTIONS_UNANSWERED = "questionUnanswered";
	
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
	 * Return the string for column "REPONSE"
	 * @return String
	 */
	public static String getColumnResponse() {
		return COLUMN_RESPONSE;
	}
	
	/**
	 * Return the string for column "ID_RESPONSE"
	 * @return String
	 */
	public static String getColumnIdReponse() {
		return COLUMN_ID_RESPONSE;
	}
	
	/**
	 * Return the string for column "conflicts"
	 * @return String
	 */
	public static String getColumnConflicts() {
		return ULR_COLUMN_CONFLITS;
	}
	
	/**
	 * Return the string for column "CONFIANCE"
	 * @return String
	 */
	public static String getColumnConfiance() {
		return ULR_COLUMN_CONFIANCE;
	}
	
	/**
	 * Return the string for column "getQuestionById"
	 * @return String
	 */
	public static String getRequestQuestionById() {
		return REQUEST_QUESTION_BY_ID;
	}
	
	/**
	 * Return the string for column "getRequestQuestionByWords"
	 * @return String
	 */
	public static String getRequestQuestionByWords() {
		return REQUEST_QUESTION_BY_WORDS;
	}
	
	/**
	 * Return the string for column "idQuestion="
	 * @return String
	 */
	public static String getUrlColumnIdQuestion() {
		return URL_COLUMN_ID_QUESTION;
	}
	
	/**
	 * Return the string for column "idReponse="
	 * @return String
	 */
	public static String getUrlColumnIdReponse() {
		return URL_COLUMN_ID_REPONSE;
	}
	
	/**
	 * Return the string for column "question="
	 * @return String
	 */
	public static String getUrlColumnQuestion() {
		return URL_COLUMN_QUESTION;
	}
	
	/**
	 * Return the string for column "insertQuestion"
	 * @return String
	 */
	public static String getRequestInsertQuestion() {
		return INSERT_QUESTION;
	}
	
	/**
	 * Return the string for column "dateQuestion="
	 * @return String
	 */
	public static String getUrlColumnDateQuestion() {
		return URL_COLUMN_DATE_QUESTION;
	}
	
	/**
	 * Return the string for column "motCleSounds"
	 * @return String
	 */
	public static String getRequestMotCleSounds() {
		return REQUEST_MOT_CLE_SOUNDS;
	}
	
	/**
	 * Return the string for column "motsCles="
	 * @return String
	 */
	public static String getUrlColumnMotsCles() {
		return URL_COLUMN_MOTS_CLES;
	}
	
	/**
	 * Return the string for column "getReponseByMotCle"
	 * @return String
	 */
	public static String getRequestReponseByMotCle() {
		return REQUEST_GET_REPONSE_BY_MOT_CLE;
	}
	
	/**
	 * Return the string for column "insertReponse"
	 * @return String
	 */
	public static String getRequestInsertReponse() {
		return REQUEST_INSERT_REPONSE;
	}
	
	/**
	 * Return the string for column "getReponseByIdQuestion"
	 * @return String
	 */
	public static String getRequestReponseByIdQuestion() {
		return REQUEST_REPONSE_BY_ID_QUESTION;
	}
	
	/**
	 * Return the string for column "questionUnanswered"
	 * @return String
	 */
	public static String getRequestQuestionsUnanswered() {
		return REQUEST_QUESTIONS_UNANSWERED;
	}
	
}
