package com.chatbot.telegram.config;

public class UserDatabaseRequests {
	
	private static final String COLUMN_QUESTION = "question";
	
	private static final String COLUMN_MOT = "mot";
	
	private static final String COLUMN_ID_MOT_CLE = "id_mot_cle";
	
	private static final String COLUMN_ID_QUESTION = "id_question";
	
	private static final String COLUMN_RESPONSE = "reponse";
	
	private static final String COLUMN_ID_RESPONSE = "id_reponse";
	
	private static final String REQUEST_REPONSE_BY_ID_QUESTION = "getReponseByIdQuestion";

	private static final String REQUEST_QUESTIONS_UNANSWERED = "questionUnanswered";
	
	private static final String REQUEST_QUESTION_BY_ID = "getQuestionById";
	
	private static final String REQUEST_QUESTION_BY_WORDS = "getQuestionByWords";
	
	private static final String REQUEST_INSERT_QUESTION = "insertQuestion";
	
	private static final String REQUEST_MOT_CLE_SOUNDS = "motCleSounds";
	
	private static final String REQUEST_GET_REPONSE_BY_MOT_CLE = "getReponseByMotCle";
	
	private static final String REQUEST_INSERT_REPONSE = "insertReponse";
	
	private static final String URL_COLUMN_ID_QUESTION = "idQuestion=";
	
	private static final String URL_COLUMN_ID_REPONSE = "idReponse=";
	
	private static final String URL_COLUMN_QUESTION = "question=";
	
	private static final String URL_COLUMN_DATE_QUESTION = "dateQuestion=";
	
	private static final String URL_COLUMN_MOTS_CLES = "motsCles=";
	
	private static final String ULR_COLUMN_CONFIANCE = "confiance=";
	
	private static final String URL_COLUMN_CONFLITS = "conflits=";
	
	/**
	 * Return the string for column "QUESTION"
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
	 * Return the string for column "insertQuestion"
	 * @return String
	 */
	public static String getRequestInsertQuestion() {
		return REQUEST_INSERT_QUESTION;
	}
	
	/**
	 * Return the string for column "motCleSounds"
	 * @return String
	 */
	public static String getRequestMotCleSounds() {
		return REQUEST_MOT_CLE_SOUNDS;
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
	
	/**
	 * Return the string for column "conflicts"
	 * @return String
	 */
	public static String getColumnConflicts() {
		return URL_COLUMN_CONFLITS;
	}
	
	/**
	 * Return the string for column "CONFIANCE"
	 * @return String
	 */
	public static String getColumnConfiance() {
		return ULR_COLUMN_CONFIANCE;
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
	 * Return the string for column "dateQuestion="
	 * @return String
	 */
	public static String getUrlColumnDateQuestion() {
		return URL_COLUMN_DATE_QUESTION;
	}
	
	
	/**
	 * Return the string for column "motsCles="
	 * @return String
	 */
	public static String getUrlColumnMotsCles() {
		return URL_COLUMN_MOTS_CLES;
	}
	
	
}
