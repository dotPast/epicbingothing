package hi.dottt.epicbingothing.commands

import com.mojang.brigadier.context.CommandContext
import com.noxcrew.interfaces.drawable.Drawable.Companion.drawable
import com.noxcrew.interfaces.element.StaticElement
import com.noxcrew.interfaces.interfaces.buildChestInterface
import com.noxcrew.interfaces.utilities.forEachInGrid
import hi.dottt.epicbingothing.utility.CardTask
import io.papermc.paper.command.brigadier.CommandSourceStack
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType

class ShowCardCommand {
    fun execute(ctx: CommandContext<CommandSourceStack>): Int {
        val cardUI = buildChestInterface {
            rows = 6
            initialTitle = Component.text("Bingo Card")

            val player = ctx.source.sender as Player

            val rawCards = player.persistentDataContainer.get(NamespacedKey("bingo", "rows"), PersistentDataType.LIST.strings())
            var items: MutableList<List<CardTask>> = mutableListOf()

            for (row in rawCards) {
                val rowData = Json.decodeFromString<List<CardTask>>(row)

                items.add(rowData)
            }

            withTransform { pane, _ ->
                forEachInGrid(5, 5) { row, column ->
                    pane[row + 1, column + 2] = StaticElement(drawable(items[row][column].getItemDisplay()))
                }
            }
        }

        runBlocking { launch { cardUI.open(ctx.source.sender as Player) } }

        return 0
    }
}