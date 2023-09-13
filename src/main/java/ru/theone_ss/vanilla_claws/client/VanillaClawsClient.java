package ru.theone_ss.vanilla_claws.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import ru.theone_ss.vanilla_claws.item.ClawsItem;
import ru.theone_ss.vanilla_claws.registry.VanillaClawsItems;

public class VanillaClawsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        VanillaClawsItems.ITEMS.forEach((identifier, item) -> {
            if (item instanceof ClawsItem) TwoModelsItemRegistry.register(item);
        });

        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> TwoModelsItemRegistry.ENTRIES.forEach((id, item) ->
                out.accept(new ModelIdentifier(new Identifier(id + "_in_hand"), "inventory"))
        ));
    }
 }

