package inno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
@Aspect
public class LogUnit {

    private String getAnnotationParam(ProceedingJoinPoint joinPoint){
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        LogTransformation annotation = method.getAnnotation(LogTransformation.class);
        String param = annotation.value();
        if (param.isEmpty()) param = "console";
        return param;
    }

    @Around("@annotation(edu.innotech.LogTransformation)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        String fileName = getAnnotationParam(joinPoint);

        String text = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss"));
        text = text + ": OPERATION=[" + joinPoint.getTarget().getClass()+"]";
        text = text + "; BEFORE EXECUTE=[" + Arrays.toString(joinPoint.getArgs()) + "]";

        Object object = joinPoint.proceed();

        text = text + "; AFTER EXECUTE=["+Arrays.toString(joinPoint.getArgs()) + "]";
        text = text + "; RESULT=["+object+"]";

        if (fileName == "console"){
            System.out.println(text);
        } else {
            try(FileWriter writer = new FileWriter(fileName, true)) {
                writer.write(text);
                writer.append('\n');
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return object;

    }
}