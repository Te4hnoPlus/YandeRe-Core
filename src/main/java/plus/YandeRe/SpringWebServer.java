package plus.YandeRe;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

//@RestController
@SpringBootApplication
public class SpringWebServer {

    private static boolean Initialized = false;
    private static boolean Has1Error = false;

    private static ApplicationContext a;

    private static HashMap<String, SpringPlugin> plugins = new HashMap<>();

    public static void registerSpringPlugin(SpringJavaPlugin p) {
        plugins.put(p.getName(), p);
    }
    public static boolean isEnabled(){
        return Initialized;
    }

    public static void disable(){
        SpringApplication.exit(a);
        Initialized = false;
        Has1Error = false;
        for(SpringPlugin p:plugins.values()){
            p.onSpringDisable();
        }
    }
    public static boolean enabled(){
        return Initialized;
    }

    public static void enable(){
        if(!Initialized) {
            Initialized = true;

            SpringApplication app = new SpringApplication(SpringWebServer.class);

            Map<String, Object> customConfig = new HashMap<>();

            customConfig.put("server.port", String.valueOf(SpringConfig.PORT));
            app.setDefaultProperties(customConfig);

            a = app.run();
            if(SpringConfig.ENABLE_CUSTOM_PAGES_PLUGIN){
                plugins.put("Custom Pages", new CustomPages());
                //registerSpringPlugin(new CustomPages());
            }
        } else {
            if(Has1Error) {
                System.out.println("Redy enabled!");
                throw new NullPointerException();
            } else {
                Has1Error = true;
            }
        }
    }





}