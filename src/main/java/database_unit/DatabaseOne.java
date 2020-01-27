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
 *	..однако временно конфигурирование будет проводиться в class Parameters
 */
	
	@Value("${Database.config}")
	private String[] databaseArray;

	public String[] getDatabaseArray() {
		return databaseArray;
	}

	public void setDatabaseArray(String[] databaseArray) {
		this.databaseArray = databaseArray;
	}

	public Parameters getParametrs() {
		return new Parameters();
	}

	public void setStructuredData(List list) {
		sessionFactory = new Configuration().configure().buildSessionFactory();
//..writing value to database!
        for(int i = 0; i < list.size(); i++) {
        	HashMap<String, String> hm = new HashMap<String, String>();
        	hm = (HashMap<String, String>) list.get(i);
        	
        	if (hm.containsKey("deal"))	//..используем маркер "deal" т.к. list содержит разные HashMap
        		addSAD(hm.get("date"), hm.get("deal"), Integer.parseInt(hm.get("volume")), hm.get("payment"));
        }
        System.out.println(".logging ..writting to database sucssesfull complete");
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
