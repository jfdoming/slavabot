package com.ekkongames.slavabot.commands.impl.sch;

import com.ekkongames.slavabot.commands.impl.MessageTask;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

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

    public HashMap<String, TimerTask> getTasks() {
        return tasks;
    }

    public void schedule(String id, String message, long delay) {
        if (scheduler != null && tasks != null) {
            TimerTask task = new MessageTask(message);
            scheduler.scheduleAtFixedRate(task, delay, delay);
            tasks.put(id, task);
        }
    }

    public void scheduleAndStore(String id, String message, long delay) {
        if (scheduler != null && tasks != null) {
            TimerTask task = new MessageTask(message);
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
