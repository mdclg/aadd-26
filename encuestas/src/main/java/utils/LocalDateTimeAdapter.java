package utils;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter  extends XmlAdapter<String, LocalDateTime> {
	
	public LocalDateTime unmarshal(String v) throws Exception {
		return LocalDateTime.parse(v);
	}

	public String marshal(LocalDateTime v) throws Exception {
		return v.toString();
	}

}
