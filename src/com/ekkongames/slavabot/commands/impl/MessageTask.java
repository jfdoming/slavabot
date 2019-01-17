package com.ekkongames.slavabot.commands.impl;

import com.ekkongames.jdacbl.utils.BotUtils;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.TimerTask;

/**
 * Assignment:
 * Author: Julian Dominguez-Schatz
 * Date: 2018-02-28
 * Description:
 */
public class MessageTask extends TimerTask {
    private final MessageReceivedEvent event;
    private final String message;

    public MessageTask(MessageReceivedEvent event, String message) {
        this.event = event;
        this.message = message;
    }

    public void run() {
        MessageReceivedEvent currentEvent = BotUtils.getEvent();
        BotUtils.begin(event);
        BotUtils.sendPlainMessage(message);
        BotUtils.end();
        if (currentEvent != null) {
            BotUtils.begin(currentEvent);
        }
    }
}
