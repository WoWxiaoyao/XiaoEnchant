package zbv5.cn.XiaoEnchant.lang;

import zbv5.cn.XiaoEnchant.util.PrintUtil;


public class Lang
{
    public static String Prefix = "&6[&bXiaoEnchant&6]";
    public static String NoPermission = "{prefix}&c你没有权限这样做";
    public static String SuccessReload = "{prefix}&a重载完成!";
    public static String FailReload = "{prefix}&c重载失败!请前往控制台查看报错.";
    public static String NullCommand = "{prefix}&c未知指令.";
    public static String Executed= "{prefix}&a你成功设置附魔&d{ench}&f-&3{level}";
    public static String ExecutedName= "{prefix}&a你成功设置物品名称为&f{name}";
    public static String NoInteger = "{prefix}&c输入值非整数.";
    public static String NullLevel = "{prefix}&c输入附魔等级异常.";
    public static String NullEnch = "{prefix}&c附魔不存在.";
    public static String NeedPlayer = "{prefix}&c只有玩家才能执行该操作.";
    public static String NullHand = "{prefix}&c需要手持一个物品.";

    public static void LoadLang()
    {
        try
        {
            PrintUtil.PrintConsole("&a&l√ &a语言文件加载完成.",false);
        }
        catch (Exception e)
        {
            PrintUtil.PrintConsole("&c&l× &4读取语言文件出现问题,请检查服务器.",false);
            e.printStackTrace();
        }
    }
}
