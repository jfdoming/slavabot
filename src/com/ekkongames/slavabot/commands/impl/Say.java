package com.ekkongames.slavabot.commands.impl;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Say extends Command {

    public Say() {
        super(new CommandInfo.Builder()
                .names("say")
                .summary("say the specified text")
                .usage("message")
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a message
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a message to say");
            return;
        }

        // send the message
        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 1; i < input.getTokenCount(); i++) {
            messageBuilder.append(input.getToken(i)).append(" ");
        }
        BotUtils.sendPlainMessage(messageBuilder.toString());
    }
}
