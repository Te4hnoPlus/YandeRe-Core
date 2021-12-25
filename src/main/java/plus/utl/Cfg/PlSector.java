package plus.utl.Cfg;

import org.bukkit.configuration.ConfigurationSection;

public interface PlSector extends ConfigurationSection {
    void saveChanges();

    PlSector reloadAsFile();

    String getColoredString(String path);

    String getColoredString(String path, String def);

    String getString(String path);

    String getString(String path, String def);

    String getOrSetIfExistString(String patch, String def);
}
