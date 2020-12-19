package user;

public class UserDatabaseRequests {
	
	private static final String SELECT_QUESTION_BY_ID = "SELECT * FROM questions WHERE id_question = ";
	
	private static final String SELECT_RESPONSE_BY_QUESTION_ID = "SELECT * FROM reponses r "
			+ "INNER JOIN repondre ON  r.id_reponse = repondre.id_reponse "
			+ "INNER JOIN questions q ON q.id_question = repondre.id_question "
			+ "WHERE q.id_question = ";
	
	private static final String SELECT_MOTS_CLE_IN = "SELECT * FROM mots_cles WHERE mot IN ";
	
	private static final String SELECT_QUESTIONS_BY_MOTS_CLES_IN = "SELECT * FROM questions q "
			+ "INNER JOIN contenir ON  q.id_question = contenir.id_question "
			+ "INNER JOIN mots_cles ON mots_cles.id_mot_cle = contenir.id_mot_cle "
			+ "WHERE mots_cles.mot IN ";
	
	private static final String COLUMN_MOT = "MOT";
	
	private static final String COLUMN_ID_QUESTION = "ID_QUESTION";
	
	private static final String COLUMN_RESPONSE = "RESPONSE";
	
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
	 * Build the request to find the response of the given question id
	 * @param idQuestion the question id
	 * @return String selectReponseByQuestionId
	 */
	public static String buildSelectReponseByQuestionId(int idQuestion) 
	{
		return SELECT_RESPONSE_BY_QUESTION_ID + String.valueOf(idQuestion);
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
	 * Build the request "select questions by mots cles IN" with the given array
	 * @param stringFilterArray the filtering array
	 * @return String selectQuestionsByMotsClesIn
	 */
	public static String buildSelectQuestionsByMotsClesIn(String stringFilterArray) {
		return SELECT_QUESTIONS_BY_MOTS_CLES_IN + stringFilterArray;
	}

	/**
	 * Return the string for column "MOT"
	 * @return String
	 */
	public static String getColumnMot() {
		return COLUMN_MOT;
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
	
}
