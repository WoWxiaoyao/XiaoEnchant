package zbv5.cn.XiaoEnchant.util;

import cn.nukkit.item.enchantment.Enchantment;

public class EnchUtil
{
    public static String getEnchName(int ench)
    {
        if(ench == 0)return "保护";
        if(ench == 1)return "火焰保护";
        if(ench == 2)return "摔落保护";
        if(ench == 3)return "爆炸保护";
        if(ench == 4)return "弹射物保护";
        if(ench == 5)return "荆棘";
        if(ench == 6)return "水下呼吸";
        if(ench == 7)return "深海探索者";
        if(ench == 8)return "水下速掘";
        if(ench == 9)return "锋利";
        if(ench == 10)return "亡灵杀手";
        if(ench == 11)return "截肢杀手";
        if(ench == 12)return "击退";
        if(ench == 13)return "火焰附加";
        if(ench == 14)return "抢夺";
        if(ench == 15)return "效率";
        if(ench == 16)return "精准采集";
        if(ench == 17)return "耐久";
        if(ench == 18)return "时运";
        if(ench == 19)return "力量";
        if(ench == 20)return "冲击";
        if(ench == 21)return "火矢";
        if(ench == 22)return "无限";
        if(ench == 23)return "海之眷顾";
        if(ench == 24)return "饵钩";
        if(ench == 25)return "寒冰行者";
        if(ench == 26)return "正在治愈";
        if(ench == 27)return "绑定的诅咒";
        if(ench == 28)return "消失的诅咒";
        if(ench == 29)return "穿刺";
        if(ench == 30)return "激流";
        if(ench == 31)return "忠诚";
        if(ench == 32)return "引雷";
        return "null";
    }

    public static Enchantment getEnch(int ench,int level)
    {
        Enchantment enchantment = Enchantment.getEnchantment(ench);
        enchantment.setLevel(level,false);
        return enchantment;
    }
}
