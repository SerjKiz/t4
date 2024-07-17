
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import inno.*;

public class Tests {
    @Test
    void testOperationAppTypeChecker(){
        Data data = new Data("vtb4060209 Кизласов Сергей игорЕвич 16.07.2024 10:15:15 excel");
        DataTransformable operation = new OperationAppTypeChecker();
        operation.transform(data);
        Assertions.assertEquals(data.getAppType(), "other:excel");
        data.setAppType( "web");
        operation.transform(data);
        Assertions.assertEquals(data.getAppType(), "web");
        data.setAppType("mobile");
        operation.transform(data);
        Assertions.assertEquals(data.getAppType(), "mobile");
    }

    @Test
    void testOperationDateChecker(){
        Data data = new Data("vtb4060209 Кизласов Сергей игорЕвич 16.07.2024 10:15:15 excel");
        DataTransformable operation = new OperationDateChecker();
        operation.transform(data);
        Assertions.assertEquals(data.getDate(), Timestamp.valueOf(LocalDateTime.parse("16.07.2024 10:15:15", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))));
        data = new Data("");
        Assertions.assertEquals(data.getDate(), null);
        Assertions.assertNotNull(data.getError());
    }

    @Test
    void testOperationCapitalizeNameFirstLetter(){
        Data data = new Data("vtb4060209 Кизласов Сергей игорЕвич 16.07.2024 10:15:15 excel");
        DataTransformable operation = new OperationCapitalizeNameFirstLetter();
        operation.transform(data);
        Assertions.assertEquals(data.getLastname(), "Кизласов");
        Assertions.assertEquals(data.getFirstname(), "Сергей");
        Assertions.assertEquals(data.getSecondName(), "Игоревич");
    }
    @Test
    void testApp(){
        App app = new App();
        List<Data> srcList = new ArrayList<>();
        String fileLine = "vtb4060209 Кизласов Сергей игорЕвич 16.07.2024 10:15:15 excel";
        srcList.add(new Data(fileLine));

        app.setReader(()->srcList);

        List<DataTransformable> opList = new ArrayList<>();
        opList.add(new OperationAppTypeChecker());
        app.setOperations(opList);

        List<Data> resultList = new ArrayList<>();
        app.setWriter((List<Data> list)->{ resultList.addAll(list); });

        List<Data> etalon = new ArrayList<>();
        Data etalonData = new Data(fileLine);
        etalonData.setAppType("other:excel");;
        etalon.add(etalonData);

        app.execute();
        Assertions.assertEquals(resultList, etalon);

    }

}
