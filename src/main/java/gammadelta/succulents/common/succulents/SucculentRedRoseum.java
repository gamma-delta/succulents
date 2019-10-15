package gammadelta.succulents.common.succulents;

import com.google.common.collect.ImmutableMap;
import gammadelta.succulents.common.SucculentsMod;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * https://www.ftd.com/blog/content/uploads/2017/09/succulents-roseum.jpg
 * Generates mana by eating redstone and other components.
 * While it's consuming the redstone, it must be given a constant redstone pulse.
 * Giving it the wrong pulse, or giving it redstone otherwise, kills it.
 */
public class SucculentRedRoseum extends GeneratingSucculent {
    public static final String name = "red_roseum";

    // NBT Tags
    private static final String TAG_IS_ENABLED = "isEnabled";
    private static final String TAG_TIMER = "timer";

    private static final int RANGE = 1; //eats up to 1 block away
    private static final Map<Item, Tuple<Integer, Integer>> ITEM_TO_MANA = initMap(); //Maps item to (mana, timer)
    private static final int EAT_PARTICLE_COUNT = 10;
    private static final int TIMER_PARTICLE_COUNT = 10;
    private static final float WORK_PARTICLE_CHANCE = 0.5f;

    private int timer;
    private boolean isOn;

    private static Map<Item, Tuple<Integer, Integer>> initMap() {
        Map<Item, Tuple<Integer, Integer>> out = new HashMap<>(); // {item: (mana, timerInTicks)}

        out.put(Items.REDSTONE, new Tuple<>(3_000, 2 * 20));
        out.put(Item.getItemFromBlock(Blocks.REDSTONE_BLOCK), new Tuple<>(30_000, 18 * 20));

        return out;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (supertile.getWorld().isRemote) return;

        if (isOn) { // we're counting down
            timer--;
            if (timer >= 0) {
                isOn = false;
                return;
            }

            if (Math.random() <= WORK_PARTICLE_CHANCE) {
                BlockPos pos = supertile.getPos();
                supertile.getWorld().spawnParticle(EnumParticleTypes.REDSTONE,
                        pos.getX(), pos.getY(), pos.getZ(),
                        0, 0, 0);
            }


            if (timer % getRedstoneInterval() == 0) { //timer ticked
                BlockPos pos = supertile.getPos();
                for (int c = 0; c < TIMER_PARTICLE_COUNT; c++)
                    supertile.getWorld().spawnParticle(EnumParticleTypes.REDSTONE,
                            pos.getX(), pos.getY(), pos.getZ(),
                            0, 0.2, 0);

                if (redstoneSignal == 0) //we want a redstone signal but didn't get it ;(
                    SucculentsMod.logger.info("Didn't get a pulse for the Red Roseum ;(");
            } else {
                if (redstoneSignal != 0) //we don't want a redstone signal but did get it >:(
                    SucculentsMod.logger.info("Got a wrong pulse for the Red Roseum >:(");
            }

        } else { //we can eat a thing
            List<EntityItem> items = supertile.getWorld().getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(supertile.getPos().add(-RANGE, -RANGE, -RANGE), supertile.getPos().add(RANGE + 1, RANGE + 1, RANGE + 1)));
            int slowdown = getSlowdownFactor();

            for (EntityItem item : items) {
                ItemStack stack = item.getItem();

                if (!stack.isEmpty() && ITEM_TO_MANA.containsKey(stack.getItem()) && !item.isDead && item.getAge() > slowdown) {
                    stack.shrink(1);
                    mana = Math.min(getMaxMana(), mana + ITEM_TO_MANA.get(stack.getItem()).getFirst());
                    timer = ITEM_TO_MANA.get(stack.getItem()).getSecond();
                    supertile.getWorld().spawnParticle(EnumParticleTypes.ITEM_CRACK,
                            item.posX, item.posY, item.posZ,
                            0, 0.2, 0,
                            Item.getIdFromItem(stack.getItem()), stack.getItemDamage());
                    break; //only eat one thing at a time.
                }
            }
        }
    }

    private int getRedstoneInterval() {
        Random rand = new Random(supertile.getPos().hashCode());
        return (rand.nextInt(5) + 10) * 2; //between 10 and 30 ticks, step size 2 (so as to work with redstone).
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }
}
