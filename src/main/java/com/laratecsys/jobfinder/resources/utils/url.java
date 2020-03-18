package com.laratecsys.jobfinder.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class url {

	public static String decodeUrl(String decode){
		
		try {
			return URLDecoder.decode(decode,"UTF-8");
		}catch(UnsupportedEncodingException e) {
			return "";
		}
		
		
	}
	
	public static List<Integer> decoderString(String decode){
		
		
		String[] aStrings = decode.split(",");
		List<Integer> aInteger = new ArrayList<>();
		
		for (int i = 0; i < aStrings.length; i++) {
			aInteger.add(Integer.parseInt(aStrings[i]));
			
		}
		return aInteger;
		
	}
}
