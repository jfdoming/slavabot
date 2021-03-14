package com.ekkongames.slavabot.commands.impl;

import com.ekkongames.jdacbl.utils.BotUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.TimerTask;

/**
 * Assignment:
 * Author: Julian Dominguez-Schatz
 * Date: 2018-02-28
 * Description:
 */
public class MessageTask extends TimerTask {
    private final String message;
    private final Guild guild;
    private final User author;
    private final MessageChannel messageChannel;

    public MessageTask(String message) {
        this.guild = BotUtils.getGuild();
        this.author = BotUtils.getAuthor();
        this.messageChannel = BotUtils.getMessageChannel();
        this.message = message;
    }

    public void run() {
        Guild currentGuild = BotUtils.getGuild();
        User currentAuthor = BotUtils.getAuthor();
        MessageChannel currentChannel = BotUtils.getMessageChannel();
        BotUtils.begin(guild, author, messageChannel);
        BotUtils.sendPlainMessage(message);
        BotUtils.end();
        if (currentGuild != null || currentAuthor != null || currentChannel != null) {
            BotUtils.begin(currentGuild, currentAuthor, currentChannel);
        }
    }
}
