package dk.mrspring.kitchen.api.sandwichable;

import net.minecraft.item.ItemStack;

/**
 * Created by Konrad on 13-05-2015.
 */
public interface ISandwichable
{
    int getHealAmount();

    ISandwichable setHealAmount(int healAmount);

    ItemStack getStack();

    ISandwichable setStack(ItemStack stack);

    boolean getIsBread();

    ISandwichable setIsBread(boolean isBread);

    boolean getHideInformation();

    ISandwichable setHideInformation(boolean hideInformation);

    boolean getShowInformation();

    ISandwichable setShowInformation(boolean showInformation);

    boolean getDropItem();

    ISandwichable setDropItem(boolean dropItem);

    boolean doesStackMatch(ItemStack stack);

    ItemStack getBoardStack(ItemStack original);

    void onAdded(ItemStack added);

    // TODO: Add missing methods. See SandwichableRegistry and SandwichableItemHandler
}