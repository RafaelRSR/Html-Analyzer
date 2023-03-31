import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlAnalyzer {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }

        String urlString = args[0];

        try {
            URL url = new URL(urlString);
            BufferedReader urlReader = new BufferedReader(new InputStreamReader(url.openStream()));

            int currentLevel = 0; // conta quantos foram fechados
            int maxLevel = 0; // 
            String deepestText = null;

            String currentLine;
            while ((currentLine = urlReader.readLine()) != null) {
                currentLine = currentLine.trim();
                if (currentLine.isEmpty()) {
                    continue;
                }
                if (currentLine.startsWith("</")) {
                    currentLevel--;
                } else if (currentLine.startsWith("<")) {
                    currentLevel++;
                } else {
                    if (currentLevel >= maxLevel) {
                        maxLevel = currentLevel;
                        deepestText = currentLine;
                    }
                }
            }

            urlReader.close();

            if (currentLevel != 0) {
                System.out.println("malformed HTML");
            } else {
                System.out.println(deepestText);
            }

        } catch (IOException e) {
            System.err.println("URL connection error");
        }
    }
}
