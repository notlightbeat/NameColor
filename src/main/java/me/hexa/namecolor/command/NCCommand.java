package me.hexa.namecolor.command;

import java.util.ArrayList;

import me.hexa.namecolor.NameColor;
import me.hexa.namecolor.gui.ColorSelectionGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NCCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player)sender;
			if (player.hasPermission("namecolor.command")) {
				if (player.hasPermission("namecolor.custom")) {
					try {

						char[] nameChars = args[0].toCharArray();
						ArrayList<Character> rawName = new ArrayList<>();

						for (int i = 0; i < nameChars.length; i++) {

							if (nameChars[i] != '&') {
								rawName.add(nameChars[i]);
							} else {
								if (nameChars[i + 1] == 'k') {
									if (NameColor.getPlugin().getConfig().getBoolean("use-magic-character") || player.isOp()) {
										i++;
									} else {
										player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You can't use the magic character 'k' in your name."));
										return false;
									}
								} else {
									i++;
								}
							}

						}

						if (rawName.size() == player.getName().length()) {

							boolean isNameEqual = true;
							int i = 0;

							while (i < rawName.size() && isNameEqual) {

								if (player.getName().charAt(i) != rawName.get(i)) {
									isNameEqual = false;
								} else {
									i++;
								}

							}

							if (isNameEqual) {
								player.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0]) + ChatColor.RESET);
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[NameColor] &9Your name now looks like this: &r" + player.getDisplayName()));
								NameColor.getPlugin(NameColor.class).getConfig().set(player.getName(), ChatColor.translateAlternateColorCodes('&', args[0]) + ChatColor.RESET);
								NameColor.getPlugin(NameColor.class).saveConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The name you write and your name do not match"));
								return false;
							}

						} else {

							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The name you write and your name must be the same."));
							return false;

						}
					} catch (ArrayIndexOutOfBoundsException e) {

						ColorSelectionGUI gui = new ColorSelectionGUI(player.getName());
						player.openInventory(gui.getInventory());
						return true;

					}
				} else {

					ColorSelectionGUI gui = new ColorSelectionGUI(player.getName());
					player.openInventory(gui.getInventory());
					return true;

				}
			} else {

				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You're not allowed to use this command."));

			}
		}
		return false;
	}
}
