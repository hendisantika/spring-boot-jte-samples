package id.my.hendisantika.jteforms.repository;

import id.my.hendisantika.jteforms.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jte-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 18/03/25
 * Time: 07.32
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface UserRepository extends ListCrudRepository<User,Long> {
}
