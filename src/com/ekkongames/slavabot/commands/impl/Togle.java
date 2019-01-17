package com.ekkongames.slavabot.commands.impl;

import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;

/**
 * Used to cache a guild, for cross server admin.
 *
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Togle extends Command {

    public Togle() {
        super(new CommandInfo.Builder()
                .names("togle")
                .visible(false)
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        BotUtils.toggleStaticGuild();
    }

}
