package hi.dottt.epicbingothing

import com.mojang.brigadier.context.CommandContext
import hi.dottt.epicbingothing.commands.AddCardsCommand
import hi.dottt.epicbingothing.commands.ShowCardCommand
import hi.dottt.epicbingothing.commands.StartGameCommand
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents

@Suppress("UnstableApiUsage")
class EBTBootstrap : PluginBootstrap {
    override fun bootstrap(context: BootstrapContext) {
        val manager = context.lifecycleManager
        manager.registerEventHandler<ReloadableRegistrarEvent<Commands?>>(
            LifecycleEvents.COMMANDS,
        ) { event: ReloadableRegistrarEvent<Commands?> ->
            val commands = event.registrar()!!
            commands.register(
                Commands.literal("card")
                    .executes { ctx: CommandContext<CommandSourceStack> ->
                        ShowCardCommand().execute(ctx)
                    }
                    .build(),
            )
            commands.register(
                Commands.literal("addcards")
                    .executes { ctx: CommandContext<CommandSourceStack> ->
                        AddCardsCommand().execute(ctx)
                    }
                    .build(),
            )

            commands.register(
                Commands.literal("startgame")
                    .executes { ctx: CommandContext<CommandSourceStack> ->
                        StartGameCommand().execute(ctx)
                    }
                    .build(),
            )
        }
    }
}
