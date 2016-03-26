package dk.mrspring.kitchen.client.model;

import com.google.common.collect.Lists;
import dk.mrspring.kitchen.client.util.ClientUtils;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

/**
 * Created on 07-03-2016 for TheKitchenMod.
 */
public class ModelBase<T extends IRenderParameter> extends net.minecraft.client.model.ModelBase
{
    public final RenderContext DEFAULT_CONTEXT = new RenderContext();
    public final RenderContext BLOCK_CONTEXT = new RenderContext(); // TODO: Translate like block (TESR).

    public ResourceLocation texture;
    List<ModelRenderer> parts = Lists.newLinkedList();
    RenderContext context = DEFAULT_CONTEXT.copy();

    public ModelBase(ResourceLocation texture, int textureWidth, int textureHeight, ModelRenderer... parts)
    {
        this.texture = texture;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        Collections.addAll(this.parts, parts);
    }

    public ModelBase(String texture, int textureWidth, int textureHeight, ModelRenderer... parts)
    {
        this(new ResourceLocation(texture), textureWidth, textureHeight, parts);
    }

    public ModelBase useBlockDefaults()
    {
        return this.setContext(BLOCK_CONTEXT.copy());
    }

    public ModelBase useDefaults()
    {
        return this.setContext(DEFAULT_CONTEXT.copy());
    }

    public ModelBase setContext(RenderContext context)
    {
        this.context = context;
        return this;
    }

    public RenderContext makeContext()
    {
        return this.context.copy();
    }

    public void preRender(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, RenderContext context)
    {
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.render(entity, f, f1, f2, f3, f4, f5, makeContext());
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, T parameters)
    {
        RenderContext context = makeContext();
        context.parameters = parameters;
        this.render(entity, f, f1, f2, f3, f4, f5, context);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, RenderContext context)
    {
        preRender(entity, f, f1, f2, f3, f4, f5, context);
        ClientUtils.push();
        ClientUtils.bind(this.texture);
        for (ModelRenderer renderer : parts) renderer.render(f5);
        renderExtras(entity, f, f1, f2, f3, f4, f5, context);
        ClientUtils.pop();
    }

    public void renderExtras(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, RenderContext context)
    {
    }

    /**
     * Renders an extra box when needed.
     *
     * @param f5 The last float in the {@link #render(Entity, float, float, float, float, float, float)} function.
     * @return Returns this model.
     */
    public ModelBase renderBox(int u, int v,
                               float xOffset, float yOffset, float zOffset,
                               int width, int height, int depth, float pivotX, float pivotY, float pivotZ,
                               float rotationX, float rotationY, float rotationZ, float f5)
    {
        ModelPart box = new ModelPart(this, u, v)
                .setTextureSize(textureWidth, textureHeight)
                .setMirrored(true)
                .setPivot(pivotX, pivotY, pivotZ)
                .setRotation(rotationX, rotationY, rotationZ)
                .addBox(xOffset, yOffset, zOffset, width, height, depth);
        box.render(f5);
        return this;
    }

    /**
     * Renders an extra box when needed.
     *
     * @param f5 The last float in the {@link #render(Entity, float, float, float, float, float, float)} function.
     * @return Return this model.
     */
    public ModelBase renderBox(int u, int v,
                               float x, float y, float z,
                               int width, int height, int depth, float f5)
    {
        return this.renderBox(u, v, 0F, 0F, 0F, width, height, depth, x, y, z, 0F, 0F, 0F, f5);
    }

    public ModelBase addPart(ModelRenderer part)
    {
        this.parts.add(part);
        return this;
    }

    public ModelBase addBox(int u, int v, float xOffset, float yOffset, float zOffset, int width, int height, int depth,
                            float pivotX, float pivotY, float pivotZ)
    {
        return this.addPart(new ModelPart(this, u, v).addBox(xOffset, yOffset, zOffset, width, height, depth)
                .setPivot(pivotX, pivotY, pivotZ).setTextureSize(textureWidth, textureHeight).setMirrored(true));
    }

    public ModelBase addBox(int u, int v, float x, float y, float z, int width, int height, int depth)
    {
        return this.addBox(u, v, 0F, 0F, 0F, width, height, depth, x, y, z);
    }

    public void setRotation(float x, float y, float z, int... indexes)
    {
        for (int i : indexes) setRotation(i, x, y, z);
    }

    public void setRotation(int index, float x, float y, float z)
    {
        setRotation(parts.get(index), x, y, z);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public ModelBase hideModels(int... indexes)
    {
        for (int i : indexes) hideModel(i);
        return this;
    }

    public ModelBase hideModel(int index)
    {
        parts.get(index).isHidden = true;
        return this;
    }

    public ModelBase showModels(int... indexes)
    {
        for (int i : indexes) showModel(i);
        return this;
    }

    public ModelBase showModel(int index)
    {
        parts.get(index).isHidden = false;
        return this;
    }

    public class RenderContext
    {
        public final float xOffset, yOffset, zOffset;
        public final float xRotation, yRotation, zRotation;
        public final float xScale, yScale, zScale;
        public T parameters = null;

        public RenderContext()
        {
            this(0F, 0F, 0F, 0F, 0F, 0F, 1F, 1F, 1F, null);
        }

        public RenderContext(float xOffset, float yOffset, float zOffset,
                             float xRotation, float yRotation, float zRotation,
                             float xScale, float yScale, float zScale,
                             T parameters)
        {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            this.zOffset = zOffset;
            this.xRotation = xRotation;
            this.yRotation = yRotation;
            this.zRotation = zRotation;
            this.xScale = xScale;
            this.yScale = yScale;
            this.zScale = zScale;
            this.parameters = parameters;
        }

        public RenderContext copy()
        {
            RenderContext context = new RenderContext(xOffset, yOffset, zOffset,
                    xRotation, yRotation, zRotation,
                    xScale, yScale, zScale, null);

            if (this.parameters != null)
                context.parameters = (T) this.parameters.copy();

            return context;
        }
    }
}