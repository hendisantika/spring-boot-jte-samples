package id.my.hendisantika.jteforms.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jte-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 18/03/25
 * Time: 07.31
 * To change this template use File | Settings | File Templates.
 */
@Data
@Builder
@Table("users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    @NotBlank(message = "The First Name field should not be blank.")
    private String firstName;
    @NotBlank(message = "The Last Name field should not be blank.")
    private String lastName;
    private String email;
    private String country;
    private String streetAddress;
    private String city;
    private String region;
    private String postalCode;
}
