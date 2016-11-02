package utils;

public abstract class UrlUtils {
	
	public static String getURL(String complemento){
		return ParamUtils.DOMINIO + complemento;
	}
	
}
