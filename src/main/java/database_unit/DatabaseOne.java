package database_unit;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.hibernate.Query;
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
	private String[] databaseArray;	//..заменить class Parameters на config.properties

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
		/*демонстрирует получение параметров из кофигурационного файла*/
//		for (int i = 0; i < databaseArray.length; i++) {
//			System.out.println(i + ": " + databaseArray[i]);
//		}
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
//..writing value to database!
        for(int i = 0; i < list.size(); i++) {
        	HashMap<String, String> hm = new HashMap<String, String>();
        	hm = (HashMap<String, String>) list.get(i);
        	if(i == 0)deleteTrash(hm.get("date"));	//..удаление повторов в базе
        	if(hm.containsKey("deal"))	//..используем маркер "deal" т.к. list содержит разные HashMap
        		addSAD(hm.get("date"), hm.get("deal").trim(), Integer.parseInt(hm.get("volume")), hm.get("payment"), getComment(hm.get("comment")));
        }
        System.out.println(".logging ..writting to database sucssesfull complete");
        sessionFactory.close();
	}
	
	private void deleteTrash(String date) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		Query query = session.createSQLQuery("DELETE FROM SAD003 WHERE DATE=" + date);
		query.executeUpdate();
		transaction.commit();
		session.close();
	}

	public void addSAD(String date, String deal, int volume, String payment, String comment) {
		Session session = sessionFactory.openSession();
			Transaction transaction = null;
			transaction = session.beginTransaction();
			SAD003 data = new SAD003(date, deal, volume, payment, comment);
			session.save(data);
			transaction.commit();
		session.close();
	}
	
	private String getComment(String s) {
		if(s.length()>1)return s.substring(1);
		else return "";
	}

}
