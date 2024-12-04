package com.lpcneoforge.lpcmod;

import com.lpcneoforge.lpcmod.server.ServerInit;
import net.luckperms.api.LuckPermsProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import net.luckperms.api.LuckPerms;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod(value = LPCNeoForge.MOD_ID, dist = Dist.DEDICATED_SERVER)
public class LPCNeoForge {
    public static final String MOD_ID = "lpcmod";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static LPCNeoForge INSTANCE;

    private final ModContainer modContainer;

    private static LuckPerms luckPerms;

    public LPCNeoForge(ModContainer container, IEventBus modEventBus) {
        modContainer = container;
        if (FMLEnvironment.dist.isClient()) {
            LOGGER.error("LPC-NeoForge: This mod is not designed to run on the client! Disabling...");
        } else {
            if(INSTANCE != null) {
                IllegalStateException exception = new IllegalStateException("Tried to create mod " + LPCNeoForge.MOD_ID + " more than once!");
                LOGGER.error(exception.getMessage(), exception);
                throw exception;
            }
            INSTANCE = this;
            modEventBus.addListener(this::commonSetup);
            ServerInit.init();

            container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        final String displayName = modContainer.getModInfo().getDisplayName();
        final String version = modContainer.getModInfo().getVersion().toString();
        LOGGER.info("         __   ___                      ");
        LOGGER.info("   |    |__) |     {} v{}", displayName, version);
        LOGGER.info("   |___ |    |___  Chat Formatter      ");
        LOGGER.info("                                       ");
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void OnServerStarting(ServerStartingEvent event) {
        luckPerms = LuckPermsProvider.get();
        LOGGER.info("LuckPerms has been loaded!");
    }

//    public static LPCNeoForge getInstance() {
//        return INSTANCE;
//    }

    public static LuckPerms getLuckperms() {
        return luckPerms;
    }

//    public void reload() {
//        LOGGER.info("Reloading LPCMod...");
//    }
}


