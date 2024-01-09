package framework;

public class CommonFunction {
    public static int regexGetMatch(String text, String regex) {
        return Integer.parseInt(text.replaceAll(regex, ""));
    }
}
