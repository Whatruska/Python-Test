import java.io.*;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

public class Person {
    TreeMap<String, String> properties = new TreeMap<>();
    public Person(String filePath) {
        readPropsFromFile(filePath);
    }

    public void readPropsFromFile (String filePath){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/" + filePath)));
            String line = reader.readLine();
            while (line != null){
                if (!line.matches("[{}]")){
                    int index = line.indexOf(":");
                    String key = getFormatedSubstring(line, 0, index);
                    String value = getFormatedSubstring(line, index + 1, line.length() - 1);
                    properties.put(key, value);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + filePath + " not found. Create one, please.");
        } catch (IOException e) {
            System.out.println("Reading error...");
            e.printStackTrace();
        }
    }

    public String getFormatedSubstring(String srcLine, int startIndex, int finishIndex){
        char dm = (char) 34;
        return srcLine.substring(startIndex, finishIndex).trim().replaceAll(String.valueOf(dm), "");
    }

    @Override
    public String toString() {
        AtomicReference<String> result = new AtomicReference<>("");
        properties.keySet().stream().forEach((elem) -> {
            result.set(result.get() + elem + " : " + properties.get(elem) + "\n");
        });
        return result.get();
    }
}
