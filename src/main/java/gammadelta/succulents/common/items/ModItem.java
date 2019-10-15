package gammadelta.succulents.common.items;

import gammadelta.succulents.common.SucculentsMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ModItem extends Item {
    public ModItem(String name) {
        setRegistryName(new ResourceLocation(SucculentsMod.ID, name));
        setUnlocalizedName(name);
    }
}
