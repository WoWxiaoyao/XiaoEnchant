package zbv5.cn.XiaoEnchant;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.plugin.PluginBase;
import zbv5.cn.XiaoEnchant.lang.Lang;
import zbv5.cn.XiaoEnchant.util.EnchUtil;
import zbv5.cn.XiaoEnchant.util.FileUtil;
import zbv5.cn.XiaoEnchant.util.PluginUtil;
import zbv5.cn.XiaoEnchant.util.PrintUtil;
import zbv5.cn.XiaoEnchant.windows.setWindows;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main extends PluginBase
{
    private static Main instance;
    public static int Max_Level = 0;
    public static List<Integer> Ui_level = new ArrayList<Integer>();


    public static Main getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        instance = this;
        PluginUtil.LoadPlugin();

    }

    @Override
    public void onDisable()
    {
        PluginUtil.DisablePlugin();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (command.getLabel().equalsIgnoreCase("XiaoEnchant"))
        {
            if (args.length == 0)
            {
                PrintUtil.PrintCommandSender(sender,false,"&6==== [&bXiaoEnchant&6] ====");
                PrintUtil.PrintCommandSender(sender,false,"&6/"+label+"&a set &e<附魔序号> <等级> &7- &b为手上的物品附魔");
                PrintUtil.PrintCommandSender(sender,false,"&6/"+label+"&a list &7- &e列出全部附魔");
                PrintUtil.PrintCommandSender(sender,false,"&6/"+label+"&a open &7- &d打开便捷附魔页面");
                PrintUtil.PrintCommandSender(sender,false,"&6/"+label+"&a setname &7- &d设置物品昵称");
                PrintUtil.PrintCommandSender(sender,false,"&6/"+label+"&c reload &7- &4重载插件配置");
                return true;
            } else {
                if(!sender.hasPermission("XiaoEnchant.admin"))
                {
                    PrintUtil.PrintCommandSender(sender,false, Lang.NoPermission);
                    return false;
                }
                if(args[0].equalsIgnoreCase("reload"))
                {
                    try
                    {
                        PluginUtil.reloadLoadPlugin();
                        PrintUtil.PrintCommandSender(sender,false,Lang.SuccessReload);
                        return true;
                    } catch (Exception e)
                    {
                        PrintUtil.PrintCommandSender(sender,false,Lang.FailReload);
                        e.printStackTrace();
                    }
                    return false;
                }
                if(args[0].equalsIgnoreCase("list"))
                {
                    PrintUtil.PrintCommandSender(sender,false,"&6==== [&bXiaoEnchant&6] ====");
                    for(int i=0;i<33;i++)
                    {
                        PrintUtil.PrintCommandSender(sender,false,"&e> &f"+i+"."+ EnchUtil.getEnchName(i));
                    }
                    return true;
                }

                if(!(sender instanceof Player))
                {
                    PrintUtil.PrintCommandSender(sender,false, Lang.NeedPlayer);
                    return false;
                }
                Player p = (Player)sender;
                Item item = p.getInventory().getItemInHand();
                if((item == null) || (item.getId() <= 0))
                {
                    PrintUtil.PrintCommandSender(sender,false,Lang.NullHand);
                    return false;
                }

                if(args[0].equalsIgnoreCase("setname"))
                {
                    if (args.length == 2)
                    {
                        String name = PrintUtil.cc(args[1]);
                        item.setCustomName(name);
                        p.getInventory().setItemInHand(item);
                        PrintUtil.PrintCommandSender(sender,false,Lang.ExecutedName.replace("{name}",name));
                        return true;
                    }
                    PrintUtil.PrintCommandSender(sender,false,"{prefix}&c正确方式: /"+label+" setname <名称>");
                    return false;
                }
                if(args[0].equalsIgnoreCase("open"))
                {
                    p.showFormWindow(setWindows.getUi(p));
                    return true;
                }
                if(args[0].equalsIgnoreCase("set"))
                {
                    if (args.length == 3)
                    {

                        Pattern pattern = Pattern.compile("[0-9]*");
                        if(!((pattern.matcher(args[1]).matches()) && pattern.matcher(args[2]).matches()))
                        {
                            PrintUtil.PrintCommandSender(sender,false,Lang.NoInteger);
                            return false;
                        }
                        int ench = Integer.parseInt(args[1]);
                        int level = Integer.parseInt(args[2]);
                        if((level > Max_Level) || (level < 0) || (level > 32767))
                        {
                            PrintUtil.PrintCommandSender(sender,false,Lang.NullLevel);
                            return false;
                        }
                        if(((0 > ench) || (ench > 32)))
                        {
                            PrintUtil.PrintCommandSender(sender,false,Lang.NullEnch);
                            return false;
                        }

                        item.addEnchantment(EnchUtil.getEnch(ench,level));
                        p.getInventory().setItemInHand(item);

                        PrintUtil.PrintCommandSender(sender,false,Lang.Executed.replace("{ench}",EnchUtil.getEnchName(ench)).replace("{level}",Integer.toString(level)));
                        return true;
                    }
                    PrintUtil.PrintCommandSender(sender,false,"{prefix}&c正确方式: /"+label+" set <附魔序号> <等级>");
                    return false;
                }
            }
            PrintUtil.PrintCommandSender(sender,false,Lang.NullCommand);
            return false;
        }
        return false;
    }
}
