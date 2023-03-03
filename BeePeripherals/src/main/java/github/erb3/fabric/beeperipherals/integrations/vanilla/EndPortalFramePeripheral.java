package github.erb3.fabric.beeperipherals.integrations.vanilla;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EndPortalFramePeripheral implements IPeripheral {
    private final World world;
    private final BlockPos pos;

    public EndPortalFramePeripheral(World world, BlockPos pos) {
        this.world = world;
        this.pos = pos;
    }

    @LuaFunction
    public final boolean hasEye() {
        BlockState frame = this.world.getBlockState(this.pos);

        if (!frame.getBlock().equals(Blocks.END_PORTAL_FRAME)) {
            throw new Error("The target block isn't a end portal frame!");
        }

        return frame.get(EndPortalFrameBlock.EYE);
    }

    @Override
    public final String getType() {
        return "end_portal_frame";
    }

    @Override
    public final boolean equals(@Nullable IPeripheral other) {
        return world.getBlockState(pos).getBlock().equals(Blocks.END_PORTAL_FRAME);
    }
}
