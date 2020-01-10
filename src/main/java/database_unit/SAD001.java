package database_unit;

public class SAD001 {
	/*	..в теории должен генерироваться автоматически*/
	
	 private String date;
	 private String foto;
	 private String copy;
	 private String print;
	 private String other;

	    /**
	     * Default Constructor
	     */
	    public SAD001() {
	    }

	    /**
	     * Plain constructor
	     */
	    public SAD001(String date, String foto, String copy, String print, String other) {
	        this.date = date;
	        this.foto = foto;
	        this.copy = copy;
	        this.print = print;
	        this.other = other;
	    }

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getFoto() {
			return foto;
		}

		public void setFoto(String foto) {
			this.foto = foto;
		}

		public String getCopy() {
			return copy;
		}

		public void setCopy(String copy) {
			this.copy = copy;
		}

		public String getPrint() {
			return print;
		}

		public void setPrint(String print) {
			this.print = print;
		}

		public String getOther() {
			return other;
		}

		public void setOther(String other) {
			this.other = other;
		}
	    
		/**
	     * toString method (optional)
	     */
	    @Override
	    public String toString() {
	        return "Data:\n" +
	                "date: " + this.date +
	                "\nFoto: " + this.foto + "\n" +
	                "Copy: " + copy + "\n" +
	                "Print: " + print + "\n" +
	                "Other: " + other + "\n";
	    }

}
