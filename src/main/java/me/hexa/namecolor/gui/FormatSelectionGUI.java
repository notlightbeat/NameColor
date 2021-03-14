package me.hexa.namecolor.gui;

import java.util.ArrayList;
import java.util.List;

import me.hexa.namecolor.NameColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FormatSelectionGUI implements ISelectionInventory {

	private final Inventory inv;
	private final Player player;
	private final int slot;
	private final char color;
	
	public FormatSelectionGUI(Player player, int slot, char color) {
		inv = Bukkit.createInventory(this, 18, ChatColor.translateAlternateColorCodes('&', "&9Choose format"));
		this.player = player;
		this.slot = slot;
		this.color = color;
        initializeItems();
	}
	
	@Override
	public Inventory getInventory() {
		return inv;
	}

	@Override
	public void initializeItems() {
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "", "Normal"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§l", "Bold"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§m", "Strikethrough"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§n", "Underline"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§o", "Italics"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§l§m", "Bold Strikethrough"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§l§n", "Bold Underline"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§l§o", "Bold Italics"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§m§o", "Strikethrough Italics"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§n§o", "Underline Italics"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§l§m§o", "Bold Strikethrough Italics"));
        inv.addItem(createGuiItem(Material.BOOK_AND_QUILL, "§l§n§o", "Bold Underline Italics"));
	}

	public ItemStack createGuiItem(Material material, String format, String name) {
		final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', format + name));
        
        List<String> lore = new ArrayList<>();
        lore.add("Preview:");
        
        if (slot != 16) {
        	lore.add("" + '§' + color + format + player.getName());
        } else {
        	lore.add(format + makeRainbow(player.getName(), format));
        }
        
        meta.setLore(lore);
        
        item.setItemMeta(meta);

        return item;
	}
	
	public String makeRainbow(String text, String format) {
        List<ChatColor> colors = new ArrayList<>();

        colors.add(ChatColor.RED);
        colors.add(ChatColor.GOLD);
        colors.add(ChatColor.YELLOW);
        colors.add(ChatColor.GREEN);
        colors.add(ChatColor.DARK_GREEN);
        colors.add(ChatColor.DARK_AQUA);
        colors.add(ChatColor.BLUE);
        colors.add(ChatColor.DARK_BLUE);
        colors.add(ChatColor.DARK_PURPLE);
        colors.add(ChatColor.LIGHT_PURPLE);

        StringBuilder builder = new StringBuilder();
        List<Character> chars = new ArrayList<>();
        
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            chars.add(c);
        }
        
        int i = 0;
        
        for (Character letter : chars) {
            if (i == colors.size()) {
                i = 0;
            }
            builder.append(colors.get(i)).append(format).append(letter);
            i++;
        }

        return builder.toString();
    }

	@Override
	public void onGUIClick(Player player, int slot, ItemStack item) {
		String format;
		switch (slot) {
		case 1: format = "§l"; break;
		case 2: format = "§m"; break;
		case 3: format = "§n"; break;
		case 4: format = "§o"; break;
		case 5: format = "§l§m"; break;
		case 6: format = "§l§n"; break;
		case 7: format = "§l§o"; break;
		case 8: format = "§m§o"; break;
		case 9: format = "§n§o"; break;
		case 10: format = "§l§m§o"; break;
		case 11: format = "§l§n§o"; break;
		default: format = ""; break;
		}
		if (this.slot != 16) {
			player.setDisplayName("§" + color + format + player.getName() + ChatColor.RESET);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[NameColor] &9Your name now looks like this: &r" + player.getDisplayName()));
			NameColor.getPlugin(NameColor.class).getConfig().set(player.getName(), "§" + color + format + player.getName() + ChatColor.RESET);
		} else {
			player.setDisplayName(makeRainbow(player.getName(), format) + ChatColor.RESET);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[NameColor] &9Your name now looks like this: &r" + player.getDisplayName()));
			NameColor.getPlugin(NameColor.class).getConfig().set(player.getName(), makeRainbow(player.getName(), format) + ChatColor.RESET);
		}
		NameColor.getPlugin(NameColor.class).saveConfig();
		if (NameColor.getPlugin(NameColor.class).getConfig().getString(player.getName()) != null) {
            player.setPlayerListName(NameColor.getPlugin(NameColor.class).getConfig().getString(player.getName()));
        }
        player.closeInventory();
	}

}

