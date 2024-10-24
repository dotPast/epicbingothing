package hi.dottt.epicbingothing.utility

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class CardTask(
    val id: String,
    val type: TYPE,
    val displayName: String = "Default Name",
    val description: String = "Default Description",
    val icon: ItemStack = ItemStack(Material.DIAMOND),
    val difficulty: DIFFICULTY = DIFFICULTY.EASY,
    val completed: Boolean = false
) {
    fun getItemDisplay(): ItemStack {
        val display = ItemStack(icon)
        val displayMeta = display.itemMeta

        displayMeta.displayName(
            Component.text(displayName)
                .color(TextColor.color(this.difficulty.color))
                .append(
                    Component.text(" - ").color(TextColor.color(0xffffff))
                )
                .append(
                    Component.text(
                        if (completed) {
                            "✅ Completed"
                        } else {
                            "❌ Not Completed"
                        }
                    )
                        .color(TextColor.color(
                            if (completed) {
                                0x88ff4d
                            } else {
                                0xff4d4d
                            }
                        ))
                )
        )

        display.setItemMeta(displayMeta)

        display.lore(
            mutableListOf(
                Component.text(description).color(TextColor.color(0xffffff)),
                Component.empty(),
                Component.text("Type: ").color(TextColor.color(0xffffff))
                    .append {
                        Component.text(this.type.displayName)
                            .color(TextColor.color(this.type.color))
                    },
                Component.text("Difficulty: ").color(TextColor.color(0xffffff))
                    .append {
                        Component.text(this.difficulty.displayName)
                            .color(TextColor.color(this.difficulty.color))
                    },
                Component.empty(),
                Component.text("ID: ${this.id}").color(TextColor.color(0x555555))
            )
        )

        return display
    }

    enum class DIFFICULTY(val displayName: String, val color: TextColor) {
        EASY("Easy", TextColor.color(0x88ff4d)),
        NORMAL("Normal", TextColor.color(0xffbf00)),
        HARD("Hard", TextColor.color(0xff4040)),
        GOOD_LUCK_GETTING_THIS_ONE("Good Luck Getting This One", TextColor.color(0xc067ff))
    }

    enum class TYPE(val displayName: String, val color: TextColor) {
        ITEM("Item", TextColor.color(0xffbf00)),
        STATISTICS("Statistics", TextColor.color(0x00acff)),
        ACHIEVEMENTS("Achievements", TextColor.color(0x88ff4d)),
        ODDBALL("Oddball", TextColor.color(0xc067ff)),
    }
}

