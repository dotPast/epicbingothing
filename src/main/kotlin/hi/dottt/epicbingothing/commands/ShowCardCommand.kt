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
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ShowCardCommand {
    fun execute(ctx: CommandContext<CommandSourceStack>): Int {
        val cardUI = buildChestInterface {
            rows = 6
            initialTitle = Component.text("Bingo Card")

            val items = listOf(
                listOf(
                    CardTask(
                        "test",
                        CardTask.TYPE.ITEM,
                        description = "Test Task Description",
                        icon = ItemStack(Material.DIAMOND, 1),
                        difficulty = CardTask.DIFFICULTY.EASY,
                        completed = true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ACHIEVEMENTS,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.DIAMOND, 1),
                        CardTask.DIFFICULTY.NORMAL,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.STATISTICS,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.DIAMOND, 1),
                        CardTask.DIFFICULTY.HARD,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ODDBALL,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.DIAMOND, 1),
                        CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ODDBALL,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.DIAMOND, 1),
                        CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
                        false
                    )
                ),
                listOf(
                    CardTask(
                        "test",
                        CardTask.TYPE.ITEM,
                        description = "Test Task Description",
                        icon = ItemStack(Material.GOLD_INGOT, 8),
                        difficulty = CardTask.DIFFICULTY.EASY,
                        completed = true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ACHIEVEMENTS,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.GOLD_INGOT, 8),
                        CardTask.DIFFICULTY.NORMAL,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.STATISTICS,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.GOLD_INGOT, 8),
                        CardTask.DIFFICULTY.HARD,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ODDBALL,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.GOLD_INGOT, 8),
                        CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ODDBALL,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.GOLD_INGOT, 8),
                        CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
                        false
                    )
                ),
                listOf(
                    CardTask(
                        "test",
                        CardTask.TYPE.ITEM,
                        description = "Test Task Description",
                        icon = ItemStack(Material.IRON_INGOT, 16),
                        difficulty = CardTask.DIFFICULTY.EASY,
                        completed = true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ACHIEVEMENTS,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.IRON_INGOT, 16),
                        CardTask.DIFFICULTY.NORMAL,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.STATISTICS,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.IRON_INGOT, 16),
                        CardTask.DIFFICULTY.HARD,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ODDBALL,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.IRON_INGOT, 16),
                        CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ODDBALL,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.IRON_INGOT, 16),
                        CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
                        false
                    )
                ),
                listOf(
                    CardTask(
                        "test",
                        CardTask.TYPE.ITEM,
                        description = "Test Task Description",
                        icon = ItemStack(Material.COPPER_INGOT, 32),
                        difficulty = CardTask.DIFFICULTY.EASY,
                        completed = true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ACHIEVEMENTS,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.COPPER_INGOT, 32),
                        CardTask.DIFFICULTY.NORMAL,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.STATISTICS,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.COPPER_INGOT, 32),
                        CardTask.DIFFICULTY.HARD,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ODDBALL,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.COPPER_INGOT, 32),
                        CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ODDBALL,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.COPPER_INGOT, 32),
                        CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
                        false
                    )
                ),
                listOf(
                    CardTask(
                        "test",
                        CardTask.TYPE.ITEM,
                        description = "Test Task Description",
                        icon = ItemStack(Material.AMETHYST_SHARD, 64),
                        difficulty = CardTask.DIFFICULTY.EASY,
                        completed = true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ACHIEVEMENTS,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.AMETHYST_SHARD, 64),
                        CardTask.DIFFICULTY.NORMAL,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.STATISTICS,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.AMETHYST_SHARD, 64),
                        CardTask.DIFFICULTY.HARD,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ODDBALL,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.AMETHYST_SHARD, 64),
                        CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
                        true
                    ),
                    CardTask(
                        "test",
                        CardTask.TYPE.ODDBALL,
                        "Test Task",
                        "Test Task Description",
                        ItemStack(Material.AMETHYST_SHARD, 64),
                        CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
                        false
                    )
                )
            )

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