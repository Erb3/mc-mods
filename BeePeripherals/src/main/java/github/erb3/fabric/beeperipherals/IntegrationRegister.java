package github.erb3.fabric.beeperipherals;

import dan200.computercraft.api.peripheral.PeripheralLookup;
import github.erb3.fabric.beeperipherals.integrations.vanilla.ComposterPeripheral;
import github.erb3.fabric.beeperipherals.integrations.vanilla.EndPortalFramePeripheral;
import github.erb3.fabric.beeperipherals.integrations.vanilla.SignPeripheral;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SignBlockEntity;

public class IntegrationRegister {

    public static void registerPeripherals() {
        PeripheralLookup.get().registerForBlockEntities((blockEntity, context) -> new SignPeripheral((SignBlockEntity) blockEntity),
                BlockEntityType.SIGN);

        PeripheralLookup.get().registerForBlocks((world, pos, state, blockEntity, context) ->
                new EndPortalFramePeripheral(world, pos), Blocks.END_PORTAL_FRAME);
        PeripheralLookup.get().registerForBlocks((world, pos, state, blockEntity, context) ->
                new ComposterPeripheral(world, pos), Blocks.COMPOSTER); // Can't just pass the state
    }

}
