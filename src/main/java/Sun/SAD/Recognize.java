package Sun.SAD;

import database_unit.DatabaseInterface;
import source_unit.*;

public class Recognize {
	Recognize() {
		
	}
	
	Recognize(SourceInterface source, DatabaseInterface parameters) {
		System.out.println("..recognize");
		System.out.println(source.getData());
		System.out.println("recog param: " + parameters.getParametrs()[1]);
		/*recognition source and parameters*/
		/*	получаем параметры...
		 *	автоматически генерируем java_bean и другие необходимые ресурсы,
		 *	..используем все это
		 *	запись в базу данных 
		 */
		parameters.setStructuredData();
	}
}
