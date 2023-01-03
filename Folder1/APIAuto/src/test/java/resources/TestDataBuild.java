package resources;

import java.util.ArrayList;
import java.util.List;

import PoJo.AddLocation;
import PoJo.Location;

public class TestDataBuild {

	public AddLocation addPlacePayload(String name, String language, String address) {
	
		AddLocation al = new AddLocation();
		al.setAccuracy(100);
		al.setAddress(address);
		al.setName(name);
		al.setPhone_number("(+91)9838933937");
		al.setWebsite("http://google.com");
		al.setLanguage(language);		
		List<String> types = new ArrayList<String>();
					types.add("Type 1");
					types.add("Type 2");
					
		al.setTypes(types);
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		al.setLocation(loc);
		return al;
	}
}
