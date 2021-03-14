package com.ekkongames.slavabot.commands.impl.role;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandGroup;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

/**
 * Created by Dolphish on 2016-10-29.
 */
public class RoleChange extends Command {

    private final CommandGroup subCommands;

    public RoleChange() {
        super(new CommandInfo.Builder()
                .names("change", "modify", "c", "m")
                .build());

        this.subCommands = new CommandGroup.Builder()
                .add(new RoleChangeMentionable())
                .add(new RoleChangeColour())
                .add(new RoleChangeName())
                .build();
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify an action to perform");
            return;
        }

        input.discardToken(0);
        subCommands.exec(input);
    }
}
