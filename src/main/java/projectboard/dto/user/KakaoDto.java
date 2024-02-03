package projectboard.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class KakaoDto {
    private Long id;
    private String nickname;
}
