package source_unit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;

public class SourceDisk implements SourceInterface {
	private String data;
	
	@Value("${SourceDisk.address}")
	private String address;
//.. other properties in class Parameters

	public SourceDisk() throws IOException {
		
	}
	
	@PostConstruct
	public void doMyInit() throws IOException {
	/**	..так как ясность в понимании работы Spring отсутствует*/
		this.data = initData();
	}
	
	private String initData() throws IOException {
		FileInputStream fin = new FileInputStream(address);  
        byte[] buffer = new byte[fin.available()];
        fin.read(buffer, 0, buffer.length);
        String s = new String(buffer, "utf-8");
		return s;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Parameters getParameters() {
		return new Parameters();
	}


}
