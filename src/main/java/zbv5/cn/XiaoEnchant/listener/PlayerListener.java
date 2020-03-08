package zbv5.cn.XiaoEnchant.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryCloseEvent;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import org.json.JSONObject;
import zbv5.cn.XiaoEnchant.lang.Lang;
import zbv5.cn.XiaoEnchant.util.EnchUtil;
import zbv5.cn.XiaoEnchant.util.PrintUtil;
import zbv5.cn.XiaoEnchant.windows.setWindows;

import java.util.HashMap;
import java.util.regex.Pattern;

public class PlayerListener implements Listener
{
    public static HashMap<String, Integer> open = new HashMap<String, Integer>();

    @EventHandler
    public void onClickWindow(PlayerFormRespondedEvent e)
    {
        if (e.getPlayer() == null) {
            return;
        }
        if (e.getResponse() == null) {
            return;
        }
        FormWindow gui = e.getWindow();

        if ((!(gui instanceof FormWindowSimple)) || (e.wasClosed()))
        {
            return;
        }

        JSONObject json = new JSONObject(e.getWindow().getJSONData());

        Player p = e.getPlayer();
        String title = json.getString("title");
        if(title.equals(PrintUtil.cc("&5&l附魔系统")))
        {
            String ButtonName = ((FormResponseSimple)e.getResponse()).getClickedButton().getText();
            String[] ButtonNames = ButtonName.split(",");
            p.showFormWindow(setWindows.setUi(p,Integer.parseInt(ButtonNames[0])));
            open.put(p.getName(),Integer.parseInt(ButtonNames[0]));
            return;
        }
        if(title.startsWith(PrintUtil.cc("&5&l附魔系统 &f- &c")))
        {
            String ButtonName = ((FormResponseSimple)e.getResponse()).getClickedButton().getText();
            if(ButtonName.equals("&c返回首页"))
            {
                open.remove(p.getName());
                p.showFormWindow(setWindows.getUi(p));
                return;
            }
            Pattern pattern = Pattern.compile("[0-9]*");
            if((pattern.matcher(ButtonName).matches()))
            {
                int level = Integer.parseInt(ButtonName);
                int ench = open.get(p.getName());
                open.remove(p.getName());

                if((level < 0) || (level > 32767))
                {
                    PrintUtil.PrintPlayer(p, Lang.NullLevel,false);
                    return;
                }
                Item item = p.getInventory().getItemInHand();
                item.addEnchantment(EnchUtil.getEnch(ench,level));
                p.getInventory().setItemInHand(item);
                PrintUtil.PrintPlayer(p,Lang.Executed.replace("{ench}",EnchUtil.getEnchName(ench)).replace("{level}",Integer.toString(level)),false);
            }
        }
    }

}
