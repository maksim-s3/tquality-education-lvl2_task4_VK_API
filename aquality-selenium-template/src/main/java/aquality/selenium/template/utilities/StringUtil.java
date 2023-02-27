package aquality.selenium.template.utilities;

public class StringUtil {
    public static String trimStringBySubstrings(String text, String start, String end) {
        return text.substring(text.indexOf(start), text.indexOf(end));
    }
}
