package host.plas.config;

import lombok.Getter;
import lombok.Setter;
import tv.quaint.objects.Identifiable;

@Getter @Setter
public class WebhookSetup implements Identifiable {
    private WebhookType type;
    private String identifier;

    private String url;

    private DifferedSetup commands;
    private DifferedSetup chat;

    public WebhookSetup(WebhookType type, String identifier, String url, DifferedSetup commands, DifferedSetup chat) {
        this.type = type;
        this.identifier = identifier;
        this.url = url;
        this.commands = commands;
        this.chat = chat;
    }

    @Getter @Setter
    public static class DifferedSetup {
        private boolean enabled;

        private DifferedType type;

        private PlainHook plain;
        private EmbedHook embed;

        public DifferedSetup(boolean enabled, DifferedType type, PlainHook plain, EmbedHook embed) {
            this.enabled = enabled;
            this.type = type;
            this.plain = plain;
            this.embed = embed;
        }

        public enum DifferedType {
            COMMAND,
            CHAT,
            ;
        }
    }

    @Getter @Setter
    public static class PlainHook {
        private boolean enabled;
        private String name;
        private String picture;
        private String content;

        public PlainHook(boolean enabled, String name, String picture, String content) {
            this.enabled = enabled;
            this.name = name;
            this.picture = picture;
            this.content = content;
        }
    }

    @Getter @Setter
    public static class EmbedHook {
        private boolean enabled;
        private int color;
        private String title;
        private String description;
        private String picture;

        public EmbedHook(boolean enabled, int color, String title, String description, String picture) {
            this.enabled = enabled;
            this.color = color;
            this.title = title;
            this.description = description;
            this.picture = picture;
        }
    }

    public enum WebhookType {
        GLOBAL,
        SERVER,
        CHANNEL,
        ;
    }
}
