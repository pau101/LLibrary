package net.ilexiconn.llibrary.server.asm;

import net.ilexiconn.llibrary.client.event.PlayerModelEvent;
import net.ilexiconn.llibrary.client.event.RenderArmEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public enum LLibraryASMHandler {
    INSTANCE;

    public boolean renderLeftArmPre(AbstractClientPlayer player, RenderPlayer renderPlayer) {
        return MinecraftForge.EVENT_BUS.post(new RenderArmEvent.Left.Pre(player, renderPlayer, renderPlayer.getMainModel()));
    }

    public void renderLeftArmPost(AbstractClientPlayer player, RenderPlayer renderPlayer) {
        MinecraftForge.EVENT_BUS.post(new RenderArmEvent.Left.Post(player, renderPlayer, renderPlayer.getMainModel()));
    }

    public boolean renderRightArmPre(AbstractClientPlayer player, RenderPlayer renderPlayer) {
        return MinecraftForge.EVENT_BUS.post(new RenderArmEvent.Right.Pre(player, renderPlayer, renderPlayer.getMainModel()));
    }

    public void renderRightArmPost(AbstractClientPlayer player, RenderPlayer renderPlayer) {
        MinecraftForge.EVENT_BUS.post(new RenderArmEvent.Right.Post(player, renderPlayer, renderPlayer.getMainModel()));
    }

    public void setRotationAngles(ModelPlayer model, Entity entity, float limbSwing, float limbSwingAmount, float rotation, float rotationYaw, float rotationPitch, float scale) {
        MinecraftForge.EVENT_BUS.post(new PlayerModelEvent.SetRotationAngles(model, (EntityPlayer) entity, limbSwing, limbSwingAmount, rotation, rotationYaw, rotationPitch, scale));
    }

    public void constructModel(ModelPlayer model) {
        MinecraftForge.EVENT_BUS.post(new PlayerModelEvent.Construct(model));
    }

    public void renderModel(ModelPlayer model, Entity entity, float limbSwing, float limbSwingAmount, float rotation, float rotationYaw, float rotationPitch, float scale) {
        MinecraftForge.EVENT_BUS.post(new PlayerModelEvent.Render(model, (EntityPlayer) entity, limbSwing, limbSwingAmount, rotation, rotationYaw, rotationPitch, scale));
    }

    public ModelPlayer assign(RenderPlayer renderPlayer, ModelPlayer model, boolean smallArms) {
        PlayerModelEvent.Assign event = new PlayerModelEvent.Assign(renderPlayer, model, smallArms);
        MinecraftForge.EVENT_BUS.post(event);
        return event.getModel();
    }
}
