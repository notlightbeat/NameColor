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

public class ColorSelectionGUI implements ISelectionInventory {
	
	private final Inventory inv;
	private final String playerName;

    public ColorSelectionGUI(String playerName) {
        inv = Bukkit.createInventory(this, 18, ChatColor.translateAlternateColorCodes('&', "&9Choose color"));
        this.playerName = playerName;
        initializeItems();
    }

    public void initializeItems() {
        inv.addItem(createGuiItem(Material.INK_SACK, 0, "&0Black"));
        inv.addItem(createGuiItem(Material.INK_SACK, 4, "&1Dark Blue"));
        inv.addItem(createGuiItem(Material.INK_SACK, 2, "&2Dark Green"));
        inv.addItem(createGuiItem(Material.INK_SACK, 6, "&3Dark Aqua"));
        inv.addItem(createGuiItem(Material.REDSTONE, "&4Dark Red"));
        inv.addItem(createGuiItem(Material.INK_SACK, 5, "&5Dark Purple"));
        inv.addItem(createGuiItem(Material.INK_SACK, 14, "&6Gold"));
        inv.addItem(createGuiItem(Material.INK_SACK, 7, "&7Gray"));
        inv.addItem(createGuiItem(Material.INK_SACK, 8, "&8Dark Gray"));
        inv.addItem(createGuiItem(Material.INK_SACK, 12, "&9Blue"));
        inv.addItem(createGuiItem(Material.INK_SACK, 10, "&aGreen"));
        inv.addItem(createGuiItem(Material.DIAMOND, "&bAqua"));
        inv.addItem(createGuiItem(Material.INK_SACK, 1, "&cRed"));
        inv.addItem(createGuiItem(Material.INK_SACK, 13, "&dLight Purple"));
        inv.addItem(createGuiItem(Material.INK_SACK, 11, "&eYellow"));
        inv.addItem(createGuiItem(Material.INK_SACK, 15, "&fWhite"));
        inv.addItem(createGuiItem(Material.GOLDEN_APPLE, 1, makeRainbow("Rainbow", "")));
    }

    public ItemStack createGuiItem(final Material material, final int durability, final String name) {
        final ItemStack item = new ItemStack(material, 1);
        item.setDurability((short)durability);
        final ItemMeta meta = item.getItemMeta();
        
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        
        List<String> lore = new ArrayList<String>();
        lore.add("Preview:");
        
        if (!(material.equals(Material.GOLDEN_APPLE))) {
        	lore.add(ChatColor.translateAlternateColorCodes('&', "&" + name.charAt(1) + playerName));
        } else {
        	lore.add(makeRainbow(playerName, ""));
        }
        
        meta.setLore(lore);
        
        item.setItemMeta(meta);

        return item;
    }
    
    public ItemStack createGuiItem(final Material material, final String name) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        
        List<String> lore = new ArrayList<String>();
        lore.add("Preview:");
        
        if (!(material.equals(Material.GOLDEN_APPLE))) {
        	lore.add(ChatColor.translateAlternateColorCodes('&', "&" + name.charAt(1) + playerName));
        } else {
        	lore.add(makeRainbow(playerName, ""));
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
	public Inventory getInventory() {
		return inv;
	}

	@Override
	public void onGUIClick(Player player, int slot, ItemStack item) {
		char color = '0';
    	switch (slot) {
			case 0: color = '0'; break;
			case 1: color = '1'; break;
			case 2: color = '2'; break;
			case 3: color = '3'; break;
			case 4: color = '4'; break;
			case 5: color = '5'; break;
			case 6: color = '6'; break;
			case 7: color = '7'; break;
			case 8: color = '8'; break;
			case 9: color = '9'; break;
			case 10: color = 'a'; break;
			case 11: color = 'b'; break;
			case 12: color = 'c'; break;
			case 13: color = 'd'; break;
			case 14: color = 'e'; break;
			case 15:
            case 16: color = 'f'; break;
        }
		FormatSelectionGUI gui = new FormatSelectionGUI(player, slot, color);
		player.openInventory(gui.getInventory());
	}
    
    
    
}
