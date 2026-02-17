package gnu.project.pbl.auth.userinfo;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public interface OauthUserInfo {

    String getSocialId();

    String getEmail();

    String getName();
    
}
