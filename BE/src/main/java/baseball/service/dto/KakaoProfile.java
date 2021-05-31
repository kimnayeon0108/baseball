package baseball.service.dto;

public class KakaoProfile {

    private Integer id;
    private String connectedAt;
    private Properties properties;
    private KakaoAccount kakaoAccount;

    public KakaoProfile() {
    }

    public KakaoProfile(Integer id, String connectedAt, Properties properties, KakaoAccount kakaoAccount) {
        this.id = id;
        this.connectedAt = connectedAt;
        this.properties = properties;
        this.kakaoAccount = kakaoAccount;
    }

    public Integer getId() {
        return id;
    }

    public String getConnectedAt() {
        return connectedAt;
    }

    public Properties getProperties() {
        return properties;
    }

    public KakaoAccount getKakaoAccount() {
        return kakaoAccount;
    }

    class Properties {

        private String nickname;
        private String profileImage;
        private String thumbnailImage;

        public Properties() {
        }

        public Properties(String nickname, String profileImage, String thumbnailImage) {
            this.nickname = nickname;
            this.profileImage = profileImage;
            this.thumbnailImage = thumbnailImage;
        }

        public String getNickname() {
            return nickname;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public String getThumbnailImage() {
            return thumbnailImage;
        }
    }

    public class KakaoAccount {

        private Boolean profileNeedsAgreement;
        private Profile profile;

        public KakaoAccount() {
        }

        public KakaoAccount(Boolean profileNeedsAgreement, Profile profile) {
            this.profileNeedsAgreement = profileNeedsAgreement;
            this.profile = profile;
        }

        public Boolean getProfileNeedsAgreement() {
            return profileNeedsAgreement;
        }

        public Profile getProfile() {
            return profile;
        }

        public class Profile {

            private String nickname;
            private String thumbnailImageUrl;
            private String profileImageUrl;
            private Boolean isDefaultImage;

            public Profile() {
            }

            public Profile(String nickname, String thumbnailImageUrl, String profileImageUrl, Boolean isDefaultImage) {
                this.nickname = nickname;
                this.thumbnailImageUrl = thumbnailImageUrl;
                this.profileImageUrl = profileImageUrl;
                this.isDefaultImage = isDefaultImage;
            }

            public String getNickname() {
                return nickname;
            }

            public String getThumbnailImageUrl() {
                return thumbnailImageUrl;
            }

            public String getProfileImageUrl() {
                return profileImageUrl;
            }

            public Boolean getIsDefaultImage() {
                return isDefaultImage;
            }
        }
    }
}




