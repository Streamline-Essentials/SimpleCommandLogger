package host.plas.managers;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessage;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import host.plas.SimpleLogger;
import host.plas.config.WebhookSetup;
import host.plas.configs.ConfiguredChatChannel;
import lombok.Getter;
import lombok.Setter;
import singularity.data.console.CosmicSender;
import singularity.modules.ModuleUtils;

import java.util.concurrent.ConcurrentSkipListMap;

public class WebhookManager {
    @Getter @Setter
    private static ConcurrentSkipListMap<String, WebhookClient> rawClients;

    public static WebhookClient getClient(String url) {
        if (rawClients == null) rawClients = new ConcurrentSkipListMap<>();

        WebhookClient rawClient = rawClients.get(url);
        if (rawClient == null) {
            WebhookClientBuilder builder = new WebhookClientBuilder(url);
            builder.setThreadFactory((job) -> {
                Thread thread = new Thread(job);
                thread.setName("SCL-Webhook-Thread");
                thread.setDaemon(true);
                return thread;
            });
            builder.setWait(true);
            rawClient = builder.build();

            rawClients.put(url, rawClient);

            SimpleLogger.getInstance().logInfo("Created WebhookClient.");
        }

        return rawClient;
    }

    public static void sendWebhookText(CosmicSender sender, String message) {
        boolean isCommand = message.startsWith("/");

        SimpleLogger.getDiscordConfig().getWebookSetups().forEach(setup -> {
            try {
                if (setup.getType() == WebhookSetup.WebhookType.GLOBAL) {
                    WebhookClient client = getClient(setup.getUrl());
                    if (isCommand && setup.getCommands().isEnabled()) {
                        sendWebhook(client, setup.getCommands(), sender, message);
                    } else if (! isCommand && setup.getChat().isEnabled()) {
                        sendWebhook(client, setup.getChat(), sender, message);
                    }
                } else {
                    if (sender.getServerName() != null && sender.getServerName().equalsIgnoreCase(setup.getIdentifier())) {
                        WebhookClient client = getClient(setup.getUrl());
                        if (isCommand && setup.getCommands().isEnabled()) {
                            sendWebhook(client, setup.getCommands(), sender, message);
                        } else if (! isCommand && setup.getChat().isEnabled()) {
                            sendWebhook(client, setup.getChat(), sender, message);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendWebhook(WebhookClient client, WebhookSetup.DifferedSetup setup, CosmicSender sender, String message) {
        if (setup == null) {
            SimpleLogger.getInstance().logInfo("WebhookSetup is null.");
            return;
        }

        if (setup.getPlain().isEnabled()) {
            String name = setup.getPlain().getName();
            name = ModuleUtils.replacePlaceholders(sender, name);

            String content = setup.getPlain().getContent();
            content = ModuleUtils.replacePlaceholders(sender, content.replace("%content%", message));

            String avatar = setup.getPlain().getPicture()
                    .replace("%streamline_user_uuid%", sender.getUuid());
            avatar = ModuleUtils.replacePlaceholders(sender, avatar);

            WebhookMessage webhookMessage = new WebhookMessageBuilder()
                    .setUsername(name)
                    .setContent(content)
                    .setAvatarUrl(avatar)
                    .build();

            client.send(webhookMessage);
        }

        if (setup.getEmbed().isEnabled()) {
            int color = setup.getEmbed().getColor();
            String title = setup.getEmbed().getTitle();
            title = ModuleUtils.replacePlaceholders(sender, title);

            String description = setup.getEmbed().getDescription();
            description = ModuleUtils.replacePlaceholders(sender, description.replace("%content%", message));

            String avatar = setup.getEmbed().getPicture()
                    .replace("%streamline_user_uuid%", sender.getUuid());
            avatar = ModuleUtils.replacePlaceholders(sender, avatar);

            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setColor(color)
                    .setTitle(new WebhookEmbed.EmbedTitle(title, null))
                    .setDescription(description)
                    .setFooter(new WebhookEmbed.EmbedFooter("Made possible by StreamlineCore.", avatar))
                    .build();

            client.send(embed);
        }
    }

    public static void sendChannelMessage(ConfiguredChatChannel channel, CosmicSender sender, String message) {
        SimpleLogger.getDiscordConfig().getMessageChannelSetups().forEach(setup -> {
            try {
                if (setup.getIdentifier().equals(channel.getIdentifier())) {
                    WebhookClient client = getClient(setup.getUrl());
                    if (setup.getChat().isEnabled()) sendWebhook(client, setup.getChat(), sender, message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
