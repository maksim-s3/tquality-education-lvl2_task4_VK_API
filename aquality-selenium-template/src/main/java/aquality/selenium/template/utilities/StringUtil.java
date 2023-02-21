package aquality.selenium.template.utilities;

public class StringUtil {
    public static String trimStringBySubstrings(String text, String start, String end) {
        int indexStart = text.indexOf(start);
        int indexEnd = text.indexOf(end);
        return text.substring(indexStart, indexEnd);
    }
}
