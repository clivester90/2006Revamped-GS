package com.rebotted.game.dialogues;

import com.rebotted.game.players.Player;

import java.util.function.Consumer;

public class DialogueOption {

    public static DialogueOption nevermind() {
        return new DialogueOption("Never mind.", plr -> plr.getPacketSender().closeAllWindows());
    }

    private final String title;
    private final Consumer<Player> consumer;

    public DialogueOption(String title, Consumer<Player> consumer) {
        this.title = title;
        this.consumer = consumer;
    }

    public String getTitle() {
        return title;
    }

    public Consumer<Player> getConsumer() {
        return consumer;
    }
}
