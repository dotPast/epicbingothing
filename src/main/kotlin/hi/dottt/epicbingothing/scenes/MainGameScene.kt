package hi.dottt.epicbingothing.scenes

import dev.asodesu.origami.engine.add
import dev.asodesu.origami.engine.player.container
import dev.asodesu.origami.engine.scene.OfflinePlayerScene
import hi.dottt.epicbingothing.behaviors.AdvancementTaskBehavior
import hi.dottt.epicbingothing.behaviors.ItemTaskBehavior
import org.bukkit.OfflinePlayer

class MainGameScene(
    id: String,
) : OfflinePlayerScene(id) {
    override fun setupComponents(player: OfflinePlayer) {
        player.container.add<AdvancementTaskBehavior>()
        player.container.add<ItemTaskBehavior>()
    }

    override fun setupComponents() {}
}
