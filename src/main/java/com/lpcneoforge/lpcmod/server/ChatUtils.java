package com.lpcneoforge.lpcmod.server;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

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
        }

        return component; // Если цвет не определен, возвращаем оригинальный компонент
    }



    public static MutableComponent parseFormattedText(String text) {
        MutableComponent component = Component.literal(""); // Корневой компонент
        StringBuilder buffer = new StringBuilder(); // Временный буфер для текста
        ChatFormatting currentFormat = ChatFormatting.WHITE; // Начальный цвет

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '&' && i + 1 < text.length()) {
                // Применяем новый формат
                if (!buffer.isEmpty()) {
                    component.append(Component.literal(buffer.toString()).withStyle(currentFormat));
                    buffer.setLength(0); // Очистка буфера
                }

                char formatCode = text.charAt(++i);
                currentFormat = getChatFormattingByCode(formatCode);
            } else {
                buffer.append(c);
            }
        }

        // Добавляем оставшийся текст
        if (!buffer.isEmpty()) {
            component.append(Component.literal(buffer.toString()).withStyle(currentFormat));
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
