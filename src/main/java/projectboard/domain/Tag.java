package projectboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
