package projectboard.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @ApiModelProperty(example = "해시태그 고유 번호")
    private Long id;
    @ApiModelProperty(example = "해시태그 이름")
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
