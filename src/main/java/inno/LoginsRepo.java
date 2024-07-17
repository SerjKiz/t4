package inno;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableJpaRepositories
public interface LoginsRepo extends CrudRepository<Logins, Long> {

}