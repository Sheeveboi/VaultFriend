package net.altosheeve.vaultfriend;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.util.math.BlockPos;

import java.io.IOException;

public class VaultFriend implements ModInitializer {
    @Override
    public void onInitialize() {

        Keymappings.registerKeys();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                Interactions.handleKeys();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        WorldRenderEvents.BEFORE_DEBUG_RENDER.register(((context) -> {
            try {
                Rendering.render();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        System.out.println("hell yeah!");
    }
}
