package com.lpcneoforge.lpcmod;

import com.lpcneoforge.lpcmod.server.LPCEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;

public final class EventListenersInit {
  public static void init(IEventBus event) {
    //Common NeoForge bus
    NeoForge.EVENT_BUS.addListener(LPCEvents::ChatMessage);
    NeoForge.EVENT_BUS.addListener(LPCNeoForge::OnServerStarting);

    //IModBusEvent events
    event.addListener(LPCNeoForge::commonSetup);
  }
}
