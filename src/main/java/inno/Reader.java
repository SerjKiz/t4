package inno;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Component
public class Reader implements DataReadable {
    @Value("${folder.path}")
    private String path;

    private boolean folderExists(){
        return Files.exists(Paths.get(path));
    }

    private List<String> readFile(File file){
        List<String> lines= new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null){
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private List<File> getFileList(){
        List<File> list = new ArrayList<>();

        if (!folderExists()) return list;

        File folder = new File(path);
        File [] files = folder.listFiles();
        for(File file:files){
            if (file.isFile()){
                list.add(file);
            }
        }
        return list;
    }


    @Override
    public List<Data> read() {
        List<File> fileList = getFileList();

        List<String> lines = new ArrayList<>();
        fileList.forEach(file -> lines.addAll(readFile(file)));
        List<Data> dataList = new ArrayList<>();
        lines.forEach(line->dataList.add(new Data(line)));
        return dataList;    }
}