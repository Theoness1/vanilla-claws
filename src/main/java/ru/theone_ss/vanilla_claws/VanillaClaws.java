package ru.theone_ss.vanilla_claws;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ru.theone_ss.vanilla_claws.compat.WinterlyIntegration;
import ru.theone_ss.vanilla_claws.registry.VanillaClawsItems;

public class VanillaClaws implements ModInitializer {
    public static ItemGroup BANILLA_CLAWS;
    public static final String MOD_ID = "vanilla_claws";

    @Override
    public void onInitialize() {
        VanillaClawsItems.init();
        BANILLA_CLAWS = createItemGroup();

        if(FabricLoader.getInstance().isModLoaded("winterly")) {
            WinterlyIntegration.init();
        }
    }

    private static ItemGroup createItemGroup() {
        var group = FabricItemGroup.builder().displayName(Text.translatable("Banilla Claws"))
                .icon(() -> VanillaClawsItems.DIAMOND_CLAWS.asItem().getDefaultStack())
                .entries((displayContext, entries) -> VanillaClawsItems.ITEMS.forEach((id, item) -> entries.add(item.getDefaultStack()))).build();
        Registry.register(Registries.ITEM_GROUP, id("items"), group);
        return group;
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

}
