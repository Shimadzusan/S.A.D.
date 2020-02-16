package database_unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;

public class DatabaseTwo implements DatabaseInterface {
	private static SessionFactory sessionFactory;
/*
 *	..взаимодействие с центральными модулями только через интерфсы
 *	..кофигурация базы определяется в файле .properties 
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
		for(int i = 0; i < list.size(); i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm = (HashMap<String, String>) list.get(i);
			if(i == 0)deleteTrash(hm.get("date"));	//..удаление повторов в базе
			if (hm.containsKey("begin_cash"))	//..используем маркер "begin_cash" т.к. list содержит разные HashMap
				addSAD(hm.get("date"), Integer.parseInt(hm.get("begin_cash")), Integer.parseInt(hm.get("end_cash")),
				Integer.parseInt(hm.get("salary")), "");
		}
		System.out.println(".logging ..writting to database TWO sucssesfull complete");
		sessionFactory.close();
	}
	
	private void deleteTrash(String date) {
			Session session = this.sessionFactory.openSession();
			Transaction transaction = null;
			transaction = session.beginTransaction();
			Query query = session.createSQLQuery("DELETE FROM SAD005 WHERE DATE=" + date);
			query.executeUpdate();
			transaction.commit();
			session.close();
	}
	
	public void addSAD(String date, int begin_cash, int end_cash, int salary, String info) {
		Session session = sessionFactory.openSession();
			Transaction transaction = null;
			transaction = session.beginTransaction();
			SAD005 data = new SAD005(date, begin_cash, end_cash, salary, info);
			session.save(data);
			transaction.commit();
		session.close();
	}

}