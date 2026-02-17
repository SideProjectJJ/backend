package gnu.project.pbl.user.repository;

import gnu.project.pbl.user.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUuid(final UUID uuid);

    boolean existsByUuid(final UUID uuid);

    Optional<User> findByOauthInfo_SocialId(String socialId);
}

