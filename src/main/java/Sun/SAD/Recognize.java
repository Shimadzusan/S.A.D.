package Sun.SAD;

import java.util.ArrayList;
import java.util.List;

import database_unit.DatabaseInterface;
import database_unit.Launch_two;
import database_unit.SAD003;
import source_unit.*;

public class Recognize {
	Recognize() {
		
	}
	
	Recognize(SourceInterface source, DatabaseInterface parameters) {
		System.out.println("..recognize");
		System.out.println(source.getData());
		universalInit(source.getData());
		
		System.out.println("recog param: " + parameters.getParametrs()[1]);
		/*recognition source and parameters*/
		/*	получаем параметры...
		 *	автоматически генерируем java_bean и другие необходимые ресурсы,
		 *	то есть: SAD003 и сопутствующие xml-конфиги, КАК??
		 *	..используем все это
		 *	запись в базу данных 
		 */
		parameters.setStructuredData();	//..list from SAD003, set to main_to!!
	}
	
	private void universalInit(String text) {
		List<SAD003> list_for_database = new ArrayList<SAD003>();	//..SAD003 MUST BE!!! INTERFACE, created by mainConfig
		//***to parse the text
			String[] dayList = text.split("\\r\\n");	//total notes for day (code 13 10 is new line(\\r\\n))
			List<String> facture = new ArrayList<String>();
				
				for(int i = 1; i < dayList.length; i++) {
					if(!dayList[i].contains("касса") && !dayList[i].contains("зп"))facture.add(dayList[i]);
				}

				for(int i = 0; i < facture.size(); i++) {
					Launch_two.main_two(getDateFormat(dayList[0]), getDeal(facture.get(i)),
							getVolume(facture.get(i)), getPayment(facture.get(i)));
				}
	}
	
	private String getDateFormat(String date) {
		String[] elements = date.split("\\.");
		String s = elements[2] + elements[1] + elements[0];
		return s;
	}
	
	private String getDeal(String deal) {
		String[] elements = deal.split(" ");
		String s = elements[0];
		return s;
	}
	
	private int getVolume(String volume) {
		String[] elements = volume.split(" ");
		String s = elements[1];
		return Integer.parseInt(s);
	}
	
	private String getPayment(String payment) {
		String[] elements = payment.split(" ");
		if(elements.length > 2) return elements[2];
		else return null;
	}
}
