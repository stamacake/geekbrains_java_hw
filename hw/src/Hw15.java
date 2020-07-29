import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.*;

public class Hw15 {

    public static int countSeq(String fileName, String sequence) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(fileName, "r");
        char[] seq = sequence.toCharArray();
        FileChannel inChannel = aFile.getChannel();
        int i = 0;
        long pos = inChannel.position();
        int count = 0;
        ByteBuffer buf = ByteBuffer.allocate(1);
        int bytesRead = -5;
        char w1='1';
        char w2[];
        ByteBuffer buf2 = ByteBuffer.allocate(sequence.length()-1);
        while (bytesRead != -1) {
            pos = inChannel.position();
            buf.clear();
            bytesRead = inChannel.read(buf);
            buf.flip();
            if(buf.hasRemaining())  w1 = (char) buf.get();
          //  System.out.println(w1);

            if ( w1== seq[i]) {
                i++;
                buf2.clear();
                bytesRead = inChannel.read(buf2);
                buf2.flip();
                while(buf2.hasRemaining()){
                    if((char) buf2.get()==seq[i]){
                        i++;
                    } else break;
                }
                if(i==sequence.length()) count++;
                i=0;
                inChannel.position(pos+1);
            }

        }
        return count;
    }


    public static void mergeFiles(String folderRoot) throws IOException {
        Path outFile=Paths.get("hw/concated/concatedFile.txt");

        File file1 = new File(folderRoot);
        List<File> filesInFolder2 = new ArrayList<File>();
        File[] files = file1.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(!f.isDirectory()) {
                    filesInFolder2.add(f);
                }
            }
        }

        try(FileChannel out=FileChannel.open(outFile, CREATE, WRITE)) {
            for(File file:filesInFolder2){
                Path inFile= Paths.get(String.valueOf(file));
                try(FileChannel in=FileChannel.open(inFile, READ)) {
                    for(long p=0, l=in.size(); p<l; )
                        p+=in.transferTo(p, l-p, out);
                }
            }
        }
    }


    public static List<File> filesWithSize(String folderRoot) throws IOException {
        List<File> filesInFolder = Files.walk(Paths.get(folderRoot))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        List<File> filesInFolder2 = new ArrayList<File>();
        for(File file:filesInFolder){
            RandomAccessFile aFile = new RandomAccessFile(file, "r");
            FileChannel inChannel = aFile.getChannel();
            if(inChannel.size()<100000) filesInFolder2.add(file);
        }
        return filesInFolder2;
    }

    public static void main(String[] args) throws IOException {

        System.out.println(countSeq("hw/resources/text.txt", "1234"));
        mergeFiles("hw/resources/files");

        System.out.println(   filesWithSize("hw/resources/tree1"));
    }
}
