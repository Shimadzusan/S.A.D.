package Sun.SAD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database_unit.DatabaseInterface;
import database_unit.Launch_two;
import source_unit.*;

public class Recognize {
//..parametrs which must be incapsulated(configuration)
	String split = "\\r\\n";	//total notes for day (code 13 10 is new line(\\r\\n))
	String[] info = {"касса", "зп"};	//	date-facture-info(!!!) main kind!
	String[] containsData = {};
	String[] structureNotes = {"where is date?", "deal", "volume", "payment", "maybe commentar"};
	String formatDate = "00.00.0000, dd.mm.yyyy(..other customer formats)";
	
	String splitFacture = " ";
//..end
	
	Recognize() {
		
	}
	
	Recognize(SourceInterface source, DatabaseInterface parameters) {
		System.out.println("..recognize");
		System.out.println(source.getData());
		universalInit(source.getData(), parameters);
		
		/*recognition source and parameters*/
		/*	получаем параметры...
		 *	автоматически генерируем java_bean и другие необходимые ресурсы,
		 *	то есть: SAD003 и сопутствующие xml-конфиги, КАК??
		 *	..используем все это
		 *	запись в базу данных 
		 */
	}
	
	private void universalInit(String text, DatabaseInterface parameters) {
		String[] dayList = text.split(split);	//total notes for day (code 13 10 is new line(\\r\\n))
		List<String[]> facture = new ArrayList<String[]>();
				
		for(int i = 1; i < dayList.length; i++) {
			//if(!dayList[i].contains("касса") && !dayList[i].contains("зп"))facture.add(dayList[i]);
			if(getInfo(dayList[i]))facture.add(dayList[i].split(splitFacture));
		}
				
//..writing to db!!!
		List<HashMap> list = new ArrayList<HashMap>();
		for(int i = 0; i < facture.size(); i++) {
//..databese config!!!
				/*
				 * здесь определяем структуру Б.Д.,
				 * а именно: какие б.д. будет иметь поля
				 * и связываем эти поля с фактурой из текстового файла
				 */
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("date", getDateFormat(dayList[0]));
			hm.put("deals", facture.get(i)[0]);
			hm.put("volume", facture.get(i)[1]);
			
			if(facture.get(i).length > 2)hm.put("payment", facture.get(i)[2]);
			hm.put("commentar", "extract comment");
//.. Вторая база работать не будет, т.к. 10 строк сверху должны быть в параметрах, НО НЕ в этом методе!!!!
									
			list.add(hm);
		}
		
		//Launch_two.main_two(list);
		parameters.setStructuredData(list);
	}
	
	private boolean getInfo(String text) {
		boolean answer = true;
		for(int i = 0; i < info.length; i++) {
			if(text.contains(info[i]))answer = false;
		}
		return answer;
	}

	private String getDateFormat(String date) {
		String[] elements = date.split("\\.");
		String s = elements[2] + elements[1] + elements[0];
		return s;
	}
	
//	private String getDeal(String deal) {
//		String[] elements = deal.split(" ");
//		String s = elements[0];
//		return s;
//	}
//	
//	private int getVolume(String volume) {
//		String[] elements = volume.split(" ");
//		String s = elements[1];
//		return Integer.parseInt(s);
//	}
//	
//	private String getPayment(String payment) {
//		String[] elements = payment.split(" ");
//		if(elements.length > 2) return elements[2];
//		else return null;
//	}
}
