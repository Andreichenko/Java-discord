package bot.repository;

import bot.entities.GuildHolderEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EntityGuildHolderRepository extends MongoRepository<GuildHolderEntity, String>{

    @NotNull Optional<GuildHolderEntity> findByGuildId(@NotNull String guildId);
}
