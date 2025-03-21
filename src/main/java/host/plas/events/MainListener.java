package host.plas.events;

import host.plas.SimpleLogger;
import host.plas.managers.WebhookManager;
import singularity.events.server.CosmicChatEvent;
import tv.quaint.events.BaseEventHandler;
import tv.quaint.events.BaseEventListener;
import tv.quaint.events.processing.BaseEventPriority;
import tv.quaint.events.processing.BaseProcessor;

public class MainListener implements BaseEventListener {
    public MainListener() {
        BaseEventHandler.bake(this, SimpleLogger.getInstance());
    }

    @BaseProcessor(priority = BaseEventPriority.HIGHEST)
    public void onMessage(CosmicChatEvent event) {
        WebhookManager.sendWebhookText(event.getSender(), event.getMessage());
    }
}
