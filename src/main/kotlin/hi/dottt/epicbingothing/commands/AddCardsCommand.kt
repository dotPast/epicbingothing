package hi.dottt.epicbingothing.commands

import com.mojang.brigadier.context.CommandContext
import hi.dottt.epicbingothing.utility.CardTask
import io.papermc.paper.command.brigadier.CommandSourceStack
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bukkit.Material
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

		val cards = listOf(
			listOf(
				CardTask(
					"diamond",
					CardTask.TYPE.ITEM,
					iconMaterial = Material.DIAMOND,
					iconAmount = 1,
					difficulty = CardTask.DIFFICULTY.EASY,

					),
				CardTask(
					"test",
					CardTask.TYPE.ADVANCEMENTS,
					iconMaterial = Material.EXPERIENCE_BOTTLE,
					iconAmount = 1,
					difficulty = CardTask.DIFFICULTY.NORMAL,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					iconMaterial = Material.DIAMOND,
					iconAmount = 1,
					difficulty = CardTask.DIFFICULTY.HARD,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					"Test Task",
					"Test Task Description",
					Material.DIAMOND,
					1,
					CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					"Test Task",
					"Test Task Description",
					Material.DIAMOND,
					1,
					CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
					false
				)
			),
			listOf(
				CardTask(
					"gold_ingot",
					CardTask.TYPE.ITEM,
					iconMaterial = Material.GOLD_INGOT,
					iconAmount = 8,
					difficulty = CardTask.DIFFICULTY.EASY,

					),
				CardTask(
					"adventure/sleep_in_bed",
					CardTask.TYPE.ADVANCEMENTS,
					iconMaterial = Material.EXPERIENCE_BOTTLE,
					iconAmount = 1,
					difficulty = CardTask.DIFFICULTY.NORMAL,
					completed = false
				),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					iconMaterial = Material.GOLD_INGOT,
					iconAmount = 8,
					difficulty = CardTask.DIFFICULTY.HARD,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					"Test Task",
					"Test Task Description",
					iconMaterial = Material.GOLD_INGOT,
					iconAmount = 8,
					CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					"Test Task",
					"Test Task Description",
					Material.GOLD_INGOT,
					8,
					CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
					false
				)
			),
			listOf(
				CardTask(
					"iron_ingot",
					CardTask.TYPE.ITEM,
					iconMaterial = Material.IRON_INGOT,
					iconAmount = 16,
					difficulty = CardTask.DIFFICULTY.EASY,

					),
				CardTask(
					"adventure/overoverkill",
					CardTask.TYPE.ADVANCEMENTS,
					iconMaterial = Material.EXPERIENCE_BOTTLE,
					iconAmount = 1,
					difficulty = CardTask.DIFFICULTY.NORMAL,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					iconMaterial = Material.IRON_INGOT,
					iconAmount = 16,
					difficulty = CardTask.DIFFICULTY.HARD,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					"Test Task",
					"Test Task Description",
					Material.IRON_INGOT,
					16,
					CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					"Test Task",
					"Test Task Description",
					Material.IRON_INGOT,
					16,
					CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
					false
				)
			),
			listOf(
				CardTask(
					"copper_ingot",
					CardTask.TYPE.ITEM,
					iconMaterial = Material.COPPER_INGOT,
					iconAmount = 32,
					difficulty = CardTask.DIFFICULTY.EASY,

					),
				CardTask(
					"nether/create_full_beacon",
					CardTask.TYPE.ADVANCEMENTS,
					iconMaterial = Material.EXPERIENCE_BOTTLE,
					iconAmount = 1,
					difficulty = CardTask.DIFFICULTY.NORMAL,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					iconMaterial = Material.COPPER_INGOT,
					iconAmount = 32,
					difficulty = CardTask.DIFFICULTY.HARD,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					"Test Task",
					"Test Task Description",
					Material.COPPER_INGOT,
					32,
					CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
				),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					"Test Task",
					"Test Task Description",
					Material.COPPER_INGOT,
					32,
					CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
					false
				)
			),
			listOf(
				CardTask(
					"amethyst_shard",
					CardTask.TYPE.ITEM,
					iconMaterial = Material.AMETHYST_SHARD,
					iconAmount = 64,
					difficulty = CardTask.DIFFICULTY.EASY,

					),
				CardTask(
					"adventure/sleep_in_bed",
					CardTask.TYPE.ADVANCEMENTS,
					iconMaterial = Material.EXPERIENCE_BOTTLE,
					iconAmount = 32,
					difficulty = CardTask.DIFFICULTY.NORMAL
				),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					iconMaterial = Material.AMETHYST_SHARD,
					iconAmount = 64,
					difficulty = CardTask.DIFFICULTY.HARD,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					"Test Task",
					"Test Task Description",
					Material.AMETHYST_SHARD,
					64,
					CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,

					),
				CardTask(
					"test",
					CardTask.TYPE.ODDBALL,
					"Test Task",
					"Test Task Description",
					Material.AMETHYST_SHARD,
					64,
					CardTask.DIFFICULTY.GOOD_LUCK_GETTING_THIS_ONE,
					false
				)
			)
		)


		val serialized = mutableListOf<String>()

		for (row in cards) {
			serialized.add(Json.encodeToString(row))
		}

		player.persistentDataContainer.set(
			NamespacedKey("bingo", "rows"),
			PersistentDataType.LIST.strings(),
			serialized
		)
		return 0
	}
}