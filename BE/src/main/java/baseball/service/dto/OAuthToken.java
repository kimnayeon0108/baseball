package baseball.service.dto;

public class OAuthToken {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;
    private int expires_in;
    private int refresh_token_expires_in;

    public OAuthToken() {
    }

    public OAuthToken(String access_token, String token_type, String refresh_token, String scope, int expires_in, int refresh_token_expires_in) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.scope = scope;
        this.expires_in = expires_in;
        this.refresh_token_expires_in = refresh_token_expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public int getRefresh_token_expires_in() {
        return refresh_token_expires_in;
    }

    public String getScope() {
        return scope;
    }
}
