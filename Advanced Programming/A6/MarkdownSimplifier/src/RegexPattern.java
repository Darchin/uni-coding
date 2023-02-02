public class RegexPattern {
    static String headers = "^#+ ";
    static String italic = "(?<!\\*)\\*(?!\\*)([^\\*]*)\\*";
    static String bold = "\\*{2}([^*]*)\\*{2}";
    static String link = "(?<!\\!)\\[(.*)\\]\\([^\\)]*\\)";
    static String image = "!\\[(.*)\\]\\([^\\)]*\\)";
    static String code = "`*.`";
    static String bullet = "(?m)^\\+ ";
    //static String[] PatternList = new String[]{headers, italic, bold, link, image, code, bullet};
}
