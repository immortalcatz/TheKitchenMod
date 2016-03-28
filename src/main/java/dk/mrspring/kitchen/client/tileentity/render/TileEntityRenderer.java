package dk.mrspring.kitchen.client.tileentity.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import static dk.mrspring.kitchen.client.util.ClientUtils.*;

/**
 * Created on 27-03-2016 for TheKitchenMod.
 */
public abstract class TileEntityRenderer<T extends TileEntity> extends TileEntitySpecialRenderer
{
    public void translateBlockModel(double x, double y, double z)
    {
        translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        rotate(180F, 0F, 0F, 1F);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float partial)
    {
        push();
        translateBlockModel(x, y, z);
        push();
        renderModel((T) entity, partial);
        pop();
        pop();
    }

    protected abstract void renderModel(T entity, float partial);
}
