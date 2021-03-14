package com.ekkongames.slavabot.commands.impl.sch;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandGroup;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.slavabot.PermissionConstants;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Schedule extends Command {

    private final Scheduler state;

    public Schedule() {
        super(new CommandInfo.Builder()
                .names("schedule", "sch")
                .summary("schedules a particular message to recur in chat periodically")
                .description("This command will attempt to switch the voice channel of the "
                        + "target user, which **will** fail if the user is not "
                        + "currently in a voice channel. In this case, the user will "
                        + "still be given the role \"Banished\"."
                )
                .usage("init|add delay id word1 word2...")
                .auth(PermissionConstants.MODERATOR_ROLE)
                .build()
        );

        state = new Scheduler();
        children = new CommandGroup.Builder()
                        .add(new ScheduleAdd(state))
                        .add(new ScheduleInit(state))
                        .add(new ScheduleRemove(state))
                        .build();
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified an action
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify an action to perform");
            return;
        }

        input.discardToken(0);
        children.exec(input);
    }

    @Override
    public void onLogin() {
        state.onLogin();
    }

    @Override
    public void onLogout() {
        state.onLogout();
    }

}
