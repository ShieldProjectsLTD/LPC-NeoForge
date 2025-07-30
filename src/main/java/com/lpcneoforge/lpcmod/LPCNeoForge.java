package com.lpcneoforge.lpcmod;

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
public final class LPCNeoForge {
  public static final String MOD_ID = "lpcmod";
  public static final Logger LOGGER = LogUtils.getLogger();

  private static LPCNeoForge instance;
  private static LuckPerms luckPerms;
  private static ModContainer modContainer;

  public LPCNeoForge(ModContainer container, IEventBus modEventBus) {
    modContainer = container;

    if (FMLEnvironment.dist.isClient()) {
      LOGGER.error("LPC-NeoForge: This mod is not designed to run on the client! Disabling...");
    } else {
      if(instance != null) {
        IllegalStateException exception = new IllegalStateException("Tried to create mod " + LPCNeoForge.MOD_ID + " more than once!");
        LOGGER.error(exception.getMessage(), exception);
        throw exception;
      }
      instance = this;

      EventListenersInit.init(modEventBus);
      container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
  }

  @SubscribeEvent(priority = EventPriority.NORMAL)
  public static void OnServerStarting(ServerStartingEvent event) {
    luckPerms = LuckPermsProvider.get();
    LOGGER.info("LuckPerms has been loaded!");
  }

  public static void commonSetup(final FMLCommonSetupEvent ignoredEvent) {
    final String displayName = modContainer.getModInfo().getDisplayName();
    final String version = modContainer.getModInfo().getVersion().toString();
    LOGGER.info("         __   ___                      ");
    LOGGER.info("   |    |__) |     {} v{}", displayName, version);
    LOGGER.info("   |___ |    |___  Chat Formatter      ");
    LOGGER.info("                                       ");
  }

  public static LuckPerms getLuckperms() {
    return luckPerms;
  }
}


