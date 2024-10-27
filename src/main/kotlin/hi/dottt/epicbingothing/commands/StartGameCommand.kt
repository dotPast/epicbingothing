package hi.dottt.epicbingothing.commands

import com.mojang.brigadier.context.CommandContext
import dev.asodesu.origami.engine.scene.PlayerScene
import dev.asodesu.origami.engine.scene.Scenes
import hi.dottt.epicbingothing.scenes.MainGameScene
import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer

class StartGameCommand {
	fun execute(ctx: CommandContext<CommandSourceStack>): Int {
		Scenes.register(MainGameScene("game"))

		val scene = Scenes.map["game"] as PlayerScene<OfflinePlayer>

		for (player in Bukkit.getOnlinePlayers()) {
			scene.addPlayer(player)


		}

		return 0
	}
}