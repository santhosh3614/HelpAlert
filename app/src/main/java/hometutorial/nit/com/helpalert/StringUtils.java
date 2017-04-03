package hometutorial.nit.com.helpalert;

/**
 * Created by nareshit on 23-Nov-16.
 */

public class StringUtils {

    private StringUtils(){

    }

    /**
     * For empty string and null string false is returned
     * @param s
     * @return true if passed string is not null and empty.
     */
    public static boolean isValidString(String s){
        return s!=null && !("".equals(s.trim()));
    }

}
