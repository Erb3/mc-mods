package github.erb3.fabric.windmill.mixin;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin {

//    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getMainArm()Lnet/minecraft/util/Arm;"), locals = LocalCapture.PRINT)
//    private void onSetAngles(LivingEntity livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci, float k) {
//        ((BipedEntityModel)(Object)this).rightArm.pitch = f * 2.0F * 0.5F / k;
//        ((BipedEntityModel)(Object)this).leftArm.pitch = f * 2.0F * 0.5F / k;
//        ((BipedEntityModel)(Object)this).rightLeg.pitch = f * 1.4F / k;
//        ((BipedEntityModel)(Object)this).leftLeg.pitch = (f + 3.1415927F) * 1.4F / k;
//    }

    @ModifyVariable(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getMainArm()Lnet/minecraft/util/Arm;"), ordinal = 1, print = true)
    private float onKAssignment(float k, LivingEntity livingEntity, float f, float g, float h, float i, float j) {
        ((BipedEntityModel)(Object)this).rightArm.pitch = f * 2.0F * 0.5F / k;
        ((BipedEntityModel)(Object)this).leftArm.pitch = f * 2.0F * 0.5F / k;
        ((BipedEntityModel)(Object)this).rightLeg.pitch = f * 1.4F / k;
        ((BipedEntityModel)(Object)this).leftLeg.pitch = (f + 3.1415927F) * 1.4F / k;
        return k;
    }

}
