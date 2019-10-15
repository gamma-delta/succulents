package gammadelta.succulents.common.items;

import gammadelta.succulents.common.SucculentsMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * MasterItems (like all other Master classes)
 * has a list of all the items in the mod.
 */
@Mod.EventBusSubscriber(modid = SucculentsMod.ID)
public final class MasterItems {
    public static Item itemCactusFruit = new ItemCactusFruit();

    @SubscribeEvent
    public static void RegisterItems(RegistryEvent.Register<Item> evt) {
        IForgeRegistry<Item> r = evt.getRegistry();

        r.registerAll(
                itemCactusFruit
        );
    }

    @SubscribeEvent
    public static void RegisterModels(ModelRegistryEvent evt) {
        registerModel(itemCactusFruit);
    }

    private static void registerModel(Item i) {
        ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
    }
}
