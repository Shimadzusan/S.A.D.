package database_unit;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Launch_two {
	 private static SessionFactory sessionFactory;

	public static void main_two(List list) {
		sessionFactory = new Configuration().configure().buildSessionFactory();

			Launch_two thisObject = new Launch_two();
	        List<SAD001> listData = thisObject.listData();
	        for (SAD001 data : listData) {
	            System.out.println(data);
	        }
	       
//..writing value to database!
	        for(int i = 0; i < list.size(); i++) {
	        	HashMap<String, String> hm = new HashMap<String, String>();
	        	hm = (HashMap<String, String>) list.get(i);
	        thisObject.addSAD(hm.get("date"), hm.get("deals"), Integer.parseInt(hm.get("volume")), hm.get("payment"));
	        }
	        System.out.println("===================================");
        sessionFactory.close();
	}
	 public List<SAD001> listData() {
		 Session session = this.sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	        List<SAD003> listData = session.createQuery("FROM SAD003").list();	//..SELECT * FROM user_table;
	        List<SAD001> listData2 = session.createQuery("FROM SAD001").list();
	        transaction.commit();
	       
	        
	        //session.createSQLQuery("queryString");
	        
	    session.close();
	        return listData2;
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
