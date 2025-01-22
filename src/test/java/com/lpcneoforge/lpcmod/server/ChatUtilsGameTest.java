package com.lpcneoforge.lpcmod.server;

import net.minecraft.ChatFormatting;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.MutableComponent;

public class ChatUtilsGameTest {
  @GameTest(template = "empty")
  public static void testApplyColorToLiteral(GameTestHelper helper) {
    String text = "Hello World";
    String colorCode = "red";

    // Ваш метод
    MutableComponent result = ChatUtils.applyColorToLiteral(text, colorCode);

    // Проверка результата
    if (!result.getStyle().getColor().equals(ChatFormatting.RED.getColor())) {
      helper.fail("Color application failed!");
    } else {
      helper.succeed();
    }
  }
}
