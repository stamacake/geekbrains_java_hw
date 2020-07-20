import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hw14 {

    public static int substingCount(String fileName, String substring) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName));
        int count = 0;
        int tmp = -5;
        boolean flag = true;
        int i =0;
        char c=(char) tmp;
        while (tmp != -1) {
            tmp = in.read();
            c = (char) tmp;

            if(i==substring.length()){
                count++;
                i=0;
            }

            if(c==substring.charAt(i)){
                i++;
            } else {
                if(i==substring.length()){
                    count++;
                    i=0;
                }
                i=0;
                if(c==substring.charAt(i)) i++;
            }

        }

        return count;
    }

    public static void filesConcat(String folderRoot) throws IOException {
        File file2 = new File("hw/concated");
        file2.mkdir();
        File concatedFile = new File("hw/concated/concatedFile.txt");
        concatedFile.createNewFile();
        FileWriter myWriter = new FileWriter("hw/concated/concatedFile.txt");
        List<File> filesInFolder = Files.walk(Paths.get(folderRoot))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        for(File file:filesInFolder){
            try {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    myWriter.write(line);
                    line = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        myWriter.close();

    }

    public static void deleteFolder2(File file) {
        File[] files = file.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder2(f);
                } else {
                    f.delete();
                }
            }
        }
        file.delete();
    }

    public static void deleteFolder(String folderName) throws IOException {
        Files.walk(Paths.get(folderName))
                .map(Path::toFile)
                .sorted((o1, o2) -> -o1.compareTo(o2))
                .forEach(File::delete);
    }

    public static void main(String[] args) throws IOException {

        System.out.println(substingCount("hw/resources/text.txt", "01"));
        filesConcat("hw/resources/files");
        File f = new File("hw/deletefolder");
        //deleteFolder("hw/deletefolder");
        deleteFolder2(f);
    }
}

