package gammadelta.succulents.common;

import gammadelta.succulents.common.succulents.SucculentRedRoseum;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import vazkii.botania.api.BotaniaAPI;

@Mod.EventBusSubscriber(modid = SucculentsMod.ID)
public final class Registration {
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> e) {
        SucculentsMod.logger.info("Registering items");
        IForgeRegistry<Item> r = e.getRegistry();

        BotaniaAPI.registerSubTile(SucculentRedRoseum.name, SucculentRedRoseum.class);
        BotaniaAPI.addSubTileToCreativeMenu(SucculentRedRoseum.name);
    }
}
