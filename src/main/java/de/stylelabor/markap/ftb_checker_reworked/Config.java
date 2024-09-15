// Config.java
package de.stylelabor.markap.ftb_checker_reworked;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = Ftb_checker_reworked.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final Config CONFIG;
    private static final Logger LOGGER = LogManager.getLogger();

    static {
        final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    public final List<ModConfig> mods;

    public Config(ForgeConfigSpec.Builder builder) {
        mods = List.of(
                new ModConfig("ftbchunks", "https://www.curseforge.com/api/v1/mods/314906/files/5378090/download", "https://www.curseforge.com/minecraft/mc-mods/ftb-chunks-forge"),
                new ModConfig("ftbquests", "https://www.curseforge.com/api/v1/mods/289412/files/5543955/download", "https://www.curseforge.com/minecraft/mc-mods/ftb-quests-forge")
        );
    }

    public static class ModConfig {
        public String modId;
        public String directDownloadLink;
        public String websiteDownloadLink;

        public ModConfig(String modId, String directDownloadLink, String websiteDownloadLink) {
            this.modId = modId;
            this.directDownloadLink = directDownloadLink;
            this.websiteDownloadLink = websiteDownloadLink;
        }
    }

    public Optional<String> getDirectDownloadLink(String modId) {
        return mods.stream()
                .filter(mod -> mod.modId.equals(modId))
                .map(mod -> mod.directDownloadLink)
                .findFirst();
    }

    public Optional<String> getWebsiteDownloadLink(String modId) {
        return mods.stream()
                .filter(mod -> mod.modId.equals(modId))
                .map(mod -> mod.websiteDownloadLink)
                .findFirst();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
        LOGGER.info("Configuration loaded");
    }
}