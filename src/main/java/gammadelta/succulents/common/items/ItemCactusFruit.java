package gammadelta.succulents.common.items;

import gammadelta.succulents.common.SucculentsMod;
import gammadelta.succulents.common.util.TooltipOnShift;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCactusFruit extends ModItem {
    public static String name = "cactus_fruit";

    public ItemCactusFruit() {
        super(name);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        TooltipOnShift.Do(tooltip, getUnlocalizedName());
    }
}
