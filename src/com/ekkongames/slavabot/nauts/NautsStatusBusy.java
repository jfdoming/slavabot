package com.ekkongames.slavabot.nauts;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class NautsStatusBusy extends Command {

    private Nauts parent;

    public NautsStatusBusy(Nauts parent) {
        super(
                new CommandInfo.Builder()
                        .names("busy", "dbm")
                        .summary("marks the author as busy and not available for an Awesomenauts game")
                        .build()
        );

        this.parent = parent;
    }

    @Override
    public void exec(CommandInput input) {
        parent.onlineMembers.remove(input.getSender());
        BotUtils.sendMessage("You've been marked as busy");
    }

}
