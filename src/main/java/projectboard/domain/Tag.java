package projectboard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
public class Tag {
    @Schema(example = "해시태그 고유 번호")
    private Long id;
    @Schema(example = "해시태그 이름")
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
