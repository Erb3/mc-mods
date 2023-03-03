package github.erb3.fabric.beeperipherals;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.peripheral.PeripheralLookup;
import github.erb3.fabric.beeperipherals.integrations.vanilla.EndPortalFramePeripheral;
import github.erb3.fabric.beeperipherals.integrations.vanilla.SignPeripheral;
import net.minecraft.block.Blocks;

public class IntegrationRegister {

    public static void registerPeripherals() {
        ComputerCraftAPI.registerGenericSource(new SignPeripheral());
        PeripheralLookup.get().registerForBlocks((world, pos, state, blockEntity, context) ->
                new EndPortalFramePeripheral(world, pos), Blocks.END_PORTAL_FRAME);
    }

}
