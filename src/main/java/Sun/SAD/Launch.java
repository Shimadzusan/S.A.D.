package Sun.SAD;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import database_unit.DatabaseOne;
import source_unit.*;
public class Launch {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class);
		
//		Recognize recognize = context.getBean("recognize", Recognize.class);
//		DatabaseOne base  = context.getBean("database", DatabaseOne.class);
//		System.out.println(base.getDatabaseArray()[2]);
		System.out.println("..end");

	}

}