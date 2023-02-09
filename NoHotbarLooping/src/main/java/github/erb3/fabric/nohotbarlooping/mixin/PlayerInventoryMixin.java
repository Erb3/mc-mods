package github.erb3.fabric.nohotbarlooping.mixin;

import github.erb3.fabric.nohotbarlooping.Nohotbarlooping;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    /**
     * @author Erb3
     * @reason To disable looping
     */
    @Overwrite
    public void scrollInHotbar(double scrollAmount) {
        assert Nohotbarlooping.client.player != null;
        PlayerInventory inv = Nohotbarlooping.client.player.getInventory();

        int i = (int) Math.signum(scrollAmount);
        int selectedSlot = inv.selectedSlot - i;

        while (selectedSlot < 0) {
            selectedSlot += 1;
        }

        while(selectedSlot >= 9) {
            selectedSlot -= 1;
        }

        inv.selectedSlot = selectedSlot;
    }
}
