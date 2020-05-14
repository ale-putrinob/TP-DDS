package dominio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SinCaracteresRepetidos implements Validacion{

	public boolean cumpleCondicion(String password) {
		Pattern pat = Pattern.compile(".*((\\w)\\2\\2).*"); 
		Matcher mat = pat.matcher(password);
		
		return !(mat.matches());
	}

}
