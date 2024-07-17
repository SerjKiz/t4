package inno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "inno")
public class Start {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Start.class);
        App app = ctx.getBean(App.class);
        app.execute();
    }
}
