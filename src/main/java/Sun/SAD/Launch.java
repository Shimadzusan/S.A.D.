package Sun.SAD;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import source_unit.*;
public class Launch {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class);
		
		Recognize recognize = context.getBean("recognize", Recognize.class);
		System.out.println("..end");

	}

}