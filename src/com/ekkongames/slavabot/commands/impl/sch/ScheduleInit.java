package com.ekkongames.slavabot.commands.impl.sch;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.jdacbl.utils.Log;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class ScheduleInit extends Command {

    private final Scheduler state;

    public ScheduleInit(Scheduler state) {
        super(new CommandInfo.Builder()
                .names("init", "i")
                .summary("initializes the scheduler, running any previously scheduled messages")
                .build()
        );

        this.state = state;
    }

    @Override
    public void exec(CommandInput input) {
        try {
            Preferences prefs = Scheduler.getPreferences();
            String[] ids = prefs.keys();
            for (String id : ids) {
                String storedValue = prefs.get(id, "0\\(no message)");
                String delayString = storedValue.substring(0, storedValue.indexOf("\\"));
                String message = storedValue.substring(storedValue.indexOf("\\") + 1);
                long delay = Long.parseLong(delayString);
                state.schedule(BotUtils.getEvent(), id, message, delay);
            }
        } catch (BackingStoreException e) {
            Log.w("Schedule Init Command", "Failed to locate backing store.");
        }
    }
}
