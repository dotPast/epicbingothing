package hi.dottt.epicbingothing

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
            LifecycleEvents.COMMANDS
        ) { event: ReloadableRegistrarEvent<Commands?> ->
            val commands = event.registrar()

        }
    }
}