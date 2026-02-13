package gnu.project.pbl.auth.factory;

import gnu.project.pbl.auth.entity.OauthUser;
import gnu.project.pbl.auth.enumerated.SocialProvider;
import gnu.project.pbl.auth.userinfo.OauthUserInfo;
import gnu.project.pbl.common.enumerated.UserRole;
import gnu.project.pbl.pbl.entity.Customer;
import gnu.project.pbl.pbl.repository.CustomerRepository;
import gnu.project.pbl.pbl.entity.Owner;
import gnu.project.pbl.pbl.repository.OwnerRepository;
import gnu.project.pbl.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OauthUserFactory {

    private final CustomerRepository customerRepository;

    public OauthUser findOrCreateUser(OauthUserInfo userInfo, SocialProvider provider,
        UserRole userRole) {
        return switch (userRole) {
            case USER -> findOrCreateCustomer(userInfo, provider);
            case ADMIN -> null;
            case GUEST -> null;
             /*
            TODO : 추후 추가 예정
             */

        };
    }



    private User findOrCreateUser(OauthUserInfo userInfo, SocialProvider provider) {
        return customerRepository.findByOauthInfo_SocialId(userInfo.getSocialId())
            .orElseGet(() -> customerRepository.save(
                Customer.createFromOAuth(userInfo.getEmail(), userInfo.getName(),
                    userInfo.getSocialId(),
                    provider)
            ));
    }

}
