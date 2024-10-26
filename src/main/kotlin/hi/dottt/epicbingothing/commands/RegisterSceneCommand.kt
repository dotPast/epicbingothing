package hi.dottt.epicbingothing.commands

import com.mojang.brigadier.context.CommandContext
import dev.asodesu.origami.engine.scene.Scenes
import hi.dottt.epicbingothing.scenes.MainGameScene
import io.papermc.paper.command.brigadier.CommandSourceStack

class RegisterSceneCommand {
	fun execute(ctx: CommandContext<CommandSourceStack>): Int {
		Scenes.register(MainGameScene())

		return 0
	}
}