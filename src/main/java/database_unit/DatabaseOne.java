package database_unit;

import java.util.HashMap;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DatabaseOne implements DatabaseInterface {
	 private static SessionFactory sessionFactory;
/*
 *	..взаимодействие с центральными модулями только через интерфсы
 *	..кофигурация базы определяется в файле .properties 
 */
	@Value("${Database.config}")
	private String[] databaseArray;
	
	private String[] databaseConfig = {"дата","фнд","копия","печать","другое"};

	public String[] getDatabaseArray() {
		return databaseArray;
	}

	public void setDatabaseArray(String[] databaseArray) {
		this.databaseArray = databaseArray;
	}

	public String[] getParametrs() {
		return databaseConfig;
	}

	public void setStructuredData(List list) {
		System.out.println("..writting to database sucssesfull complete");
		//Launch_two.main_two();
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		//..writing value to database!
        for(int i = 0; i < list.size(); i++) {
        	HashMap<String, String> hm = new HashMap<String, String>();
        	hm = (HashMap<String, String>) list.get(i);
        addSAD(hm.get("date"), hm.get("deals"), Integer.parseInt(hm.get("volume")), hm.get("payment"));
        }
        System.out.println("===================================");
    sessionFactory.close();
	}
	
	 public void addSAD(String date, String deal, int volume, String payment) {
		 Session session = sessionFactory.openSession();
		 	Transaction transaction = null;

		 	transaction = session.beginTransaction();
		 	SAD003 data = new SAD003(date, deal, volume, payment);
		 	session.save(data);
		 	transaction.commit();
		 	
		 session.close();
	 }
	
}
