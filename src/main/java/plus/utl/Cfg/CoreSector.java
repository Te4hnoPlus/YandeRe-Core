package plus.utl.Cfg;


import org.bukkit.configuration.file.YamlConfiguration;
import java.io.InputStreamReader;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class CoreSector extends YamlConfiguration implements PlSector {

    private final String name;
    private File file;

    public CoreSector(String name){
        super();
        this.name = name;
        try {
            file = new File(name);
            if (!file.exists()) {
                //load(new InputStreamReader(v.getResource(name), StandardCharsets.UTF_8));
                save(file);
            } else {
                load(file);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveChanges(){
        try {
            save(file);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public PlSector reloadAsFile(){
        return new CoreSector(name);
    }

    @Override
    public String getColoredString(String path) {
        return super.getString(path, "").replace('&', '\u00A7');
    }


    @Override
    public String getColoredString(String path, String def) {
        return super.getString(path, def).replace('&', '\u00A7');
    }
    @Override
    public String getString(String path){
        return super.getString(path);
    }
    @Override
    public String getString(String path, String def){
        return super.getString(path, def);
    }

    @Override
    public String getOrSetIfExistString(String patch, String def) {
        String r = super.getString(patch);
        if(r==null){
            super.set(patch, def);
            return def;
        } else return r;
    }
}
