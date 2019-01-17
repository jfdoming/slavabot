package com.ekkongames.slavabot.nauts;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandGroup;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import net.dv8tion.jda.core.entities.User;

import java.util.HashSet;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Nauts extends Command {

    HashSet<User> onlineMembers;

    public Nauts() {
        super(
                new CommandInfo.Builder()
                    .names("nauts")
                    .summary("perform various Awesomenauts-related actions")
                    .build()
        );

        this.children = new CommandGroup.Builder()
                .add(new NautsTeamgen(this))
                .add(new NautsStatus(this))
                .add(new NautsLeaderboard())
                .build();

        onlineMembers = new HashSet<>();
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify an action to perform");
            return;
        }

        input.discardToken(0);
        children.exec(input);
    }

}
