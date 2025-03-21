package host.plas.config;

import host.plas.SimpleLogger;
import tv.quaint.storage.resources.flat.simple.SimpleConfiguration;

import java.util.concurrent.ConcurrentSkipListSet;

public class DiscordConfig extends SimpleConfiguration {
    public DiscordConfig() {
        super("webhooks.yml", SimpleLogger.getInstance(), true);
    }

    @Override
    public void init() {
        getGlobalWebhookUrl();
        getGlobalCommandsPlainEnabled();
        getGlobalCommandsPlainName();
        getGlobalCommandsPlainPicture();
        getGlobalCommandsPlainContent();

        getGlobalCommandsEnabled();

        getGlobalCommandsEmbedEnabled();
        getGlobalCommandsEmbedColor();
        getGlobalCommandsEmbedTitle();
        getGlobalCommandsEmbedDescription();
        getGlobalCommandsEmbedPicture();

        getGlobalChatPlainEnabled();
        getGlobalChatPlainName();
        getGlobalChatPlainPicture();
        getGlobalChatPlainContent();

        getGlobalChatEnabled();

        getGlobalChatEmbedEnabled();
        getGlobalChatEmbedColor();
        getGlobalChatEmbedTitle();
        getGlobalChatEmbedDescription();
        getGlobalChatEmbedPicture();
    }

    public String getGlobalWebhookUrl() {
        reloadResource();

        return getOrSetDefault("global.url", "WEBHOOK_URL");
    }

    public boolean getGlobalCommandsEnabled() {
        reloadResource();

        return getOrSetDefault("global.commands.enabled", true);
    }

    public boolean getGlobalCommandsPlainEnabled() {
        reloadResource();

        return getOrSetDefault("global.commands.plain.enabled", true);
    }

    public String getGlobalCommandsPlainName() {
        reloadResource();

        return getOrSetDefault("global.commands.plain.name", "%streamline_user_absolute%");
    }

    public String getGlobalCommandsPlainPicture() {
        reloadResource();

        return getOrSetDefault("global.commands.plain.picture", "https://crafatar.com/avatars/%streamline_user_uuid%?size=128&overlay");
    }

    public String getGlobalCommandsPlainContent() {
        reloadResource();

        return getOrSetDefault("global.commands.plain.content", "%this_command%");
    }

    public boolean getGlobalCommandsEmbedEnabled() {
        reloadResource();

        return getOrSetDefault("global.commands.embed.enabled", false);
    }

    public int getGlobalCommandsEmbedColor() {
        reloadResource();

        return getOrSetDefault("global.commands.embed.color", 0);
    }

    public String getGlobalCommandsEmbedTitle() {
        reloadResource();

        return getOrSetDefault("global.commands.embed.title", "%streamline_user_absolute%");
    }

    public String getGlobalCommandsEmbedDescription() {
        reloadResource();

        return getOrSetDefault("global.commands.embed.description", "%this_command%");
    }

    public String getGlobalCommandsEmbedPicture() {
        reloadResource();

        return getOrSetDefault("global.commands.embed.picture", "https://crafatar.com/avatars/%streamline_user_uuid%?size=128&overlay");
    }

    public boolean getGlobalChatEnabled() {
        reloadResource();

        return getOrSetDefault("global.chat.enabled", true);
    }

    public boolean getGlobalChatPlainEnabled() {
        reloadResource();

        return getOrSetDefault("global.chat.plain.enabled", true);
    }

    public String getGlobalChatPlainName() {
        reloadResource();

        return getOrSetDefault("global.chat.plain.name", "%streamline_user_absolute%");
    }

    public String getGlobalChatPlainPicture() {
        reloadResource();

        return getOrSetDefault("global.chat.plain.picture", "https://crafatar.com/avatars/%streamline_user_uuid%?size=128&overlay");
    }

    public String getGlobalChatPlainContent() {
        reloadResource();

        return getOrSetDefault("global.chat.plain.content", "%this_command%");
    }

    public boolean getGlobalChatEmbedEnabled() {
        reloadResource();

        return getOrSetDefault("global.chat.embed.enabled", false);
    }

    public int getGlobalChatEmbedColor() {
        reloadResource();

        return getOrSetDefault("global.chat.embed.color", 0);
    }

    public String getGlobalChatEmbedTitle() {
        reloadResource();

        return getOrSetDefault("global.chat.embed.title", "%streamline_user_absolute%");
    }

    public String getGlobalChatEmbedDescription() {
        reloadResource();

        return getOrSetDefault("global.chat.embed.description", "%this_command%");
    }

    public String getGlobalChatEmbedPicture() {
        reloadResource();

        return getOrSetDefault("global.chat.embed.picture", "https://crafatar.com/avatars/%streamline_user_uuid%?size=128&overlay");
    }

    public WebhookSetup getGlobalSetup() {
        reloadResource();

        return new WebhookSetup(
                WebhookSetup.WebhookType.GLOBAL,
                "PROXY",
                getGlobalWebhookUrl(),
                new WebhookSetup.DifferedSetup(
                        getGlobalCommandsEnabled(),
                        WebhookSetup.DifferedSetup.DifferedType.COMMAND,
                        new WebhookSetup.PlainHook(
                                getGlobalCommandsPlainEnabled(),
                                getGlobalCommandsPlainName(),
                                getGlobalCommandsPlainPicture(),
                                getGlobalCommandsPlainContent()
                        ),
                        new WebhookSetup.EmbedHook(
                                getGlobalCommandsEmbedEnabled(),
                                getGlobalCommandsEmbedColor(),
                                getGlobalCommandsEmbedTitle(),
                                getGlobalCommandsEmbedDescription(),
                                getGlobalCommandsEmbedPicture()
                        )
                ),
                new WebhookSetup.DifferedSetup(
                        getGlobalChatEnabled(),
                        WebhookSetup.DifferedSetup.DifferedType.CHAT,
                        new WebhookSetup.PlainHook(
                                getGlobalChatPlainEnabled(),
                                getGlobalChatPlainName(),
                                getGlobalChatPlainPicture(),
                                getGlobalChatPlainContent()
                        ),
                        new WebhookSetup.EmbedHook(
                                getGlobalChatEmbedEnabled(),
                                getGlobalChatEmbedColor(),
                                getGlobalChatEmbedTitle(),
                                getGlobalChatEmbedDescription(),
                                getGlobalChatEmbedPicture()
                        )
                )
        );
    }

    public String getServerWebhookUrl(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".url", "WEBHOOK_URL");
    }

    public boolean getServerCommandsEnabled(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".commands.enabled", true);
    }

    public boolean getServerCommandsPlainEnabled(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".commands.plain.enabled", true);
    }

    public String getServerCommandsPlainName(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".commands.plain.name", "%streamline_user_absolute%");
    }

    public String getServerCommandsPlainPicture(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".commands.plain.picture", "https://crafatar.com/avatars/%streamline_user_uuid%?size=128&overlay");
    }

    public String getServerCommandsPlainContent(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".commands.plain.content", "%this_command%");
    }

    public boolean getServerCommandsEmbedEnabled(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".commands.embed.enabled", false);
    }

    public int getServerCommandsEmbedColor(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".commands.embed.color", 0);
    }

    public String getServerCommandsEmbedTitle(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".commands.embed.title", "%streamline_user_absolute%");
    }

    public String getServerCommandsEmbedDescription(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".commands.embed.description", "%this_command%");
    }

    public String getServerCommandsEmbedPicture(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".commands.embed.picture", "https://crafatar.com/avatars/%streamline_user_uuid%?size=128&overlay");
    }

    public boolean getServerChatEnabled(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".chat.enabled", true);
    }

    public boolean getServerChatPlainEnabled(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".chat.plain.enabled", true);
    }

    public String getServerChatPlainName(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".chat.plain.name", "%streamline_user_absolute%");
    }

    public String getServerChatPlainPicture(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".chat.plain.picture", "https://crafatar.com/avatars/%streamline_user_uuid%?size=128&overlay");
    }

    public String getServerChatPlainContent(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".chat.plain.content", "%this_command%");
    }

    public boolean getServerChatEmbedEnabled(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".chat.embed.enabled", false);
    }

    public int getServerChatEmbedColor(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".chat.embed.color", 0);
    }

    public String getServerChatEmbedTitle(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".chat.embed.title", "%streamline_user_absolute%");
    }

    public String getServerChatEmbedDescription(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".chat.embed.description", "%this_command%");
    }

    public String getServerChatEmbedPicture(String server) {
        reloadResource();

        return getOrSetDefault("servers." + server + ".chat.embed.picture", "https://crafatar.com/avatars/%streamline_user_uuid%?size=128&overlay");
    }

    public WebhookSetup getServerSetup(String server) {
        return new WebhookSetup(
                WebhookSetup.WebhookType.SERVER,
                server,
                getServerWebhookUrl(server),
                new WebhookSetup.DifferedSetup(
                        getServerCommandsEnabled(server),
                        WebhookSetup.DifferedSetup.DifferedType.COMMAND,
                        new WebhookSetup.PlainHook(
                                getServerCommandsPlainEnabled(server),
                                getServerCommandsPlainName(server),
                                getServerCommandsPlainPicture(server),
                                getServerCommandsPlainContent(server)
                        ),
                        new WebhookSetup.EmbedHook(
                                getServerCommandsEmbedEnabled(server),
                                getServerCommandsEmbedColor(server),
                                getServerCommandsEmbedTitle(server),
                                getServerCommandsEmbedDescription(server),
                                getServerCommandsEmbedPicture(server)
                        )
                ),
                new WebhookSetup.DifferedSetup(
                        getServerChatEnabled(server),
                        WebhookSetup.DifferedSetup.DifferedType.CHAT,
                        new WebhookSetup.PlainHook(
                                getServerChatPlainEnabled(server),
                                getServerChatPlainName(server),
                                getServerChatPlainPicture(server),
                                getServerChatPlainContent(server)
                        ),
                        new WebhookSetup.EmbedHook(
                                getServerChatEmbedEnabled(server),
                                getServerChatEmbedColor(server),
                                getServerChatEmbedTitle(server),
                                getServerChatEmbedDescription(server),
                                getServerChatEmbedPicture(server)
                        )
                )
        );
    }

    public ConcurrentSkipListSet<WebhookSetup> getServerSetups() {
        reloadResource();

        ConcurrentSkipListSet<WebhookSetup> r = new ConcurrentSkipListSet<>();

        singleLayerKeySet("servers").forEach(s -> {
            try {
                r.add(getServerSetup(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return r;
    }

    public ConcurrentSkipListSet<WebhookSetup> getWebookSetups() {
        ConcurrentSkipListSet<WebhookSetup> r = new ConcurrentSkipListSet<>();

        r.addAll(getServerSetups());
        r.add(getGlobalSetup());

        return r;
    }

    public String getMessageChannelWebhookUrl(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".url", "WEBHOOK_URL");
    }

    public boolean getMessageChannelChatEnabled(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".chat.enabled", true);
    }

    public boolean getMessageChannelChatPlainEnabled(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".chat.plain.enabled", true);
    }

    public String getMessageChannelChatPlainName(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".chat.plain.name", "%streamline_user_absolute%");
    }

    public String getMessageChannelChatPlainPicture(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".chat.plain.picture", "https://crafatar.com/avatars/%streamline_user_uuid%?size=128&overlay");
    }

    public String getMessageChannelChatPlainContent(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".chat.plain.content", "%this_command%");
    }

    public boolean getMessageChannelChatEmbedEnabled(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".chat.embed.enabled", false);
    }

    public int getMessageChannelChatEmbedColor(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".chat.embed.color", 0);
    }

    public String getMessageChannelChatEmbedTitle(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".chat.embed.title", "%streamline_user_absolute%");
    }

    public String getMessageChannelChatEmbedDescription(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".chat.embed.description", "%this_command%");
    }

    public String getMessageChannelChatEmbedPicture(String messageChannel) {
        reloadResource();

        return getOrSetDefault("message-channels." + messageChannel + ".chat.embed.picture", "https://crafatar.com/avatars/%streamline_user_uuid%?size=128&overlay");
    }

    public WebhookSetup getMessageChannelSetup(String messageChannel) {
        return new WebhookSetup(
                WebhookSetup.WebhookType.CHANNEL,
                messageChannel,
                getMessageChannelWebhookUrl(messageChannel),
                null,
                new WebhookSetup.DifferedSetup(
                        getMessageChannelChatEnabled(messageChannel),
                        WebhookSetup.DifferedSetup.DifferedType.CHAT,
                        new WebhookSetup.PlainHook(
                                getMessageChannelChatPlainEnabled(messageChannel),
                                getMessageChannelChatPlainName(messageChannel),
                                getMessageChannelChatPlainPicture(messageChannel),
                                getMessageChannelChatPlainContent(messageChannel)
                        ),
                        new WebhookSetup.EmbedHook(
                                getMessageChannelChatEmbedEnabled(messageChannel),
                                getMessageChannelChatEmbedColor(messageChannel),
                                getMessageChannelChatEmbedTitle(messageChannel),
                                getMessageChannelChatEmbedDescription(messageChannel),
                                getMessageChannelChatEmbedPicture(messageChannel)
                        )
                )
        );
    }

    public ConcurrentSkipListSet<WebhookSetup> getMessageChannelSetups() {
        reloadResource();

        ConcurrentSkipListSet<WebhookSetup> r = new ConcurrentSkipListSet<>();

        singleLayerKeySet("message-channels").forEach(s -> {
            try {
                r.add(getMessageChannelSetup(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return r;
    }
}
