package database_unit;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Launch_two {
	 private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		sessionFactory = new Configuration().configure().buildSessionFactory();

			Launch_two thisObject = new Launch_two();
	        List<SAD001> listData = thisObject.listData();
	        for (SAD001 data : listData) {
	            System.out.println(data);
	        }
	        
	        System.out.println("===================================");
        sessionFactory.close();
	}
	 public List<SAD001> listData() {
		 Session session = this.sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	        List<SAD001> listData = session.createQuery("FROM SAD001").list();	//..SELECT * FROM user_table;
	        transaction.commit();
	        
	    session.close();
	        return listData;
	 }

}
