package com.ekkongames.slavabot.commands.impl;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.slavabot.PermissionConstants;
import net.dv8tion.jda.core.entities.User;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Nickname extends Command {

    public Nickname() {
        super(new CommandInfo.Builder()
                .names("nickname", "nick")
                .usage("@user [newname]")
                .summary("changes the server-local nickname of a user")
                .description("Omit the last parameter to reset a nickname.")
                .auth(PermissionConstants.MODERATOR_ROLE)
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a user to add a role to");
            return;
        }

        // make sure the user specified a valid target
        User target = BotUtils.getTargetUser(input, 1);
        if (target == null) {
            BotUtils.sendMessage("You must mention a user or specify a valid user ID");
            return;
        }

        // make sure the user specified a new name
        if (input.getTokenCount() < 3) {
            BotUtils.setUserNickname(target, "");
        } else {
            BotUtils.setUserNickname(target, input.getToken(2));
        }
    }

}
