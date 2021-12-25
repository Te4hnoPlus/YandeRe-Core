package your_package.exapmle;

import plus.utl.Cfg.Config;
import plus.YandeRe.SpringJavaPlugin;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Example extends SpringJavaPlugin {
	
	private Config config; //be like YmlConfiguration
	private String pageContent;

	@Override
    public void onSpringEnable(){
		//called when Spring Boot is enabled
		//called always after onPluginEnable()
		
		pageContent = config.getOrSetIfExistString("key", "default_value");
		//create in patch "key" "default_value" if patch not exist after return
		
		//create page with CustomPages (if this is enabled in spring.yml)
		//you can view this page by following the link
		//http://your_domain/?page=your_page.html
		CustomPages.addPage("spring_custom_pages/your_page.html");
		
		
    }
    @Override
    public void onSpringDisable(){
		//called when Spring Boot is turns off
    }

    @Override
    public void onPluginEnable(){
		//called when Plugin is enabled
		//be like JavaPlugin.onEnable()
		
		config = getOrCreateYandeReConfig("custom_config");
		
		
    }
    @Override
    public void onPluginDisable(){
		//called when Plugin is enabled
		//be like JavaPlugin.onDisable()
	}
	
	@RequestMapping(value = "/example_page", produces = MediaType.TEXT_HTML_VALUE)
	public String onWebOpen(){
		//displays the "pageContent" page to the user when he 
		//clicks on the link "http://your_domain/example_page"
		//read more in the Spring Framework documentation
		
		return pageContent;
	}
}