package com.ekkongames.slavabot.commands.impl.music;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandGroup;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

public class Music extends Command {

    public Music() {
        super(
                new CommandInfo.Builder()
                        .names("music")
                        .build(),
                new CommandGroup.Builder()
                        .add(new MusicPlay())
                        .add(new MusicPause())
                        .add(new MusicResume())
                        .add(new MusicSkip())
                        .add(new MusicStop())
                        .add(new MusicLeave())
                        .build()
        );
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
