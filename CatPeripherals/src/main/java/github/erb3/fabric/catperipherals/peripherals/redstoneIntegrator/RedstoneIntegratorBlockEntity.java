package github.erb3.fabric.catperipherals.peripherals.redstoneIntegrator;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class RedstoneIntegratorBlockEntity extends BlockEntity {
    public RedstoneIntegratorBlockEntity(BlockPos pos, BlockState state) {
        super(RedstoneIntegratorBlock.blockEntityType, pos, state);
    }
}
