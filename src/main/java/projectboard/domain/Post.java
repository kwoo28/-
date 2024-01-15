package projectboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String hashTag;
    private LocalDateTime createdAt;

    public Post(Long userId, String title, String content, String hashTag) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.hashTag = hashTag;
    }
}