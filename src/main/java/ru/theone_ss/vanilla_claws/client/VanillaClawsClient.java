package ru.theone_ss.vanilla_claws.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.util.ModelIdentifier;
import ru.theone_ss.vanilla_claws.registry.VanillaClawsItems;

public class VanillaClawsClient implements ClientModInitializer {



    @Override
    public void onInitializeClient() {

        TwoModelsItemRegistry.register(VanillaClawsItems.DIAMOND_CLAWS);

        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> TwoModelsItemRegistry.ENTRIES.forEach((id, item) ->
                out.accept(new ModelIdentifier(id + "_in_hand#inventory"))
        ));
    }
 }

