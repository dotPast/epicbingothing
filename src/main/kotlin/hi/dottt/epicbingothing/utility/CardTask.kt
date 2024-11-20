package hi.dottt.epicbingothing.utility

import io.papermc.paper.advancement.AdvancementDisplay
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack

@Serializable
class CardTask(
    var id: String = "default",
    var amount: Int = 1,
    var type: TYPE = TYPE.ITEM,
    var displayName: String = "",
    var description: String = "",
    var iconMaterial: Material = Material.DIAMOND,
    var completed: Boolean = false,
) {
    fun getItemDisplay(): ItemStack {
        var display = ItemStack(iconMaterial, amount)

        if (type == TYPE.ITEM) {
            val itemMaterial = Material.getMaterial(id.uppercase())

            if (itemMaterial != null) {
                display = ItemStack(itemMaterial, amount)
            }
        }

        val displayMeta = display.itemMeta

        val mm = MiniMessage.miniMessage()

        var deserializedDisplayName = mm.deserialize(displayName)
        var deserializedDescription = mm.deserialize(description)

        if (type == TYPE.ADVANCEMENTS) {
            val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

            display =
                if (advancement != null) {
                    advancement.display!!.icon()
                } else {
                    ItemStack(Material.EXPERIENCE_BOTTLE, 1)
                }
        }

        if (deserializedDisplayName == Component.text("")) {
            deserializedDisplayName =
                when (type) {
                    TYPE.ITEM -> {
                        Component.text("Get ").append(display.displayName())
                    }

                    TYPE.ADVANCEMENTS -> {
                        val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

                        if (advancement == null || id == "default") {
                            Component.text("Get an Unknown Advancement")
                        } else {
                            Component
                                .text("Get the ")
                                .append(advancement.displayName())
                                .append(Component.text(" Advancement"))
                        }
                    }

                    TYPE.ODDBALL -> {
                        Component.text("Complete a Missing Task")
                    }
                }
        }

        if (deserializedDescription == Component.text("")) {
            deserializedDescription =
                when (type) {
                    TYPE.ITEM -> {
                        if (amount == 1) {
                            Component.text("Get ").append(display.displayName())
                        } else {
                            Component.text("Get $amount ").append(display.displayName())
                        }
                    }

                    TYPE.ADVANCEMENTS -> {
                        val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

                        if (advancement == null || id == "default") {
                            Component.text(
                                "Complete an Unknown Advancement. Please check if the task ID is valid",
                            )
                        } else {
                            when (advancement.display!!.frame()) {
                                AdvancementDisplay.Frame.CHALLENGE ->
                                    Component
                                        .text("Complete the ")
                                        .append(advancement.displayName())
                                        .append(Component.text(" challenge"))

                                AdvancementDisplay.Frame.GOAL ->
                                    Component
                                        .text("Reach the ")
                                        .append(advancement.displayName())
                                        .append(Component.text(" goal"))

                                AdvancementDisplay.Frame.TASK ->
                                    Component
                                        .text("Get the ")
                                        .append(advancement.displayName())
                                        .append(Component.text(" advancement"))

                                else ->
                                    Component
                                        .text("Get the ")
                                        .append(advancement.displayName())
                                        .append(Component.text(" advancement"))
                            }
                        }
                    }

                    TYPE.ODDBALL ->
                        Component.text(
                            "Complete a Missing Task. Please check if the task ID is valid.",
                        )
                }
        }

        displayMeta.displayName(
            deserializedDisplayName
                .color(TextColor.color(this.type.color))
                .append(Component.text(" - ").color(TextColor.color(0xffffff)))
                .append(
                    Component
                        .text(
                            if (completed) {
                                "✅ Completed"
                            } else {
                                "❌ Not Completed"
                            },
                        ).color(
                            TextColor.color(
                                if (completed) {
                                    0x88ff4d
                                } else {
                                    0xff4d4d
                                },
                            ),
                        ),
                ),
        )

        display.setItemMeta(displayMeta)

        display.lore(
            mutableListOf(
                deserializedDescription.color(TextColor.color(0xffffff)),
                if (this.type == TYPE.ADVANCEMENTS) {
                    Component
                        .text("( ")
                        .append(
                            Bukkit
                                .getAdvancement(NamespacedKey.minecraft(id))!!
                                .display!!
                                .description(),
                        ).append(Component.text(" )"))
                } else {
                    Component.empty()
                },
                Component.text("Type: ").color(TextColor.color(0xffffff)).append {
                    Component
                        .text(this.type.deserializedDisplayName)
                        .color(TextColor.color(this.type.color))
                },
                Component.empty(),
                Component.text("ID: ${this.id}").color(TextColor.color(0x555555)),
            ),
        )

        return display
    }

    fun getAnnouncementDisplay(): Component {
        var display = ItemStack(iconMaterial, iconAmount)
        val displayMeta = display.itemMeta

        val mm = MiniMessage.miniMessage()

        var deserializedDisplayName = mm.deserialize(displayName)
        var deserializedDescription = mm.deserialize(description)

        if (type == TYPE.ADVANCEMENTS) {
            val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

            display =
                if (advancement != null) {
                    advancement.display!!.icon()
                } else {
                    ItemStack(Material.EXPERIENCE_BOTTLE, 1)
                }
        }

        if (deserializedDisplayName == Component.text("")) {
            deserializedDisplayName =
                when (type) {
                    TYPE.ITEM -> {
                        Component.text("Get ").append(display.displayName())
                    }

                    TYPE.ADVANCEMENTS -> {
                        val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

                        if (advancement == null || id == "default") {
                            Component.text("Get an Unknown Advancement")
                        } else {
                            Component
                                .text("Get the ")
                                .append(advancement.displayName())
                                .append(Component.text(" Advancement"))
                        }
                    }

                    TYPE.ODDBALL -> {
                        Component.text("Complete a Missing Task")
                    }
                }
        }

        if (deserializedDescription == Component.text("")) {
            deserializedDescription =
                when (type) {
                    TYPE.ITEM -> {
                        if (amount == 1) {
                            Component.text("Get ").append(display.displayName())
                        } else {
                            Component.text("Get $amount ").append(display.displayName())
                        }
                    }

                    TYPE.ADVANCEMENTS -> {
                        val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

                        if (advancement == null || id == "default") {
                            Component.text(
                                "Complete an Unknown Advancement. Please check if the task ID is valid",
                            )
                        } else {
                            when (advancement.display!!.frame()) {
                                AdvancementDisplay.Frame.CHALLENGE ->
                                    Component
                                        .text("Complete the ")
                                        .append(advancement.displayName())
                                        .append(Component.text(" challenge"))

                                AdvancementDisplay.Frame.GOAL ->
                                    Component
                                        .text("Reach the ")
                                        .append(advancement.displayName())
                                        .append(Component.text(" goal"))

                                AdvancementDisplay.Frame.TASK ->
                                    Component
                                        .text("Get the ")
                                        .append(advancement.displayName())
                                        .append(Component.text(" advancement"))

                                else ->
                                    Component
                                        .text("Get the ")
                                        .append(advancement.displayName())
                                        .append(Component.text(" advancement"))
                            }
                        }
                    }

                    TYPE.ODDBALL ->
                        Component.text(
                            "Complete a Missing Task. Please check if the task ID is valid.",
                        )
                }
        }

        displayMeta.displayName(deserializedDisplayName.color(TextColor.color(this.type.color)))

        display.setItemMeta(displayMeta)

        display.lore(
            mutableListOf(
                deserializedDescription.color(TextColor.color(0xffffff)),
                Component.empty(),
                Component.text("Type: ").color(TextColor.color(0xffffff)).append {
                    Component
                        .text(this.type.deserializedDisplayName)
                        .color(TextColor.color(this.type.color))
                },
                Component.empty(),
                Component.text("ID: ${this.id}").color(TextColor.color(0x555555)),
            ),
        )

        return display.displayName()
    }

    enum class TYPE(
        val deserializedDisplayName: String,
        val color: TextColor,
    ) {
        ITEM("Item", TextColor.color(0xffbf00)),
        ADVANCEMENTS("Advancements", TextColor.color(0x88ff4d)),
        ODDBALL("Oddball", TextColor.color(0xc067ff)),
    }
}
