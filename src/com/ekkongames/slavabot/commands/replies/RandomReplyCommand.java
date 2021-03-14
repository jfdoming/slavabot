package com.ekkongames.slavabot.commands.replies;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.slavabot.utils.UnfairRandomIterator;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class RandomReplyCommand extends Command {

    private static final String[] REPLIES = {
            // Snork
            "_SO FAST_",
            "Bombeat",
            "<:bb1:804873337289441390><:bb2:804873337326141445><:bb3:804873337393250344><:bb4:804873337385910332>",
            "\"Sticky!\" - Snork Gunk 2k19",

            // Max
            "_F I S H - Y_",

            // Lonestar
            "Son of a - aehhhhhhh...",

            // Misc
            "Did I stutter?",
    };

    private static final UnfairRandomIterator<String> REPLY_ITERATOR = new UnfairRandomIterator<>(REPLIES);

    private static final float REPLY_PROBABILITY = 0.02f;

    public RandomReplyCommand() {
        super(new CommandInfo.Builder()
                .visible(false)
                .names("<none>", "")
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        if (Math.random() < REPLY_PROBABILITY) {
            BotUtils.sendPlainMessage(REPLY_ITERATOR.next());
        }
    }

}
