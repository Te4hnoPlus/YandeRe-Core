package plus.utl.Achat;

import net.minecraft.SystemUtils;
import net.minecraft.network.chat.ChatMessageType;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Json {

    public static void sendChatJson(Player pl){
        sendChatJson((CraftPlayer)pl);
    }
    public static void sendChatJson(CraftPlayer pl){
        pl.getHandle().b.sendPacket(new PacketPlayOutChat(
                        IChatBaseComponent.ChatSerializer.a("string"),
                        ChatMessageType.b,
                        SystemUtils.b
                )
        );
    }
}
