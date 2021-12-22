package io.github.mg138.monofont.command

import PUACodes
import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.context.CommandContext
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.argument
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.ClickEvent
import net.minecraft.text.HoverEvent
import net.minecraft.text.LiteralText
import net.minecraft.text.Style

object IconTable {
    private fun listIcons(context: CommandContext<ServerCommandSource>): Int {
        val source = context.source

        val page = try {
            IntegerArgumentType.getInteger(context, "page")
        } catch (e: Exception) {
            0
        }

        for (i in 0..16) {
            val line = LiteralText("")
            for (j in 0..16) {
                val n = j + (i * 16) + (page * 256) + PUACodes.start

                line.append(LiteralText(Character.toString(n)).apply {
                    style = Style.EMPTY
                        .withHoverEvent(HoverEvent(HoverEvent.Action.SHOW_TEXT, LiteralText("Click to copy!")))
                        .withClickEvent(ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, this.rawString))
                })
            }
            line.append("\n")
            source.sendFeedback(line, false)
        }

        return Command.SINGLE_SUCCESS
    }

    fun register() {
        CommandRegistrationCallback.EVENT.register { dispatcher, _ ->
            dispatcher.register(
                literal("mono")
                    .then(
                        literal("icons")
                            .then(
                                argument("page", IntegerArgumentType.integer(0, 24))
                                    .executes(this::listIcons)
                            )
                    )
            )
        }
    }
}