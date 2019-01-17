package com.ekkongames.slavabot.commands.impl;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.jdacbl.utils.PrimitiveUtils;
import com.ekkongames.slavabot.PermissionConstants;
import net.dv8tion.jda.core.entities.User;

import java.util.prefs.Preferences;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Warn extends Command {

    public Warn() {
        super(new CommandInfo.Builder()
                .names("warn", "w")
                .summary("warn a user about being banished")
                .description("The target user is given 3 warns before they are "
                        + "automatically banished."
                )
                .usage("@user")
                .auth(PermissionConstants.OWNER_ROLE)
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a user to warn");
            return;
        }
        User target = PrimitiveUtils.get(input.getMentionedUsers());
        if (target == null) {
            BotUtils.sendMessage("No user was mentioned, or the mentioned user doesn't ");
            return;
        }

        Preferences preferences = Warns.getPreferences();
        int warns = preferences.getInt("warns-" + target.getId(), 0) + 1;

        if (warns < 3) {
            BotUtils.sendPrivateMessage(target, "This is a warning to STFU or get banished.");
            BotUtils.sendPrivateMessage(target, "You have " + (3 - warns)
                    + " more warning" + (warns == 2 ? "" : "s")
                    + " before you are banished.");
        } else {
            BotUtils.sendPrivateMessage(target, "You did not STFU; you are now banished.");
            warns = 0;

            BotUtils.addRoleToUser(target, Banish.BANISH_ROLE);
            BotUtils.moveUserToVoiceChannel(target, Banish.BANISH_VC);
        }

        preferences.putInt("warns-" + target.getId(), warns);
    }

}
