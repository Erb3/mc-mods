package github.erb3.fabric.windmill.mixin;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin {

    @ModifyVariable(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "FIELD",
            target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;riding:Z", opcode = Opcodes.GETFIELD),
            ordinal = 5, print = true)
    private float onKAssignment(float k, LivingEntity livingEntity, float f, float g, float h, float i, float j) {
        ((BipedEntityModel)(Object)this).rightArm.pitch = f * 2.0F * 0.5F / k;
        ((BipedEntityModel)(Object)this).leftArm.pitch = f * 2.0F * 0.5F / k;
        ((BipedEntityModel)(Object)this).rightLeg.pitch = f * 1.4F / k;
        ((BipedEntityModel)(Object)this).leftLeg.pitch = (f + 3.1415927F) * 1.4F / k;
        return k;
    }

}
