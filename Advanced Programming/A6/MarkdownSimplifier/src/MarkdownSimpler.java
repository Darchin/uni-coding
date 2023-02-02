import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class MarkdownSimpler {
    final String content;

    public MarkdownSimpler(Path filePath) throws IOException {
        this.content = Files.readString(filePath);
    }

    public String separate() {
        String replaced = this.content.replaceAll(RegexPattern.headers, "");
        replaced = replaced.replaceAll(RegexPattern.italic, "$1");
        replaced = replaced.replaceAll(RegexPattern.bold, "$1");
        replaced = replaced.replaceAll(RegexPattern.link, "$1");
        replaced = replaced.replaceAll(RegexPattern.image, " $1 ");
        replaced = replaced.replaceAll(RegexPattern.code, "");
        replaced = replaced.replaceAll(RegexPattern.bullet, "");
        return replaced;
    }
}

