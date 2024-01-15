package projectboard.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @ApiModelProperty(example = "게시글 고유 번호")
    private Long id;
    @ApiModelProperty(example = "게시글 유저 번호")
    private Long userId;
    @ApiModelProperty(example = "게시글 제목")
    private String title;
    @ApiModelProperty(example = "게시글 내용")
    private String content;
    @ApiModelProperty(example = "게시글 해시 태그")
    private String hashTag;
    @ApiModelProperty(example = "게시글 생성 일자")
    private LocalDateTime createdAt;

    public Post(Long userId, String title, String content, String hashTag) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.hashTag = hashTag;
    }
}