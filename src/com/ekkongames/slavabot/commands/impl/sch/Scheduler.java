package com.ekkongames.slavabot.commands.impl.sch;

import com.ekkongames.slavabot.commands.impl.MessageTask;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;

/**
 * Assignment:
 * Author: Julian Dominguez-Schatz
 * Date: 2018-02-28
 * Description:
 */
public class Scheduler {

    // persistent preferences
    private static final Preferences preferences = Preferences.userNodeForPackage(Scheduler.class);

    private Timer scheduler;
    private HashMap<String, TimerTask> tasks;

    public static Preferences getPreferences() {
        return preferences;
    }

    public Timer getScheduler() {
        return scheduler;
    }

    public HashMap<String, TimerTask> getTasks() {
        return tasks;
    }

    public void schedule(MessageReceivedEvent event, String id, String message, long delay) {
        if (scheduler != null && tasks != null) {
            TimerTask task = new MessageTask(event, message);
            scheduler.scheduleAtFixedRate(task, delay, delay);
            tasks.put(id, task);
        }
    }

    public void scheduleAndStore(MessageReceivedEvent event, String id, String message, long delay) {
        if (scheduler != null && tasks != null) {
            TimerTask task = new MessageTask(event, message);
            scheduler.scheduleAtFixedRate(task, delay, delay);
            tasks.put(id, task);
            preferences.put(id, message);
        }
    }

    public void onLogin() {
        scheduler = new Timer();
        tasks = new HashMap<>();
    }

    public void onLogout() {
        scheduler.cancel();
        scheduler.purge();
        scheduler = null;
        tasks = null;
    }
}
