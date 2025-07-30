package com.lpcneoforge.lpcmod;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(value = Dist.DEDICATED_SERVER, modid = LPCNeoForge.MOD_ID)
public class Config {
  private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
  public static final ModConfigSpec SPEC;

  public static final ModConfigSpec.ConfigValue<String> CHAT_FORMAT;

  static {
    BUILDER.comment("""
                -----------------------------------
                - Placeholders:
                 %prefix%
                 %suffix%
                 %prefixes%
                 %suffixes%
                 %world%
                 %username%
                 %displayname%
                 %username-color%  | tip: add meta to user or group "meta.username-color.&<colorCode>"
                 %message-color%   | tip: add meta to user or group "meta.message-color.&<colorCode>"
                 %chatmessage%
                -----------------------------------
                """);
    BUILDER.comment("""
                - Color Codes:
                 &0 - Black      | &1 - Dark Blue
                 &2 - Dark Green | &3 - Dark Aqua
                 &4 - Dark Red   | &5 - Dark Purple
                 &6 - Gold       | &7 - Gray
                 &8 - Dark Gray  | &9 - Blue
                 &a - Green      | &b - Aqua
                 &c - Red        | &d - Light Purple
                 &e - Yellow     | &f - White
                -----------------------------------
                 &k - Random Symbols | &l - Bold
                 &m - Strikethrough  | &n - Underline
                 &o - Italic         | &r - Reset
                -----------------------------------
                 #RRGGBB - Hex Color Codes (e.g. #ff00ff)
                -----------------------------------
                """);
    CHAT_FORMAT = BUILDER.comment("Chat format string with placeholders.")
            .define("chatFormat", "%prefix%%username%%suffix%: %chatmessage%");

    SPEC = BUILDER.build();
  }

  @SubscribeEvent
  public static void onLoad(final ModConfigEvent event) {}
}
