package net.dev.pluginmanager.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import net.dev.pluginmanager.PluginManager;

public class FileUtils {

	private File directory, file;
	private YamlConfiguration cfg;

	private PluginManager pluginManager;
	private Utils utils;
	
	public FileUtils() {
		pluginManager = PluginManager.getInstance();
		utils = pluginManager.getUtils();
		
		directory = new File("plugins/" + pluginManager.getDescription().getName() + "/");
		file = new File(directory, "setup.yml");
		
		if(!(directory.exists()))
			directory.mkdir();
		
		if(!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		
		cfg = YamlConfiguration.loadConfiguration(file);
		
		boolean isNonLegacy = Integer.parseInt(utils.getVersion().replace("v", "").split("_")[1]) > 12;
		
		cfg.addDefault("Settings.PluginsInventory.Title", "&aPlugins");
		cfg.addDefault("Settings.PluginsInventory.Plugin.DisplayName", "&b%plugin%");
		cfg.addDefault("Settings.PluginsInventory.Plugin.Lore", getListFromStrings("&7Version&8: &a%plugin%"));
		cfg.addDefault("Settings.PluginsInventory.Plugin.Type.Enabled", isNonLegacy ? "LEGACY_STAINED_CLAY" : "STAINED_CLAY");
		cfg.addDefault("Settings.PluginsInventory.Plugin.Type.Disabled", isNonLegacy ? "LEGACY_STAINED_CLAY" : "STAINED_CLAY");
		cfg.addDefault("Settings.PluginsInventory.Plugin.MetaData.Enabled", 5);
		cfg.addDefault("Settings.PluginsInventory.Plugin.MetaData.Disabled", 14);
		cfg.addDefault("Settings.PluginsInventory.Info.DisplayName", "&6&lInfo");
		cfg.addDefault("Settings.PluginsInventory.Info.Lore", getListFromStrings("&7Leftclick a plugin to open its settings", "&7Rightclick a plugin to toggle its status"));
		cfg.addDefault("Settings.PluginsInventory.Info.Type", isNonLegacy ? "LEGACY_NETHER_STAR" : "NETHER_STAR");
		cfg.addDefault("Settings.PluginsInventory.Next.DisplayName", "&8Next page");
		cfg.addDefault("Settings.PluginsInventory.Next.Lore", getListFromStrings("&7Click here to go to the next page"));
		cfg.addDefault("Settings.PluginsInventory.Next.Type", isNonLegacy ? "LEGACY_ARROW" : "ARROW");
		cfg.addDefault("Settings.PluginsInventory.Back.DisplayName", "&8Previous page");
		cfg.addDefault("Settings.PluginsInventory.Back.Lore", getListFromStrings("&7Click here to go to the previous page"));
		cfg.addDefault("Settings.PluginsInventory.Back.Type", isNonLegacy ? "LEGACY_ARROW" : "ARROW");
		
		cfg.addDefault("Settings.SettingsInventory.Title", "&b%plugin%");
		cfg.addDefault("Settings.SettingsInventory.UsePlaceHolders", true);
		cfg.addDefault("Settings.SettingsInventory.PlaceHolder.DisplayName", "&8&k,&r");
		cfg.addDefault("Settings.SettingsInventory.PlaceHolder.Type", isNonLegacy ? "LEGACY_STAINED_GLASS_PANE" : "STAINED_GLASS_PANE");
		cfg.addDefault("Settings.SettingsInventory.Enable.DisplayName", "&aEnable");
		cfg.addDefault("Settings.SettingsInventory.Enable.Lore", getListFromStrings("&7Click here to enable the plugin"));
		cfg.addDefault("Settings.SettingsInventory.Enable.Type", isNonLegacy ? "LEGACY_STAINED_CLAY" : "STAINED_CLAY");
		cfg.addDefault("Settings.SettingsInventory.Enable.MetaData", 5);
		cfg.addDefault("Settings.SettingsInventory.Disable.DisplayName", "&cDisable");
		cfg.addDefault("Settings.SettingsInventory.Disable.Lore", getListFromStrings("&7Click here to disable the plugin"));
		cfg.addDefault("Settings.SettingsInventory.Disable.Type", isNonLegacy ? "LEGACY_STAINED_CLAY" : "STAINED_CLAY");
		cfg.addDefault("Settings.SettingsInventory.Disable.MetaData", 14);
		cfg.addDefault("Settings.SettingsInventory.Info.DisplayName", "&6&l%plugin%");
		cfg.addDefault("Settings.SettingsInventory.Info.Lore", getListFromStrings("&7Version&8: &a%version%"));
		cfg.addDefault("Settings.SettingsInventory.Info.Type", isNonLegacy ? "LEGACY_NETHER_STAR" : "NETHER_STAR");
		cfg.addDefault("Settings.SettingsInventory.Back.DisplayName", "&8Go back");
		cfg.addDefault("Settings.SettingsInventory.Back.Lore", getListFromStrings("&7Click here to go back to the overview"));
		cfg.addDefault("Settings.SettingsInventory.Back.Type", isNonLegacy ? "LEGACY_ARROW" : "ARROW");
		
		cfg.addDefault("Messages.Prefix", "&8┃ &aPM &8» ");
		cfg.addDefault("Messages.NoPerm", "Unknown command. Type \"/help\" for help.");
		cfg.addDefault("Messages.NotPlayer", "&cOnly players can perform this command");
		cfg.addDefault("Messages.PluginNotFound", "&cThe plugin &a%plugin% &could not be found&7!");
		cfg.addDefault("Messages.PluginEnabled", "&eThe plugin &a%plugin% &ehas been enabled&7!");
		cfg.addDefault("Messages.PluginDisabled", "&cThe plugin &a%plugin% &chas been disabled&7!");
		cfg.addDefault("Messages.AllPluginsEnabled", "&eAll plugins have been enabled&7!");
		cfg.addDefault("Messages.AllPluginsDisabled", "&cAll plugins have been disabled&7!");
		cfg.addDefault("Messages.PluginCanNotBeToggled", "&cThe plugin &a%plugin% &ccan not be enabled/disabled&7!");
		cfg.addDefault("Messages.PluginLoaded", "&eThe plugin &a%plugin% &ehas been loaded and enabled&7!");
		cfg.addDefault("Messages.AllPluginsLoaded", "&eAll plugins have been loaded and enabled&7!");
		cfg.addDefault("Messages.PluginUnloaded", "&cThe plugin &a%plugin% &chas been unloaded and disabled&7!");
		cfg.addDefault("Messages.AllPluginsUnloaded", "&cAll plugins have been unloaded and disabled&7!");
		cfg.addDefault("Messages.PluginReloaded", "&eThe plugin &a%plugin% &ehas been reloaded and enabled&7!");
		cfg.addDefault("Messages.AllPluginsReloaded", "&eAll plugins have been reloaded and enabled&7!");
		cfg.addDefault("Messages.PluginCanNotBeLoaded", "&cThe plugin &a%plugin% &ccould not be loaded&7!");
		cfg.addDefault("Messages.AllPlugins.Header", "&8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯ &bAll plugins &8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		cfg.addDefault("Messages.AllPlugins.Plugin", "&8- %state%%name% &7(&e%version%&7)");
		cfg.addDefault("Messages.AllPlugins.Footer", "&8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		cfg.addDefault("Messages.PluginRestarted", "&eThe plugin &a%plugin% &ehas been restarted&7!");
		cfg.addDefault("Messages.AllPluginsRestarted", "&eAll plugins have been restarted&7!");
		cfg.addDefault("Messages.PluginCommands.Header", "&8⎯⎯⎯⎯⎯ &7All commands of &b%plugin% &8⎯⎯⎯⎯⎯");
		cfg.addDefault("Messages.PluginCommands.Command", "&8- &a%name% &7(&e%usage%&7)");
		cfg.addDefault("Messages.PluginCommands.Footer", "&8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		cfg.addDefault("Messages.NoPluginCommands", "&cThe plugin &a%plugin% &chas no registered commands&7!");
		cfg.addDefault("Messages.CommandBelongsToPlugin", "&eThe command &a%command% &ebelongs to the plugin &d%plugin%&7!");
		cfg.addDefault("Messages.CommandNotFound", "&cThe command &a%command% &cdoes not exist&7!");
		cfg.addDefault("Messages.PluginAlreadyLoaded", "&cThe plugin &a%plugin% &cis already loaded&7!");
		cfg.addDefault("Messages.PluginInfo", Arrays.asList("&8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯ &b%name% &8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯", "&7Author(s)&8: &a%authors%", "&7Version&8: &a%version%", "&8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯ &b%name% &8⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯"));
		cfg.options().copyDefaults(true);
		saveFile();
	}
	
	public void saveFile() {
		try {
			cfg.save(file);
		} catch (IOException e) {
		}
		
		utils.setPrefix(getConfigString("Messages.Prefix"));
		utils.setNoPerm(getConfigString("Messages.NoPerm"));
		utils.setNotPlayer(getConfigString("Messages.NotPlayer"));
	}
	
	public String getConfigString(String path) {
		return ChatColor.translateAlternateColorCodes('&', cfg.getString(path));
	}
	
	private List<String> getListFromStrings(String... strings) {
		return Arrays.asList(strings);
	}

	public List<String> getStringList(String path) {
		List<String> list = new ArrayList<>();
		
		for (String string : cfg.getStringList(path))
			list.add(ChatColor.translateAlternateColorCodes('&', string));
		
		return list;
	}
	
	public YamlConfiguration getConfig() {
		return cfg;
	}

}