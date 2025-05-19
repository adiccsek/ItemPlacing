package hu.shiya.itemPlacing.commands;

import hu.shiya.itemPlacing.ItemPlacing;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HatPlaceCommand implements CommandExecutor {
    private final ItemPlacing plugin;
    public HatPlaceCommand( final ItemPlacing plugin ) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (commandSender instanceof Player player ) {
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (itemInHand.getType() == Material.AIR) {
                player.sendMessage(" You can't put air on your head! ");
                return true;
            }
            Material itemOnHead;
            if (player.getInventory().getHelmet() == null) {
                itemOnHead = Material.AIR;
            } else {
                itemOnHead = player.getInventory().getHelmet().getType();
            }

            List<String> bannedItems = plugin.getConfig().getStringList("banned-items");
            List<ItemStack> bannedItemsStack = new ArrayList<>();

            for (String oneItem : bannedItems) {
                Material temp = Material.valueOf(oneItem.toUpperCase());
                ItemStack itemStackItem = new ItemStack ( temp );
                bannedItemsStack.add( itemStackItem );
            }

            for (ItemStack itemStack : bannedItemsStack) {
                if (itemStack.getType() == itemInHand.getType()) {
                    player.sendMessage(ChatColor.RED + " This is a banned item! ");
                    return true;
                }
            }
                if (itemOnHead == Material.AIR && player.hasPermission("hat.put")) {
                    player.getInventory().setHelmet(itemInHand);
                    player.sendMessage(ChatColor.GREEN + "The item was succesfully put on your head!");
                    return true;
                }
                else if (itemOnHead != Material.AIR && player.hasPermission("hat.put")) {
                    player.sendMessage(ChatColor.RED + "You have something on your head already!");
                    return true;
                }
            }
        return true;
    }
}
