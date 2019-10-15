package gammadelta.succulents.common.inits;

import gammadelta.succulents.common.SucculentsMod;
import gammadelta.succulents.common.items.MasterItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeManaInfusion;

public final class InitRecipes {
    private static final int RECIPE_COUNT = 1;

    public static RecipeManaInfusion cactusFruitRecipe;

    public static int init() {
        cactusFruitRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(MasterItems.itemCactusFruit, 1, 0), new ItemStack(Blocks.CACTUS), 3000);

        return RECIPE_COUNT;
    }
}
