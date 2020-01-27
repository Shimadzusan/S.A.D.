package source_unit;

public class Parameters {

	public final String split = "\\r\\n";	//.. divider an users text, total notes for day (code 13 10 is new line(\\r\\n))
	public final String splitFacture = " ";	//.. divider in deals structure
//.. date-facture-info(!!!) main kinds!	there exist anyway!
	public final String customerDate = "";//"00.00.0000, dd.mm.yyyy(..other customer formats)";
	public final String[] infoMarker = {"касса утро", "касса вечер", "зп"}; //.. для разделения на facture and info
	
	public String getSplit() {
		return split;
	}
	public String getSplitFacture() {
		return splitFacture;
	}
	public String getCustomerDate() {
		return customerDate;
	}
	public String[] getInfoMarker() {
		return infoMarker;
	}
	
}
