package gammadelta.succulents.common;

import gammadelta.succulents.common.inits.InitRecipes;
import gammadelta.succulents.common.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = SucculentsMod.ID, name = SucculentsMod.NAME, version = SucculentsMod.VERSION, dependencies = SucculentsMod.DEPENDENCIES)
public class SucculentsMod {
    public static final String ID = "succulents";
    public static final String NAME = "Succulents";
    public static final String BUILD = "GRADLE:BUILD";
    public static final String VERSION = "GRADLE:VERSION-" + BUILD;
    public static final String DEPENDENCIES = "required-after:botania;";
    public static final String CLIENT_PROXY = "gammadelta.succulents.common.proxy.ClientProxy";
    public static final String SERVER_PROXY = "gammadelta.succulents.common.proxy.ServerProxy";

    @Mod.Instance(ID)
    public static SucculentsMod instance;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    public static IProxy proxy;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();

        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        InitRecipes.init();

        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
