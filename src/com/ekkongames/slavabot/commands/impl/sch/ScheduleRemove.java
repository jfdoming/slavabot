package com.ekkongames.slavabot.commands.impl.sch;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

import java.util.TimerTask;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class ScheduleRemove extends Command {

    private final Scheduler state;

    public ScheduleRemove(Scheduler state) {
        super(new CommandInfo.Builder()
                .names("remove", "-")
                .summary("deschedules a particular message from recurring in chat periodically")
                .usage("id")
                .build()
        );

        this.state = state;
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified an id
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify an id");
            return;
        }
        String messageId = input.getToken(1);

        // deschedule the message
        TimerTask task = state.getTasks().get(messageId);
        if (task == null) {
            BotUtils.sendMessage("No message found with id \"" + messageId + "\"");
            return;
        }

        task.cancel();
        Scheduler.getPreferences().remove(messageId);

        BotUtils.sendMessage("Decheduled the message with id \"" + messageId + "\"");
    }
}
