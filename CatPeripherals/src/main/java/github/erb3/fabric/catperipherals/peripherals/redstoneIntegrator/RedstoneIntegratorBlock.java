package github.erb3.fabric.catperipherals.peripherals.redstoneIntegrator;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RedstoneIntegratorBlock extends Block implements BlockEntityProvider {
    public static RedstoneIntegratorBlock REDSTONE_INTEGRATOR_BLOCK = new RedstoneIntegratorBlock();
    public static BlockEntityType<RedstoneIntegratorBlockEntity> blockEntityType;

    public RedstoneIntegratorBlock() {
        super(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    }

    public static void register() {
        Registry.register(Registries.BLOCK, new Identifier("catperipherals", "redstone_integrator"), REDSTONE_INTEGRATOR_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("catperipherals", "redstone_integrator"),
                new BlockItem(REDSTONE_INTEGRATOR_BLOCK, new FabricItemSettings()));
        blockEntityType = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("catperipherals", "redstone_integrator_entity"),
                FabricBlockEntityTypeBuilder.create(RedstoneIntegratorBlockEntity::new, REDSTONE_INTEGRATOR_BLOCK).build());

    }

    public static Block getBlock() {
        return REDSTONE_INTEGRATOR_BLOCK;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RedstoneIntegratorBlockEntity(pos, state);
    }
}
