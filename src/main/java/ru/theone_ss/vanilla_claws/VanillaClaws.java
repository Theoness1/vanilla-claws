package ru.theone_ss.vanilla_claws;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import ru.theone_ss.vanilla_claws.compat.WinterlyIntegration;
import ru.theone_ss.vanilla_claws.registry.VanillaClawsItems;

public class VanillaClaws implements ModInitializer {
    public static final String MOD_ID = "vanilla_claws";

    @Override
    public void onInitialize() {
        VanillaClawsItems.init();
        if(FabricLoader.getInstance().isModLoaded("winterly")) {
            WinterlyIntegration.init();
        }
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

}
