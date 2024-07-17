package inno;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class OperationDateChecker implements DataTransformable {


    @Override
    @LogTransformation("log.txt")
    public void transform(Data data) {
        if (data.getDate() == null){
            data.setError("Error: Date is empty");
        }
    }
}