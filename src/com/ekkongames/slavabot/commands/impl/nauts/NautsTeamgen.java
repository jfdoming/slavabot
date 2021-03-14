package com.ekkongames.slavabot.commands.impl.nauts;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import net.dv8tion.jda.api.entities.Member;

import java.util.Collections;
import java.util.List;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class NautsTeamgen extends Command {

    private final Nauts parent;

    public NautsTeamgen(Nauts parent) {
        super(
                new CommandInfo.Builder()
                        .names("teamgen")
                        .summary("randomly generate Awesomenauts teams")
                        .description("These teams are based on the online users with the Awesomenauts role, " +
                                "and additional members can be included by mentioning them as parameters.")
                        .usage("[@user1 @user2 ...]")
                        .build()
        );

        this.parent = parent;
    }

    private void makePlayerString(StringBuilder messageBuilder, List<Member> players, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (i == endIndex - 1) {
                messageBuilder.append(" and");
            } else if (i > startIndex) {
                messageBuilder.append(",");
            }
            messageBuilder.append(" **");
            messageBuilder.append(players.get(i).getEffectiveName());
            messageBuilder.append("**");
        }
    }

    @Override
    public void exec(CommandInput input) {
        input.discardToken(0);
        List<Member> awesomenautsPlayers = parent.getOnlinePlayers(input);
        int playerCount = awesomenautsPlayers.size();

        StringBuilder messageBuilder = new StringBuilder();

        if (awesomenautsPlayers.size() < 4) {
            // There are only enough members online to create a single team.
            messageBuilder.append("The team members are");
            makePlayerString(messageBuilder, awesomenautsPlayers, 0, playerCount);
        } else {
            Collections.shuffle(awesomenautsPlayers);

            messageBuilder.append("Team 1 is");
            makePlayerString(messageBuilder, awesomenautsPlayers, 0, playerCount / 2);
            messageBuilder.append(", and team 2 is");
            makePlayerString(messageBuilder, awesomenautsPlayers, playerCount / 2, playerCount);
        }
        messageBuilder.append(".");
        BotUtils.sendPlainMessage(messageBuilder.toString());
    }

}
