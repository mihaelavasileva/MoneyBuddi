package controller.manager;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import exceptions.InvalidDataException;
import model.Currency;

public class CurrencyConverter {
	
public static double convert(double amount,Currency from,Currency to) throws InvalidDataException  {
		
		if(from.getType().equals(to.getType())){
			return amount;
		}
		try {
		URL url=new URL("http://data.fixer.io/api/latest?access_key=3f529a134e44b9e698123926bbc9a537&symbols="+
	    from.getType().toString()+","+to.getType().toString());
		
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		InputStream responseBodyStream=con.getInputStream();
		
		StringBuffer sb=new StringBuffer();
		int b=responseBodyStream.read();
		while(b!=-1) {
			sb.append((char)b);
			b=responseBodyStream.read();
		}
		
		
		JsonParser jp=new JsonParser();
		JsonObject jobj=(JsonObject) jp.parse(sb.toString());
		double temp1=jobj.getAsJsonObject("rates").get(from.getType().toString()).getAsDouble();
		double temp2=jobj.getAsJsonObject("rates").get(to.getType().toString()).getAsDouble();
		
		return round(amount*temp2/temp1);
		}catch(IOException e) {
			throw new InvalidDataException("Something went wrong with the Currency API");
		}
		
	}

		public static double round(double value) {
			int places=2;
		   
		    BigDecimal bd = new BigDecimal(value);
		    bd = bd.setScale(places, RoundingMode.HALF_UP);
		    return bd.doubleValue();
		}

}
