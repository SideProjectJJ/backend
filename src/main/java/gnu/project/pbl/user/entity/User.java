package gnu.project.pbl.user.entity;


import gnu.project.pbl.auth.entity.OauthInfo;
import gnu.project.pbl.auth.entity.OauthUser;
import gnu.project.pbl.auth.enumerated.SocialProvider;
import gnu.project.pbl.common.entity.BaseEntity;
import gnu.project.pbl.common.enumerated.Gender;
import gnu.project.pbl.common.enumerated.UserRole;
import gnu.project.pbl.user.dto.request.UserRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity implements OauthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BINARY(16)", unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "height")
    private Short height;

    @Column(name = "weight")
    private Short weight;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Embedded
    private OauthInfo oauthInfo;


    public static User createFromOAuth(
        final String email,
        final String name,
        final String socialId,
        final SocialProvider provider
    ) {
        final OauthInfo oauthInfo = OauthInfo.of(socialId, email,name , provider);

        return new User(
            null,
            null,
            null,
            null,
            null,
            null,
            false,
            UserRole.USER,
            oauthInfo
        );
    }
    @PrePersist
    public void prePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }
    public void signUp(UserRequest request){
        this.phoneNumber = request.phoneNumber();
        this.height = request.height();
        this.weight = request.weight();
        this.gender = request.gender();
    }

    public boolean isActive(){
        return isDeleted;
    }
    public void reactivate(){
        isDeleted = false;
    }
    @Override
    public UserRole getUserRole() {
        return this.userRole != null ? this.userRole : UserRole.USER;
    }

    @Override
    public OauthInfo getOauthInfo() {
        return this.oauthInfo;
    }


}
