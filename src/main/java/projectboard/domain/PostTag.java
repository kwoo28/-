package projectboard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostTag {
    @Schema(example = "해시태그 고유 번호")
    private Long tagId;
    @Schema(example = "게시글 고유 번호")
    private Long postId;

    @Builder
    public PostTag(Long tagId, Long postId) {
        this.tagId = tagId;
        this.postId = postId;
    }
}
