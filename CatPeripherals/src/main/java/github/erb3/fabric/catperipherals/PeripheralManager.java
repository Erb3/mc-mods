package github.erb3.fabric.catperipherals;

import github.erb3.fabric.catperipherals.peripherals.redstoneIntegrator.RedstoneIntegratorBlock;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;


public class PeripheralManager {
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier("catperipherals", "group"))
            .icon(() -> new ItemStack(RedstoneIntegratorBlock.getBlock()))
            .build();

    public static void registerBlocks() {
        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> {
            content.add(RedstoneIntegratorBlock.getBlock());
        });

        RedstoneIntegratorBlock.register();
    }
}
