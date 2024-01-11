package framework;

public class CommonFunctions {
    public static String removeMatchingText(String text, String regex) {
        return text.replaceAll(regex, "");
    }
}
