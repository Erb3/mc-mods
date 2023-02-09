package github.erb3.fabric.nohotbarlooping.mixin;

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

        int i = (int) Math.signum(scrollAmount);
        int selectedSlot = ((PlayerInventory) (Object) this).selectedSlot - i;


        while (selectedSlot < 0) {
            selectedSlot += 1;
        }

        while(selectedSlot >= 9) {
            selectedSlot -= 1;
        }

        ((PlayerInventory) (Object) this).selectedSlot = selectedSlot;
    }
}
