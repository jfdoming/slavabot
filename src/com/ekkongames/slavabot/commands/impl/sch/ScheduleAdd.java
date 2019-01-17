package com.ekkongames.slavabot.commands.impl.sch;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.slavabot.commands.impl.MessageTask;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.TimerTask;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class ScheduleAdd extends Command {

    private final Scheduler state;

    public ScheduleAdd(Scheduler state) {
        super(new CommandInfo.Builder()
                .names("add", "+")
                .summary("schedules a particular message to recur in chat periodically")
                .usage("delay id word1 word2...")
                .build()
        );

        this.state = state;
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a period
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a period");
            return;
        }

        int delay;
        try {
            delay = Integer.parseInt(input.getToken(1));
        } catch (NumberFormatException e) {
            BotUtils.sendMessage("The period you specified is not a number");
            return;
        }

        // make sure the user specified an id
        if (input.getTokenCount() < 3) {
            BotUtils.sendMessage("You must specify an id");
            return;
        }
        String messageId = input.getToken(2);

        // make sure the user specified a message
        if (input.getTokenCount() < 4) {
            BotUtils.sendMessage("You must specify a message");
            return;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 3; i < input.getTokenCount(); i++) {
            if (i > 3) {
                messageBuilder.append(' ');
            }

            messageBuilder.append(input.getToken(i));
        }
        final String message = messageBuilder.toString();

        // schedule the message
        final MessageReceivedEvent event = BotUtils.getEvent();
        TimerTask taskToSchedule = new MessageTask(event, message);
        state.getScheduler().scheduleAtFixedRate(taskToSchedule, delay, delay);
        state.getTasks().put(messageId, taskToSchedule);
        Scheduler.getPreferences().put(messageId, message);

        BotUtils.sendMessage("Scheduled the message \"" + message + "\" every " + delay + " ms");
        BotUtils.sendPlainMessage("Your message id is \"" + messageId + "\".");
    }
}
