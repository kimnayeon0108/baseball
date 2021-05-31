package baseball.service;

import baseball.domain.User;
import baseball.repository.UserRepository;
import baseball.service.dto.KakaoProfile;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(KakaoProfile kakaoProfile, String accessToken) {
        String name = kakaoProfile.getKakaoAccount().getProfile().getNickname();
        User user = new User(name, accessToken);
        userRepository.save(user);
    }
}
