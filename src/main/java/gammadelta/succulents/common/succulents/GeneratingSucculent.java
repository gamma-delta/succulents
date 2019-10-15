package gammadelta.succulents.common.succulents;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import vazkii.botania.api.subtile.SubTileGenerating;

public class GeneratingSucculent extends SubTileGenerating {

    private void onFailCondition() {
        if (getWorld().isRemote) {
            // We are on the client side so we can play particles
            Vec3d offset = getWorld().getBlockState(getPos()).getOffset(getWorld(), getPos()).addVector(0.4, 0.7, 0.4);
            supertile.getWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
                    supertile.getPos().getX() + offset.x,
                    supertile.getPos().getY() + offset.y,
                    supertile.getPos().getZ() + offset.z,
                    0.1D, 0.2D, 0.1D);
        }

        supertile.getWorld().playSound(null, supertile.getPos(), SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 2.0F, 1F);
    }
}
