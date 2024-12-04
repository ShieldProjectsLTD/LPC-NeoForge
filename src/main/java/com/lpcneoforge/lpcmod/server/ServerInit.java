package com.lpcneoforge.lpcmod.server;

import com.lpcneoforge.lpcmod.LPCNeoForge;
import net.neoforged.neoforge.common.NeoForge;

public final class ServerInit {
    public static void init() {
        NeoForge.EVENT_BUS.addListener(LPCPermissions::registerPermissionNodes);
        NeoForge.EVENT_BUS.addListener(LPCCommands::registerCommands);
        NeoForge.EVENT_BUS.addListener(LPCEvents::ChatMessage);
        NeoForge.EVENT_BUS.addListener(LPCNeoForge::OnServerStarting);
    }
}
