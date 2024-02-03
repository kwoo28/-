package projectboard.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import projectboard.dto.user.KakaoDto;

@Service
public class KakaoServiceImpl implements KakaoService{
    @Value("${kakao.client.id}")
    private String KAKAO_CLIENT_ID;
    @Value("${kakao.redirect.url}")
    private String KAKAO_REDIRECT_URL;

    //카카오 로그인 토큰 받기
    public KakaoDto getKakaoInfo(String code){

        if(code == null){
            throw new RuntimeException("카카오 로그인 인증 code가 없습니다.");
        }

        String accessToken = "";
        String refreshToken = "";

        //HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 생성
        //Map으로 사용하면 HttpEntity에 담기지 않음.
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "bbded244e5e6458f8c01595a2aada301");
        params.add("redirect_uri", "http://localhost:8080/user/kakao/callback");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나로 합침
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        try {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());

            accessToken = (String) jsonObject.get("access_token");
            refreshToken = (String) jsonObject.get("refresh_token");

        } catch (Exception e) {
            throw new RuntimeException("해당 json 데이터가 없습니다.");
        }

        return getKakaoInfoWithToken(accessToken);
    }

    private KakaoDto getKakaoInfoWithToken(String accessToken){

        //HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer " + accessToken);

        //HttpHeader 담기
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        try {
            //Response 데이터 파싱
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
            //kakao_acount -> profile
            JSONObject account = (JSONObject) jsonObject.get("kakao_account");
            JSONObject profile = (JSONObject) account.get("profile");

            Long id = (Long) jsonObject.get("id");
            String nickname = String.valueOf(profile.get("nickname"));

            return KakaoDto.builder()
                    .id(id)
                    .nickname(nickname).build();

        } catch (Exception e){
            throw new RuntimeException("해당 json 데이터가 없습니다.");
        }
    }
}
