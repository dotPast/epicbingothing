package hi.dottt.epicbingothing.utility

import io.papermc.paper.advancement.AdvancementDisplay
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack

@Serializable
class CardTask(
    val id: String = "default",
    val type: TYPE = TYPE.ITEM,
    var displayName: String = "",
    var description: String = "",
    var iconMaterial: Material = Material.DIAMOND,
    var iconAmount: Int = 1,
    val difficulty: DIFFICULTY = DIFFICULTY.EASY,
    var completed: Boolean = false
) {
    fun getItemDisplay(): ItemStack {
        var display = ItemStack(iconMaterial, iconAmount)
        val displayMeta = display.itemMeta

        if (type == TYPE.ADVANCEMENTS) {
            val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

            display = if (advancement != null) {
                advancement.display!!.icon()
            } else {
                ItemStack(Material.EXPERIENCE_BOTTLE, 1)
            }
        }

        if (displayName.isEmpty()) {
            displayName = when (type) {
                TYPE.ITEM -> {
                    val plainTextName = PlainTextComponentSerializer.plainText().serialize(display.displayName())

                    if (iconMaterial != Material.getMaterial(id)) {
                        if (iconAmount == 1) {
                            when (plainTextName[2]) {
                                'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> "Get a ${
                                    plainTextName.subSequence(
                                        1,
                                        plainTextName.length - 1
                                    )
                                }"

                                else -> "Get an ${plainTextName.subSequence(1, plainTextName.length - 1)}"
                            }
                        } else {
                            "Get ${plainTextName.subSequence(1, plainTextName.length - 1)}s"
                        }
                    } else {
                        "Get an Unknown Item"
                    }
                }

                TYPE.ADVANCEMENTS -> {
                    val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

                    if (advancement == null || id == "default") {
                        "Get an Unknown Advancement"
                    } else {
                        val plainTextName =
                            PlainTextComponentSerializer.plainText().serialize(advancement.displayName())
                        "Get the \"${plainTextName.subSequence(1, plainTextName.length - 1)}\" Advancement"
                    }
                }

                TYPE.ODDBALL -> {
                    "Complete a Missing Task"
                }
            }
        }

        if (description.isEmpty()) {
            description = when (type) {
                TYPE.ITEM -> {
                    val plainTextName = PlainTextComponentSerializer.plainText().serialize(display.displayName())

                    if (iconAmount == 1) {
                        "Get a single ${plainTextName.subSequence(1, plainTextName.length - 1)}"
                    } else {
                        "Get ${iconAmount} ${plainTextName.subSequence(1, plainTextName.length - 1)}s"
                    }
                }

                TYPE.ADVANCEMENTS -> {
                    val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

                    if (advancement == null || id == "default") {
                        "Complete an Unknown Advancement. Please check if the task ID is valid"
                    } else {
                        val plainTextName =
                            PlainTextComponentSerializer.plainText().serialize(advancement.displayName())
                        when (advancement.display!!.frame()) {
                            AdvancementDisplay.Frame.CHALLENGE -> "Complete the \"${
                                plainTextName.subSequence(
                                    1,
                                    plainTextName.length - 1
                                )
                            }\" challenge"

                            AdvancementDisplay.Frame.GOAL -> "Reach the \"${
                                plainTextName.subSequence(
                                    1,
                                    plainTextName.length - 1
                                )
                            }\" goal"

                            AdvancementDisplay.Frame.TASK -> "Complete the \"${
                                plainTextName.subSequence(
                                    1,
                                    plainTextName.length - 1
                                )
                            }\" advancement"

                            else -> "Complete the \"${
                                plainTextName.subSequence(
                                    1,
                                    plainTextName.length - 1
                                )
                            }\" advancement"
                        }
                    }
                }

                TYPE.ODDBALL -> "Complete a Missing Task. Please check if the task ID is valid."
            }
        }

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
                        .color(
                            TextColor.color(
                                if (completed) {
                                    0x88ff4d
                                } else {
                                    0xff4d4d
                                }
                            )
                        )
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

    fun getAnnouncementDisplay(): Component {
        var display = ItemStack(iconMaterial, iconAmount)
        val displayMeta = display.itemMeta

        if (type == TYPE.ADVANCEMENTS) {
            val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

            display = if (advancement != null) {
                advancement.display!!.icon()
            } else {
                ItemStack(Material.EXPERIENCE_BOTTLE, 1)
            }
        }

        if (displayName.isEmpty()) {
            displayName = when (type) {
                TYPE.ITEM -> {
                    val plainTextName = PlainTextComponentSerializer.plainText().serialize(display.displayName())

                    if (iconMaterial != Material.getMaterial(id)) {
                        if (iconAmount == 1) {
                            when (plainTextName[2]) {
                                'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> "Get a ${
                                    plainTextName.subSequence(
                                        1,
                                        plainTextName.length - 1
                                    )
                                }"

                                else -> "Get an ${plainTextName.subSequence(1, plainTextName.length - 1)}"
                            }
                        } else {
                            "Get ${plainTextName.subSequence(1, plainTextName.length - 1)}s"
                        }
                    } else {
                        "Get an Unknown Item"
                    }
                }

                TYPE.ADVANCEMENTS -> {
                    val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

                    if (advancement == null || id == "default") {
                        "Get an Unknown Advancement"
                    } else {
                        val plainTextName =
                            PlainTextComponentSerializer.plainText().serialize(advancement.displayName())
                        "Get the \"${plainTextName.subSequence(1, plainTextName.length - 1)}\" Advancement"
                    }
                }

                TYPE.ODDBALL -> {
                    "Complete a Missing Task"
                }
            }
        }

        if (description.isEmpty()) {
            description = when (type) {
                TYPE.ITEM -> {
                    val plainTextName = PlainTextComponentSerializer.plainText().serialize(display.displayName())

                    if (iconAmount == 1) {
                        "Get a single ${plainTextName.subSequence(1, plainTextName.length - 1)}"
                    } else {
                        "Get ${iconAmount} ${plainTextName.subSequence(1, plainTextName.length - 1)}s"
                    }
                }

                TYPE.ADVANCEMENTS -> {
                    val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

                    if (advancement == null || id == "default") {
                        "Complete an Unknown Advancement. Please check if the task ID is valid"
                    } else {
                        val plainTextName =
                            PlainTextComponentSerializer.plainText().serialize(advancement.displayName())
                        when (advancement.display!!.frame()) {
                            AdvancementDisplay.Frame.CHALLENGE -> "Complete the \"${
                                plainTextName.subSequence(
                                    1,
                                    plainTextName.length - 1
                                )
                            }\" challenge"

                            AdvancementDisplay.Frame.GOAL -> "Reach the \"${
                                plainTextName.subSequence(
                                    1,
                                    plainTextName.length - 1
                                )
                            }\" goal"

                            AdvancementDisplay.Frame.TASK -> "Complete the \"${
                                plainTextName.subSequence(
                                    1,
                                    plainTextName.length - 1
                                )
                            }\" advancement"

                            else -> "Complete the \"${
                                plainTextName.subSequence(
                                    1,
                                    plainTextName.length - 1
                                )
                            }\" advancement"
                        }
                    }
                }

                TYPE.ODDBALL -> "Complete a Missing Task. Please check if the task ID is valid."
            }
        }

        displayMeta.displayName(
            Component.text(displayName)
                .color(TextColor.color(this.difficulty.color))
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

        return display.displayName()
    }

    enum class DIFFICULTY(val displayName: String, val color: TextColor) {
        EASY("Easy", TextColor.color(0x88ff4d)),
        NORMAL("Normal", TextColor.color(0xffbf00)),
        HARD("Hard", TextColor.color(0xff4040)),
        GOOD_LUCK_GETTING_THIS_ONE("Good Luck Getting This One", TextColor.color(0xc067ff))
    }

    enum class TYPE(val displayName: String, val color: TextColor) {
        ITEM("Item", TextColor.color(0xffbf00)),
        ADVANCEMENTS("Advancements", TextColor.color(0x88ff4d)),
        ODDBALL("Oddball", TextColor.color(0xc067ff)),
    }

    fun serialize() {

    }
}

