package gammadelta.succulents.common.proxy;

import gammadelta.succulents.common.SucculentsMod;
import gammadelta.succulents.common.succulents.SucculentRedRoseum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vazkii.botania.api.BotaniaAPIClient;

public class ClientProxy implements IProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        this.registerModels();
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    private void registerModels() {
        registerModel(SucculentRedRoseum.name);
    }

    private void registerModel(String name) {
        BotaniaAPIClient.registerSubtileModel(name,
                new ModelResourceLocation(new ResourceLocation(SucculentsMod.ID, name), "normal"),
                new ModelResourceLocation(new ResourceLocation(SucculentsMod.ID, name), "inventory"));
    }
}
