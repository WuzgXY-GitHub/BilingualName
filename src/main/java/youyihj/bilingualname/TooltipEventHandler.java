package youyihj.bilingualname;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Locale;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

/**
 * @author youyihj & WuzgXY
 */
@Mod.EventBusSubscriber
public class TooltipEventHandler {
    public static final Locale EN_US = new Locale();

    static {
        EN_US.loadLocaleDataFiles(Minecraft.getMinecraft().getResourceManager(), Lists.newArrayList("zh_cn"));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onTooltip(ItemTooltipEvent event) {
        if (Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getJavaLocale().equals(java.util.Locale.US)) return;
        ItemStack stack = event.getItemStack();
        List<String> tooltip = event.getToolTip();
        String key = Util.tryFindingKey(EN_US, stack);
        if (key.isEmpty()) return;
        String localizedName = TextFormatting.WHITE + EN_US.formatMessage(key, new Object[0]);
        tooltip.add(localizedName);
    }
}
