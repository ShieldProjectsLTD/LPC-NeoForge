package com.lpcneoforge.lpcmod.server;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

public final class ChatUtils {

  public static MutableComponent applyColorToLiteral(String text, String colorCode) {
    MutableComponent component = ChatUtils.parseFormattedText(text);

    if (colorCode.startsWith("&") && colorCode.length() > 1) {
      char code = colorCode.charAt(1);
      ChatFormatting chatFormatting = ChatUtils.getChatFormattingByCode(code);

      if (chatFormatting != null) {
        // Применяем цвет только к literal
        return Component.literal(text).withStyle(Style.EMPTY.withColor(chatFormatting.getColor()));
      }
    } else if (colorCode.startsWith("#") && colorCode.length() == 7) {
      try {
        int rgb = Integer.parseInt(colorCode.substring(1), 16);
        TextColor color = TextColor.fromRgb(rgb);

        return Component.literal(text).withStyle(Style.EMPTY.withColor(color));
      } catch (NumberFormatException e) {
        return component;
      }
    }

    return component; // Если цвет не определен, возвращаем оригинальный компонент
  }

  public static MutableComponent parseFormattedText(String text) {
    MutableComponent component = Component.literal(""); // Корневой компонент
    StringBuilder buffer = new StringBuilder(); // Текущий текст
    Style currentStyle = Style.EMPTY.withColor(ChatFormatting.WHITE); // Начальный стиль

    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);

      // HEX-цвет (должен начинаться с # и иметь длину 7 символов)
      if (c == '#' && i + 6 < text.length()) {
        String hexCode = text.substring(i, i + 7);
        try {
          int rgb = Integer.parseInt(hexCode.substring(1), 16);
          // Добавляем накопленный буфер со старым стилем
          if (!buffer.isEmpty()) {
            component.append(Component.literal(buffer.toString()).withStyle(currentStyle));
            buffer.setLength(0);
          }
          currentStyle = currentStyle.withColor(TextColor.fromRgb(rgb));
          i += 6; // Пропускаем весь hex-код
          continue;
        } catch (NumberFormatException ignored) {
          // Невалидный hex — просто продолжаем как обычный текст
        }
      }

      // § или & форматирование
      if ((c == '&' || c == '§') && i + 1 < text.length()) {
        char formatCode = text.charAt(i + 1);
        ChatFormatting formatting = getChatFormattingByCode(formatCode);
        if (formatting != null) {
          // Добавляем накопленный буфер
          if (!buffer.isEmpty()) {
            component.append(Component.literal(buffer.toString()).withStyle(currentStyle));
            buffer.setLength(0);
          }

          if (formatting == ChatFormatting.RESET) {
            currentStyle = Style.EMPTY;
          } else if (formatting.isColor()) {
            currentStyle = currentStyle.withColor(formatting.getColor());
          } else {
            currentStyle = currentStyle.applyFormat(formatting);
          }

          i++; // Пропускаем код форматирования
          continue;
        }
      }

      buffer.append(c);
    }

    // Добавляем оставшийся текст
    if (!buffer.isEmpty()) {
      component.append(Component.literal(buffer.toString()).withStyle(currentStyle));
    }

    return component;
  }


  public static ChatFormatting getChatFormattingByCode(char code) {
    return switch (code) {
      case '0' -> ChatFormatting.BLACK;
      case '1' -> ChatFormatting.DARK_BLUE;
      case '2' -> ChatFormatting.DARK_GREEN;
      case '3' -> ChatFormatting.DARK_AQUA;
      case '4' -> ChatFormatting.DARK_RED;
      case '5' -> ChatFormatting.DARK_PURPLE;
      case '6' -> ChatFormatting.GOLD;
      case '7' -> ChatFormatting.GRAY;
      case '8' -> ChatFormatting.DARK_GRAY;
      case '9' -> ChatFormatting.BLUE;
      case 'a' -> ChatFormatting.GREEN;
      case 'b' -> ChatFormatting.AQUA;
      case 'c' -> ChatFormatting.RED;
      case 'd' -> ChatFormatting.LIGHT_PURPLE;
      case 'e' -> ChatFormatting.YELLOW;
      case 'f' -> ChatFormatting.WHITE;
      case 'k' -> ChatFormatting.OBFUSCATED;
      case 'l' -> ChatFormatting.BOLD;
      case 'm' -> ChatFormatting.STRIKETHROUGH;
      case 'n' -> ChatFormatting.UNDERLINE;
      case 'o' -> ChatFormatting.ITALIC;
      case 'r' -> ChatFormatting.RESET;
      default -> ChatFormatting.WHITE; // Если код не распознан
    };
  }
}
