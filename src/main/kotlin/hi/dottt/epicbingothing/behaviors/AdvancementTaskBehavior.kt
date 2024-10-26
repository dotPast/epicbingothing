package hi.dottt.epicbingothing.behaviors

import dev.asodesu.origami.engine.player.OnlinePlayerBehaviour
import dev.asodesu.origami.engine.player.PlayerBehaviourContainer
import dev.asodesu.origami.engine.wiring.annotations.Subscribe
import hi.dottt.epicbingothing.utility.CardTask
import io.papermc.paper.advancement.AdvancementDisplay
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.persistence.PersistentDataType

class AdvancementTaskBehavior(container: PlayerBehaviourContainer): OnlinePlayerBehaviour(container) {
    @Subscribe
    fun getAdvancement(event: PlayerAdvancementDoneEvent) {
        val player = event.player
        val advancement = event.advancement

        val tasksPDC = player.persistentDataContainer.get(NamespacedKey("bingo", "rows"), PersistentDataType.LIST.strings())!!

        val tasks = mutableListOf<String>()

        for (row in tasksPDC) {
            val rowData = Json.decodeFromString<List<CardTask>>(row)

            for (task in rowData) {
                if (task.type == CardTask.TYPE.ADVANCEMENTS && advancement.key.key == task.id) {
                    val name = advancement.displayName()

                    when (advancement.display!!.frame()) {
                        AdvancementDisplay.Frame.CHALLENGE -> {
                            Bukkit.broadcast(Component.text("${player.name} marked the ").append(name).append(Component.text(" challenge space!")))
                            task.completed = true
                        }
                        AdvancementDisplay.Frame.GOAL -> {
                            Bukkit.broadcast(Component.text("${player.name} marked the ").append(name).append(Component.text(" goal space!")))
                            task.completed = true
                        }
                        else -> {
                            Bukkit.broadcast(Component.text("${player.name} marked the ").append(name).append(Component.text(" advancement space!")))
                            task.completed = true
                        }
                    }
                }
            }

            tasks.add(Json.encodeToString(rowData))
        }

        player.persistentDataContainer.set(NamespacedKey("bingo", "rows"), PersistentDataType.LIST.strings(), tasks)
    }
}