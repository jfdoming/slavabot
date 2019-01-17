package com.ekkongames.slavabot.nauts;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class NautsStatusLTP extends Command {

    private Nauts parent;

    public NautsStatusLTP(Nauts parent) {
        super(
                new CommandInfo.Builder()
                        .names("ltp", "ready")
                        .summary("marks the author as looking for an Awesomenauts game")
                        .build()
        );

        this.parent = parent;
    }

    @Override
    public void exec(CommandInput input) {
        parent.onlineMembers.add(input.getSender());
        BotUtils.sendMessage("You've been marked as looking to play");
    }

}
