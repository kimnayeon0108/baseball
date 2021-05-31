package baseball.domain;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private Long id;

    private String name;
    private String accessToken;

    public User(String name, String accessToken) {
        this.name = name;
        this.accessToken = accessToken;
    }
}
