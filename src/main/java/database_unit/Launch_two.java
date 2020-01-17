package database_unit;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Launch_two {
	 private static SessionFactory sessionFactory;

	public static void main_two(String date, String deal, int volume, String payment) {
		sessionFactory = new Configuration().configure().buildSessionFactory();

			Launch_two thisObject = new Launch_two();
//	        List<SAD003> listData = thisObject.listData();
//	        for (SAD003 data : listData) {
//	            System.out.println(data);
//	        }
	        
//..writing value to database!	        
	        thisObject.addSAD(date, deal, volume, payment);

	        System.out.println("===================================");
        sessionFactory.close();
	}
	 public List<SAD003> listData() {
		 Session session = this.sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	        List<SAD003> listData = session.createQuery("FROM SAD003").list();	//..SELECT * FROM user_table;
	        transaction.commit();
	       
	        
	        //session.createSQLQuery("queryString");
	        
	    session.close();
	        return listData;
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
