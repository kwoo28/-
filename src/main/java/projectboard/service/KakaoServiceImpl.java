package projectboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import projectboard.domain.User;
import projectboard.repository.UserMapper;
import projectboard.util.JwtUtil;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoServiceImpl implements KakaoService{
    @Value("${jwt.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l;

    private final UserMapper userMapper;

    //카카오 로그인 정보 받기
    @Override
    public String kakaoLogin(String code){

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

        } catch (ParseException e) {
            throw new RuntimeException("해당 json 데이터가 없습니다.");
        }

        User user = getKakaoInfoWithToken(accessToken);

        if(userMapper.findUserByUserId(user.getUserId())==null){
            userMapper.createUser(user);
            log.info("{}의 아이디로 카카오 회원가입합니다.", user.getUserId());

        }

        User findUser = userMapper.findUserByUserId(user.getUserId());
        String token = JwtUtil.createJwt(findUser.getId(), key, expireTimeMs);
        log.info("{}의 아이디로 카카오 로그인을 시도합니다. 토큰 : {}", findUser.getUserId(), token);

        return token;
    }

    private User getKakaoInfoWithToken(String accessToken){

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

            JSONObject account = (JSONObject) jsonObject.get("kakao_account");
            JSONObject profile = (JSONObject) account.get("profile");

            String id = String.valueOf(jsonObject.get("id"));
            String nickname = String.valueOf(profile.get("nickname"));
            String email = String.valueOf(account.get("email"));

            UUID password = UUID.randomUUID();

            return User.builder()
                    .userId(id+"_"+email)
                    .userPw(password.toString())
                    .userName(email+"_"+nickname)
                    .email(email)
                    .build();

        } catch (ParseException e){
            throw new RuntimeException("해당 json 데이터가 없습니다.");
        }
    }

}
