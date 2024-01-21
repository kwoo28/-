package projectboard.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostTag {
    @ApiModelProperty(example = "해시태그 고유 번호")
    private Long tag_id;
    @ApiModelProperty(example = "게시글 고유 번호")
    private Long post_id;

    @Builder
    public PostTag(Long tag_id, Long post_id) {
        this.tag_id = tag_id;
        this.post_id = post_id;
    }
}
