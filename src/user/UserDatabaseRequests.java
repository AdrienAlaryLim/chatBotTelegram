package user;

public class UserDatabaseRequests {
	
	private static final String selectQuestionById = "SELECT * FROM questions WHERE id_question = ";
	
	private static final String selectReponseByQuestionId = "SELECT * FROM reponses r "
			+ "INNER JOIN repondre ON  r.id_reponse = repondre.id_reponse "
			+ "INNER JOIN questions q ON q.id_question = repondre.id_question "
			+ "WHERE q.id_question = ";
	
	private static final String selectMotsClesIn = "SELECT * FROM mots_cles WHERE mot IN ";
	
	private static final String selectQuestionsByMotsClesIn = "SELECT * FROM questions q "
			+ "INNER JOIN contenir ON  q.id_question = contenir.id_question "
			+ "INNER JOIN mots_cles ON mots_cles.id_mot_cle = contenir.id_mot_cle "
			+ "WHERE mots_cles.mot IN ";
	/**
	 * Build the request to find question by the given id
	 * @param idQuestion the question id
	 * @return String selectQuestionById
	 */
	public static String buildSelectQuestionById(int idQuestion) 
	{
		return selectQuestionById + String.valueOf(idQuestion);
	}
	
	/**
	 * Build the request to find the response of the given question id
	 * @param idQuestion the question id
	 * @return String selectReponseByQuestionId
	 */
	public static String buildSelectReponseByQuestionId(int idQuestion) 
	{
		return selectReponseByQuestionId + String.valueOf(idQuestion);
	}

	/**
	 * Build the request "select in mots cles IN" with the given array
	 * @param stringFilterArray the filtering array
	 * @return String selectMotsClesIn
	 */
	public static String buildSelectMotsClesIn(String stringFilterArray) {
		return selectMotsClesIn + stringFilterArray;
	}
	
	/**
	 * Build the request "select questions by mots cles IN" with the given array
	 * @param stringFilterArray the filtering array
	 * @return String selectQuestionsByMotsClesIn
	 */
	public static String buildSelectQuestionsByMotsClesIn(String stringFilterArray) {
		return selectQuestionsByMotsClesIn + stringFilterArray;
	}
}
