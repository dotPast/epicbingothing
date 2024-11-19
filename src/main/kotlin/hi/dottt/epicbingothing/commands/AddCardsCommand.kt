package hi.dottt.epicbingothing.commands

import com.mojang.brigadier.context.CommandContext
import hi.dottt.epicbingothing.utility.CardTask
import io.papermc.paper.command.brigadier.CommandSourceStack
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType

class AddCardsCommand {
    fun execute(ctx: CommandContext<CommandSourceStack>): Int {
        if (ctx.source.sender !is Player) {
            ctx.source.sender.sendPlainMessage("Sender is not a player.")
            return 0
        }

        val player = ctx.source.sender as Player

        val cards = CardTask().generateCard()

        val serialized = mutableListOf<String>()

        for (row in cards) {
            serialized.add(Json.encodeToString(row))
        }

        player.persistentDataContainer.set(
            NamespacedKey("bingo", "rows"),
            PersistentDataType.LIST.strings(),
            serialized,
        )
        return 0
    }
}
