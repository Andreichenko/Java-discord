package bot.repositories;

import bot.entities.AliasEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AliasEntityRepository extends CrudRepository<AliasEntity, String> {
    Set<AliasEntity> findAllByServerId(String serverId);
    AliasEntity findByServerIdAndName(String serverId, String name);
}
