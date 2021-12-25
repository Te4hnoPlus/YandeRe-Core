package plus.YandeRe;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginBase;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

import plus.utl.Cfg.CoreSector;
import plus.utl.Cfg.PlSector;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public final class YandeRePlugin extends PluginBase implements Plugin {

    private PlSector c = new CoreSector("YandeRe.yml");

    private static YandeRePlugin p;
    public YandeRePlugin(){
        p = this;
    }
    public static YandeRePlugin getSelf(){
        if(p==null)p = new YandeRePlugin();
        return p;
    }

    @Override
    public File getDataFolder() {
        return null;
    }

    @Override
    public PluginDescriptionFile getDescription() {
        return null;
    }

    @Override
    public FileConfiguration getConfig() {
        return null;
    }

    @Override
    public InputStream getResource(String string) {
        return null;
    }

    @Override
    public void saveConfig() {
        c.saveChanges();
    }

    @Override
    public void saveDefaultConfig() {
        c.saveChanges();
    }

    @Override
    public void saveResource( String string, boolean bl) {
        System.out.println("Not supported in YandeRe plugin");
    }

    @Override
    public void reloadConfig() {
        c.reloadAsFile();
    }

    @Override
    public PluginLoader getPluginLoader() {
        return null;
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void onDisable() {
        plus.YandeRe.SpringWebServer.disable();
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public boolean isNaggable() {
        return false;
    }

    @Override
    public void setNaggable(boolean bl) {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String string, String string2) {
        return null;
    }

    @Override
    public BiomeProvider getDefaultBiomeProvider(String string, String string2) {
        return null;
    }

    @Override
    public Logger getLogger() {
        return Bukkit.getLogger();
    }

    @Override
    public boolean onCommand( CommandSender commandSender, Command command, String string, String[] strings) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String string, String[] strings) {
        return null;
    }
}
