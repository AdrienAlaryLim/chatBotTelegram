package user;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import common.entities.Contenir;
import common.entities.MotsCles;
import common.entities.Questions;
import common.entities.Repondre;
import common.entities.Reponses;


public class HibernateUtil {
	private static SessionFactory sessionFactory;
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null){
			try {
				Configuration configuration = new Configuration();
				
				configuration.addAnnotatedClass(Contenir.class);
				configuration.addAnnotatedClass(MotsCles.class);
				configuration.addAnnotatedClass(Questions.class);
				configuration.addAnnotatedClass(Repondre.class);
				configuration.addAnnotatedClass(Reponses.class);
				
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://127.0.0.1:3306/chatbot_db");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				
				configuration.setProperties(settings);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
