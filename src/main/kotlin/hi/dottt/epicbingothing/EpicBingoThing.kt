package hi.dottt.epicbingothing

import dev.asodesu.origami.engine.Origami
import org.bukkit.plugin.java.JavaPlugin

class EpicBingoThing : JavaPlugin() {
    override fun onEnable() {
        Origami.init(this)
    }
}