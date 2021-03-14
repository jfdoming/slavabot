package com.ekkongames.slavabot.commands.impl;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.jdacbl.utils.PrimitiveUtils;
import com.ekkongames.slavabot.PermissionConstants;
import net.dv8tion.jda.api.entities.User;

import java.util.prefs.Preferences;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Warns extends Command {

    // persistent preferences
    private static final Preferences preferences = Preferences.userNodeForPackage(Warns.class);

    public Warns() {
        super(new CommandInfo.Builder()
                .names("warns", "ws")
                .summary("perform warn-related operations")
                .description("Valid operations include:\n"
                        + "- check: check the number of warns a user has left before they are banished\n"
                        + "- reset: reset the number of warns a user has left before they are banished to 3"
                )
                .usage("@user")
                .auth(PermissionConstants.OWNER_ROLE)
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a warn operation to perform");
            return;
        }
        if (input.getTokenCount() < 3) {
            BotUtils.sendMessage("You must specify a user to perform an operation on");
            return;
        }
        User target = PrimitiveUtils.get(input.getMentionedUsers());
        if (target == null) {
            BotUtils.sendMessage("No user was mentioned, or the mentioned user doesn't exist");
            return;
        }

        switch (input.getToken(1)) {
            case "check":
                int warns = 3 - preferences.getInt("warns-" + target.getId(), 0);
                BotUtils.sendMessage(target.getAsMention() + " has " + warns + " warns left before they are banished");
                break;
            case "reset":
                preferences.putInt("warns-" + target.getId(), 0);
                BotUtils.sendMessage(target.getAsMention() + " has 3 warns left before they are banished");
                break;
            default:
                BotUtils.sendMessage("Unknown operation");
        }
    }

    public static Preferences getPreferences() {
        return preferences;
    }
}
