package hi.dottt.epicbingothing.utility

import io.papermc.paper.advancement.AdvancementDisplay
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.advancement.Advancement
import org.bukkit.inventory.ItemStack

@Serializable
class CardTask(
	var id: String = "default",
	var type: TYPE = TYPE.ITEM,
	var displayName: Component = Component.text(""),
	var description: Component = Component.text(""),
	var iconMaterial: Material = Material.DIAMOND,
	var iconAmount: Int = 1,
	var completed: Boolean = false
) {
	fun getItemDisplay(): ItemStack {
		var display = ItemStack(iconMaterial, iconAmount)
		val displayMeta = display.itemMeta

		if (type == TYPE.ADVANCEMENTS) {
			val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

			display = if (advancement != null) {
				advancement.display !!.icon()
			}
			else {
				ItemStack(Material.EXPERIENCE_BOTTLE, 1)
			}
		}

		if (displayName == Component.text("")) {
			displayName = when (type) {
				TYPE.ITEM -> {
					Component.text("Get ").append(display.displayName())
				}

				TYPE.ADVANCEMENTS -> {
					val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

					if (advancement == null || id == "default") {
						Component.text("Get an Unknown Advancement")
					}
					else {
						Component.text("Get the ").append(advancement.displayName()).append(Component.text(" Advancement"))
					}
				}

				TYPE.ODDBALL -> {
					Component.text("Complete a Missing Task")
				}
			}
		}

		if (description == Component.text("")) {
			description = when (type) {
				TYPE.ITEM -> {
					if (iconAmount == 1) {
						Component.text("Get ").append(display.displayName())
					}
					else {
						Component.text("Get $iconAmount ").append(display.displayName())
					}
				}

				TYPE.ADVANCEMENTS -> {
					val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

					if (advancement == null || id == "default") {
						Component.text("Complete an Unknown Advancement. Please check if the task ID is valid")
					}
					else {
						when (advancement.display !!.frame()) {
							AdvancementDisplay.Frame.CHALLENGE -> Component.text("Complete the ").append(advancement.displayName()).append(Component.text(" challenge"))

							AdvancementDisplay.Frame.GOAL -> Component.text("Reach the ").append(advancement.displayName()).append(Component.text(" goal"))

							AdvancementDisplay.Frame.TASK -> Component.text("Get the ").append(advancement.displayName()).append(Component.text(" advancement"))

							else -> Component.text("Get the ").append(advancement.displayName()).append(Component.text(" advancement"))
						}
					}
				}

				TYPE.ODDBALL -> Component.text("Complete a Missing Task. Please check if the task ID is valid.")
			}
		}

		displayMeta.displayName(
			displayName
				.color(TextColor.color(this.type.color))
				.append(
					Component.text(" - ").color(TextColor.color(0xffffff))
				)
				.append(
					Component.text(
						if (completed) {
							"✅ Completed"
						}
						else {
							"❌ Not Completed"
						}
					)
						.color(
							TextColor.color(
								if (completed) {
									0x88ff4d
								}
								else {
									0xff4d4d
								}
							)
						)
				)
		)

		display.setItemMeta(displayMeta)

		display.lore(
			mutableListOf(
				description.color(TextColor.color(0xffffff)),
				if (this.type == TYPE.ADVANCEMENTS) {
					Component.text("( ").append(Bukkit.getAdvancement(NamespacedKey.minecraft(id))!!.display!!.description()).append(Component.text(" )"))
				} else {
					Component.empty()
				},
				Component.text("Type: ").color(TextColor.color(0xffffff))
					.append {
						Component.text(this.type.displayName)
							.color(TextColor.color(this.type.color))
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
				advancement.display !!.icon()
			}
			else {
				ItemStack(Material.EXPERIENCE_BOTTLE, 1)
			}
		}

		if (displayName == Component.text("")) {
			displayName = when (type) {
				TYPE.ITEM -> {
					Component.text("Get ").append(display.displayName())
				}

				TYPE.ADVANCEMENTS -> {
					val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

					if (advancement == null || id == "default") {
						Component.text("Get an Unknown Advancement")
					}
					else {
						Component.text("Get the ").append(advancement.displayName()).append(Component.text(" Advancement"))
					}
				}

				TYPE.ODDBALL -> {
					Component.text("Complete a Missing Task")
				}
			}
		}

		if (description == Component.text("")) {
			description = when (type) {
				TYPE.ITEM -> {
					if (iconAmount == 1) {
						Component.text("Get ").append(display.displayName())
					}
					else {
						Component.text("Get $iconAmount ").append(display.displayName())
					}
				}

				TYPE.ADVANCEMENTS -> {
					val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(id))

					if (advancement == null || id == "default") {
						Component.text("Complete an Unknown Advancement. Please check if the task ID is valid")
					}
					else {
						when (advancement.display !!.frame()) {
							AdvancementDisplay.Frame.CHALLENGE -> Component.text("Complete the ").append(advancement.displayName()).append(Component.text(" challenge"))

							AdvancementDisplay.Frame.GOAL -> Component.text("Reach the ").append(advancement.displayName()).append(Component.text(" goal"))

							AdvancementDisplay.Frame.TASK -> Component.text("Get the ").append(advancement.displayName()).append(Component.text(" advancement"))

							else -> Component.text("Get the ").append(advancement.displayName()).append(Component.text(" advancement"))
						}
					}
				}

				TYPE.ODDBALL -> Component.text("Complete a Missing Task. Please check if the task ID is valid.")
			}
		}

		displayMeta.displayName(
			displayName.color(TextColor.color(this.type.color))
		)

		display.setItemMeta(displayMeta)

		display.lore(
			mutableListOf(
				description.color(TextColor.color(0xffffff)),
				Component.empty(),
				Component.text("Type: ").color(TextColor.color(0xffffff))
					.append {
						Component.text(this.type.displayName)
							.color(TextColor.color(this.type.color))
					},
				Component.empty(),
				Component.text("ID: ${this.id}").color(TextColor.color(0x555555))
			)
		)

		return display.displayName()
	}

	fun generateCard(): MutableList<MutableList<CardTask>> {
		val card =
			mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf<CardTask>())

		for (row in 0..4) {
			for (column in 0..4) {
				val task = CardTask()
				val cardType = listOf(TYPE.ITEM, TYPE.ITEM, TYPE.ADVANCEMENTS).random()
				task.type = cardType

				when (cardType) {
					TYPE.ITEM -> {
						val stackSize = listOf(1, 8, 16, 32, 64).random()
						var itemMaterial: Material? = null

						val unobtainableItems = listOf(
							Material.BEDROCK,
							Material.BUDDING_AMETHYST,
							Material.PETRIFIED_OAK_SLAB,
							Material.CHORUS_PLANT,
							Material.SPAWNER,
							Material.FARMLAND,
							Material.INFESTED_STONE,
							Material.INFESTED_COBBLESTONE,
							Material.INFESTED_STONE_BRICKS,
							Material.INFESTED_MOSSY_STONE_BRICKS,
							Material.INFESTED_CRACKED_STONE_BRICKS,
							Material.INFESTED_CHISELED_STONE_BRICKS,
							Material.INFESTED_DEEPSLATE,
							Material.REINFORCED_DEEPSLATE,
							Material.END_PORTAL_FRAME,
							Material.COMMAND_BLOCK,
							Material.BARRIER,
							Material.LIGHT,
							Material.DIRT_PATH,
							Material.REPEATING_COMMAND_BLOCK,
							Material.CHAIN_COMMAND_BLOCK,
							Material.STRUCTURE_VOID,
							Material.STRUCTURE_BLOCK,
							Material.JIGSAW,
							Material.BUNDLE,
							Material.ALLAY_SPAWN_EGG,
							Material.AXOLOTL_SPAWN_EGG,
							Material.BAT_SPAWN_EGG,
							Material.BEE_SPAWN_EGG,
							Material.BLAZE_SPAWN_EGG,
							Material.CAT_SPAWN_EGG,
							Material.CAVE_SPIDER_SPAWN_EGG,
							Material.CHICKEN_SPAWN_EGG,
							Material.COD_SPAWN_EGG,
							Material.COW_SPAWN_EGG,
							Material.CREEPER_SPAWN_EGG,
							Material.DOLPHIN_SPAWN_EGG,
							Material.DONKEY_SPAWN_EGG,
							Material.DROWNED_SPAWN_EGG,
							Material.ELDER_GUARDIAN_SPAWN_EGG,
							Material.ENDERMAN_SPAWN_EGG,
							Material.ENDERMITE_SPAWN_EGG,
							Material.EVOKER_SPAWN_EGG,
							Material.FOX_SPAWN_EGG,
							Material.FROG_SPAWN_EGG,
							Material.GHAST_SPAWN_EGG,
							Material.GLOW_SQUID_SPAWN_EGG,
							Material.GOAT_SPAWN_EGG,
							Material.GUARDIAN_SPAWN_EGG,
							Material.HOGLIN_SPAWN_EGG,
							Material.HORSE_SPAWN_EGG,
							Material.HUSK_SPAWN_EGG,
							Material.LLAMA_SPAWN_EGG,
							Material.MAGMA_CUBE_SPAWN_EGG,
							Material.MOOSHROOM_SPAWN_EGG,
							Material.MULE_SPAWN_EGG,
							Material.OCELOT_SPAWN_EGG,
							Material.PANDA_SPAWN_EGG,
							Material.PARROT_SPAWN_EGG,
							Material.PHANTOM_SPAWN_EGG,
							Material.PIG_SPAWN_EGG,
							Material.PIGLIN_SPAWN_EGG,
							Material.PIGLIN_BRUTE_SPAWN_EGG,
							Material.PILLAGER_SPAWN_EGG,
							Material.POLAR_BEAR_SPAWN_EGG,
							Material.PUFFERFISH_SPAWN_EGG,
							Material.RABBIT_SPAWN_EGG,
							Material.RAVAGER_SPAWN_EGG,
							Material.SALMON_SPAWN_EGG,
							Material.SHEEP_SPAWN_EGG,
							Material.SHULKER_SPAWN_EGG,
							Material.SILVERFISH_SPAWN_EGG,
							Material.SKELETON_SPAWN_EGG,
							Material.SKELETON_HORSE_SPAWN_EGG,
							Material.SLIME_SPAWN_EGG,
							Material.SPIDER_SPAWN_EGG,
							Material.SQUID_SPAWN_EGG,
							Material.STRAY_SPAWN_EGG,
							Material.STRIDER_SPAWN_EGG,
							Material.TADPOLE_SPAWN_EGG,
							Material.TRADER_LLAMA_SPAWN_EGG,
							Material.TROPICAL_FISH_SPAWN_EGG,
							Material.TURTLE_SPAWN_EGG,
							Material.VEX_SPAWN_EGG,
							Material.VILLAGER_SPAWN_EGG,
							Material.VINDICATOR_SPAWN_EGG,
							Material.WANDERING_TRADER_SPAWN_EGG,
							Material.WARDEN_SPAWN_EGG,
							Material.WITCH_SPAWN_EGG,
							Material.WITHER_SKELETON_SPAWN_EGG,
							Material.WOLF_SPAWN_EGG,
							Material.ZOGLIN_SPAWN_EGG,
							Material.ZOMBIE_SPAWN_EGG,
							Material.ZOMBIE_HORSE_SPAWN_EGG,
							Material.ZOMBIE_VILLAGER_SPAWN_EGG,
							Material.ZOMBIFIED_PIGLIN_SPAWN_EGG,
							Material.PLAYER_HEAD,
							Material.COMMAND_BLOCK_MINECART,
							Material.KNOWLEDGE_BOOK,
							Material.DEBUG_STICK,
							Material.FROGSPAWN
						)

						while (itemMaterial == null) {
							itemMaterial = Material.entries.random()

							if (unobtainableItems.contains(itemMaterial) || ! itemMaterial.isItem) {
								itemMaterial = null
							}
						}

						task.iconMaterial = itemMaterial
						task.iconAmount = stackSize


					}

					TYPE.ADVANCEMENTS -> {
						val advancements: MutableList<Advancement> = mutableListOf()

						Bukkit.advancementIterator().forEach {
							advancement -> advancements.add(advancement)
						}

						while (task.id == "default") {
							val pickedAdvancement = advancements.random()

							if (pickedAdvancement.key.key.split("/")[0] != "recipes") {
								task.id = pickedAdvancement.key.key
							}
						}
					}

					TYPE.ODDBALL -> {}
				}

				card[row].add(task)
			}
		}

		return card
	}

	enum class TYPE(val displayName: String, val color: TextColor) {
		ITEM("Item", TextColor.color(0xffbf00)),
		ADVANCEMENTS("Advancements", TextColor.color(0x88ff4d)),
		ODDBALL("Oddball", TextColor.color(0xc067ff)),
	}
}

