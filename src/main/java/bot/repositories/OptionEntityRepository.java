package bot.repositories;

import bot.entities.OptionEntity;
import org.springframework.data.repository.CrudRepository;

public interface OptionEntityRepository extends CrudRepository<OptionEntity, String> {
    /**
     * Find all options values
     * @param serverId
     * @param name
     * @return
     */
    OptionEntity findByServerIdAndName(String serverId, String name);

}
