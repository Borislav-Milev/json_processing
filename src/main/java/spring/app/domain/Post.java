package spring.app.domain;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Post {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Expose
    @NonNull
    @NotNull
    @Length(min = 3, max = 2048)
    @Column(columnDefinition = "TEXT")
    private String title;

    @Expose
    @NonNull
    @NotNull
    private String content;

    @Expose
    @NonNull
    @NotNull
    private String imageUrl;

    @Expose
    @ManyToOne
    private User author;

    @Expose(serialize = false)
    @NonNull
    @NotNull
    @Transient
    private Long authorId;

    @Expose
    private LocalDateTime created = LocalDateTime.now();
    @Expose
    private LocalDateTime modified = LocalDateTime.now();
}
