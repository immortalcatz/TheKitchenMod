package dk.mrspring.kitchen.client.model;

import dk.mrspring.kitchen.ModInfo;

public class ModelBaconRaw extends ModelBase
{
    public ModelBaconRaw()
    {
        super(ModInfo.toResource("textures/models/raw_bacon.png"), 64, 32);

        this.addBox(13, 21, 2.466667F, 23F, -4F, 3, 1, 2);
        this.addBox(13, 21, 2.466667F, 23F, -6F, 3, 1, 2);
        this.addBox(0, 18, 2.466667F, 23F, -2F, 3, 1, 3);
        this.addBox(0, 18, 2.466667F, 23F, 1F, 3, 1, 3);
        this.addBox(0, 18, 2.466667F, 23F, 4F, 3, 1, 3);

        this.addBox(0, 23, -1.533333F, 23F, 4F, 3, 1, 3);
        this.addBox(0, 23, -1.533333F, 23F, 1F, 3, 1, 3);
        this.addBox(13, 25, -1.533333F, 23F, -1F, 3, 1, 2);
        this.addBox(0, 23, -1.533333F, 23F, -4F, 3, 1, 3);
        this.addBox(0, 23, -1.533333F, 23F, -7F, 3, 1, 3);

        this.addBox(13, 29, -5.533333F, 23F, -7F, 3, 1, 2);
        this.addBox(0, 28, -5.533333F, 23F, -5F, 3, 1, 3);
        this.addBox(0, 28, -5.533333F, 23F, -2F, 3, 1, 3);
        this.addBox(24, 27, -5.533333F, 23F, 1F, 3, 1, 4);
        this.addBox(0, 28, -5.533333F, 23F, 5F, 3, 1, 3);
    }
}