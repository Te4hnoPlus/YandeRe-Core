package plus.YandeRe;

import org.bukkit.plugin.java.JavaPlugin;

import org.springframework.web.bind.annotation.RestController;
import plus.utl.Cfg.Config;

import java.util.HashMap;

public abstract class SpringJavaPlugin extends JavaPlugin implements SpringPlugin {

    private final HashMap<String, Config> configs = new HashMap();

    @Override
    public final void onEnable(){
        onPluginEnable();
        try {
            if(SpringWebServer.isEnabled()) {
                SpringWebServer.registerSpringPlugin(this);
                onSpringEnable();
            }
            else throw new NullPointerException("Spring Core is disabled!");
        } catch (Exception e){
            System.out.println(String.format("Critical error! Plugin %s disabled", super.getFile().getName()));
            System.out.println("----------------------------------");
            e.printStackTrace();
            System.out.println("----------------------------------");
        }
    }

    @Override
    public void onSpringEnable(){
    }
    @Override
    public void onSpringDisable(){
    }

    @Override
    public void onPluginEnable(){
    }
    @Override
    public void onPluginDisable(){}

    public final void onDisable(){
        onPluginDisable();
    }

    @Override
    public void reloadConfig(){
        super.reloadConfig();
        for(Config c:configs.values()){
            c.reloadAsFile();
        }
    }

    public Config getOrCreateYandeReConfig(String name){
        if(configs.containsKey(name))
            return configs.get(name);
        else {
            Config c = new Config(this, name);
            configs.put(name, c);
            return c;
        }
    }
}
