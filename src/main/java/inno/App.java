package inno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class App {

    @Autowired
    private DataReadable reader;
    @Autowired
    private List<DataTransformable> operations;
    @Autowired
    private DataWritable writer;

    public void execute(){
        List<Data> list = reader.read();

        list.forEach(data -> operations.forEach(f->f.transform(data)));
        List<Data> correctList = list.stream().filter((data)->data.getError()==null).collect(Collectors.toList());

        writer.write(correctList);
    }

    public DataReadable getReader() {
        return reader;
    }

    public List<DataTransformable> getOperations() {
        return operations;
    }

    public DataWritable getWriter() {
        return writer;
    }
    public void setReader(DataReadable reader) {
        this.reader = reader;
    }
    public void setOperations(List<DataTransformable> operations) {
        this.operations = operations;
    }
    public void setWriter(DataWritable writer) {
        this.writer = writer;
    }
}
