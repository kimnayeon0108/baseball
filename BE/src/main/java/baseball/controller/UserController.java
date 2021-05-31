package baseball.controller;

import baseball.service.UserService;
import baseball.service.dto.KakaoProfile;
import baseball.service.dto.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private ObjectMapper objectMapper;
    private UserService userService;

    public UserController(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) {
        ResponseEntity<String> responseWithAccessToken = sendRequestWithAuthorizationCode(code);

        String accessToken = null;
        try {
            accessToken = getAccessToken(responseWithAccessToken);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info("카카오 엑세스 토큰: {}", accessToken);

        ResponseEntity<String> userInfo = sendRequestWithAccessToken(accessToken);

        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(userInfo.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info("카카오 user name: {}", kakaoProfile.getKakaoAccount().getProfile().getNickname());

        userService.save(kakaoProfile, accessToken);
        return userInfo.getBody();
    }

    // 리소스 서버인 카카오로 authorization code 외 클라이언트를 식별할 수 있는 필요한 정보를 담아 request 를 보낸다.
    private ResponseEntity<String> sendRequestWithAuthorizationCode(String code) {
        //POST방식으로 key=value 데이터 request 보낸다 (카카오쪽으로)
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "ae9ee4e74e683de2a2c27d412178fea2");
        params.add("redirect_add", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        // Http 요청하기 - POST 방식으로 - 그리고 response 변수의 응답 받는다.
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        return response;
    }

    private String getAccessToken(ResponseEntity<String> responseEntity) throws JsonProcessingException {
        OAuthToken oAuthToken = objectMapper.readValue(responseEntity.getBody(), OAuthToken.class);
        return oAuthToken.getAccess_token();
    }

    private ResponseEntity<String> sendRequestWithAccessToken(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        return response;
    }
}
