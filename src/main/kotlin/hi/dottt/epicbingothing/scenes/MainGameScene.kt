package hi.dottt.epicbingothing.scenes

import dev.asodesu.origami.engine.add
import dev.asodesu.origami.engine.player.container
import dev.asodesu.origami.engine.scene.OnlineGlobalScene
import hi.dottt.epicbingothing.behaviors.AdvancementTaskBehavior
import hi.dottt.epicbingothing.behaviors.ItemTaskBehavior
import org.bukkit.entity.Player

class MainGameScene : OnlineGlobalScene() {
	override fun setupComponents(player: Player) {
		player.container.add<AdvancementTaskBehavior>()
		player.container.add<ItemTaskBehavior>()
	}

	override fun setupComponents() {}
}