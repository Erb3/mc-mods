package github.erb3.fabric.beeperipherals.integrations.vanilla;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ComposterPeripheral implements IPeripheral {
    private final World world;
    private final BlockPos pos;

    public ComposterPeripheral(World world, BlockPos pos) {
        this.world = world;
        this.pos = pos;
    }

    private BlockState getState() {
        return this.world.getBlockState(this.pos);
    }

    @Override
    public String getType() {
        return "composter";
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return getState().getBlock().equals(Blocks.COMPOSTER);
    }

    @LuaFunction
    public final int getLevel() {
        return getState().get(ComposterBlock.LEVEL);
    }
}
