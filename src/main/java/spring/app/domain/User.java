package spring.app.domain;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Expose
    @NonNull
    @NotNull
    @Length(max = 20)
    private String firstName;

    @Expose
    @NonNull
    @NotNull
    @Length(min = 2, max = 20)
    private String lastName;

    @Expose
    @NonNull
    @NotNull
    private String userName;

    @Expose(serialize = false)
    @NonNull
    @NotNull
    @Length(min = 5, max = 30)
    private String password;

    @Expose(serialize = false)
    @NonNull
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Expose
    @NonNull
    @NotNull
    @URL
    private String imageUrl;

    @Expose
    private LocalDateTime created = LocalDateTime.now();
    @Expose
    private LocalDateTime modified = LocalDateTime.now();
}
