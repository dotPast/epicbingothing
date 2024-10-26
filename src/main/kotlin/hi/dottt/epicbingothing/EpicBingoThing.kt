package hi.dottt.epicbingothing

import com.noxcrew.interfaces.InterfacesListeners
import dev.asodesu.origami.engine.Origami
import org.bukkit.plugin.java.JavaPlugin

class EpicBingoThing : JavaPlugin() {
	override fun onEnable() {
		Origami.init(this)
		InterfacesListeners.install(this)
	}
}