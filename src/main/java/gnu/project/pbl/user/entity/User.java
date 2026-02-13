package gnu.project.pbl.user.entity;


import gnu.project.pbl.auth.entity.OauthInfo;
import gnu.project.pbl.auth.entity.OauthUser;
import gnu.project.pbl.auth.enumerated.SocialProvider;
import gnu.project.pbl.common.entity.BaseEntity;
import gnu.project.pbl.common.enumerated.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customer")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity implements OauthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

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
        final UUID uuid,
        final SocialProvider provider
    ) {
        final OauthInfo oauthInfo = OauthInfo.of(email, name, uuid, provider);

        return new User(
            null,
            null,
            false,
            UserRole.USER,
            oauthInfo
        );
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
