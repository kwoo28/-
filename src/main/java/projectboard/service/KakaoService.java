package projectboard.service;

import projectboard.dto.user.KakaoDto;

public interface KakaoService {
    KakaoDto getKakaoInfo(String code);
}
