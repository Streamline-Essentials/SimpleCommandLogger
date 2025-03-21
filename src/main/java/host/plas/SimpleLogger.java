package host.plas;

import host.plas.config.DiscordConfig;
import host.plas.events.MainListener;
import host.plas.external.MessagingAdapter;
import host.plas.managers.WebhookManager;
import host.plas.ratapi.LoggerExpansion;
import lombok.Getter;
import lombok.Setter;
import org.pf4j.PluginWrapper;
import singularity.modules.SimpleModule;

import java.util.ArrayList;
import java.util.List;

public class SimpleLogger extends SimpleModule {
    @Getter @Setter
    private static SimpleLogger instance;

    @Getter @Setter
    private static LoggerExpansion loggerExpansion;

    @Getter @Setter
    private static DiscordConfig discordConfig;

    @Getter @Setter
    private static MainListener mainListener;

    @Getter @Setter
    private static MessagingAdapter messagingAdapter;

    public SimpleLogger(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void registerCommands() {
        setCommands(new ArrayList<>());
    }

    @Override
    public void onEnable() {
        instance = this;

        discordConfig = new DiscordConfig();

        loggerExpansion = new LoggerExpansion();

        mainListener = new MainListener();

        messagingAdapter = new MessagingAdapter();
    }

    @Override
    public void onDisable() {
        WebhookManager.getRawClients().clear();
    }
}
