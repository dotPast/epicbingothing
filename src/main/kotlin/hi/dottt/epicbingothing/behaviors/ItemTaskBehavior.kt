package hi.dottt.epicbingothing.behaviors

import dev.asodesu.origami.engine.player.OnlinePlayerBehaviour
import dev.asodesu.origami.engine.player.PlayerBehaviourContainer
import dev.asodesu.origami.engine.wiring.annotations.Subscribe
import hi.dottt.epicbingothing.utility.CardTask
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class ItemTaskBehavior(
    container: PlayerBehaviourContainer,
) : OnlinePlayerBehaviour(container) {
    fun checkItems(
        player: Player,
        item: ItemStack,
    ) {
        val tasksPDC =
            player.persistentDataContainer.get(NamespacedKey("bingo", "rows"), PersistentDataType.LIST.strings())!!

        val tasks = mutableListOf<String>()

        for (row in tasksPDC) {
            val rowData = Json.decodeFromString<List<CardTask>>(row)

            for (task in rowData) {
                if (task.type == CardTask.TYPE.ITEM && !task.completed) {
                    if (player.inventory.contains(
                            task.iconMaterial,
                            task.iconAmount,
                        ) ||
                        (item.type == task.iconMaterial && item.amount >= task.iconAmount)
                    ) {
                        Bukkit.broadcast(
                            Component
                                .text("${player.name} marked the ")
                                .append(task.getAnnouncementDisplay())
                                .append(Component.text(" space!")),
                        )
                        task.completed = true
                    }
                }
            }

            tasks.add(Json.encodeToString(rowData))
        }

        player.persistentDataContainer.set(NamespacedKey("bingo", "rows"), PersistentDataType.LIST.strings(), tasks)
    }

    @Subscribe
    fun slotChange(event: PlayerInventorySlotChangeEvent) {
        checkItems(event.player, event.newItemStack)
    }
}
