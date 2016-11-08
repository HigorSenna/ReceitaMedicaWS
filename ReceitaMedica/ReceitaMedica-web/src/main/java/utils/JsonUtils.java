package utils;

import org.primefaces.json.JSONObject;

import com.google.gson.Gson;

public abstract class JsonUtils {
	
	private static Gson gson;
	private static JSONObject jsonObject;
	
	public static String parseJson(Object objeto){
		gson = new Gson();
		return gson.toJson(objeto);
	}
	
	public static JSONObject parseObject(String json){
		jsonObject = new JSONObject(json);
		return jsonObject;
	}
}
