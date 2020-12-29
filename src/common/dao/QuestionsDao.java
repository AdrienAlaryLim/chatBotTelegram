package common.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import common.entities.Questions;

@Stateless
public class QuestionsDao {
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Questions u WHERE u.idQuestion=:pIdQuestion";
	private static final String PARAM_EMAIL           = "email";
	
	    // Injection du manager, qui s'occupe de la connexion avec la BDD
	    @PersistenceContext( unitName = "bdd_mydbname_PU" )
	    private EntityManager em;
	
	    // Enregistrement d'un nouvel questions
	    public void creer( Questions questions ) throws DAOException {
	        try {
	            em.persist( questions );
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }
	    }
	
	    // Recherche d'un questions à partir de son adresse email
	    public Questions trouver( String email ) throws DAOException {
	        Questions questions = null;
	        Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
	        requete.setParameter( PARAM_EMAIL, email );
	        try {
	            questions = (Questions) requete.getSingleResult();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	    }
        return questions;
    }
}

