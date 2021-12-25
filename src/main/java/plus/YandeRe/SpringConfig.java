package plus.YandeRe;

import plus.utl.Cfg.Config;

public class SpringConfig {

    private final Config c;

    public static Boolean ENABLE_WEB = false;
    public static Boolean ENABLE_CUSTOM_PAGES_PLUGIN = true;
    public static int PORT = 80;


    public SpringConfig(){
        c = new Config("spring.yml");
        reload();
    }
    public void reload(){
        c.reloadAsFile();

        ENABLE_WEB = c.getOrSetIfExistString("spring.enabled", "true").equalsIgnoreCase("true");
        ENABLE_CUSTOM_PAGES_PLUGIN =
                c.getOrSetIfExistString("spring.enable_custom_pages", "true").equalsIgnoreCase("true");
        int p = c.getInt("spring.port", 0);
        if(p==0)c.set("spring.port", 80);
        PORT = p;
        c.saveChanges();
    }

}
