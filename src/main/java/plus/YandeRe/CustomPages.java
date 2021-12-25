package plus.YandeRe;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import plus.YandeRe.web.Page;
import plus.YandeRe.web.ReloadablePage;
import plus.YandeRe.web.StaticPage;
import plus.utl.Reloadable;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class CustomPages implements Reloadable, SpringPlugin {

    public CustomPages(){
        onSpringEnable();
    }

    private static final HashMap<String, Page> pages = new HashMap<>();

    public void addPage(String name, String content){
        addPage(name, new StaticPage(content));
    }

    public void addPage(String name, Page page){
        if(name==null || name.equals(""))
            throw new NullPointerException("Name is null!");
        else
            pages.put(name, page);
    }
    public void clearPages(){
        pages.clear();
    }
    public void removePage(String name){
        pages.remove(name);
    }

    @Override
    public void Reload() {
        List<String> pgs = new ArrayList<>();
        for(String k:pages.keySet()){
            Page p = pages.get(k);
            if(p instanceof Reloadable){
                ((Reloadable) p).Reload();
                pgs.add(k);
            }
        }
        if(pgs.size()>0) {
            System.out.println(String.format("Reloaded %s pages.", pgs.size()));
            for(String pg:pgs){
                System.out.println(String.format("| %s", pg));
            }
        } else {
            System.out.println("In [Custom Pages] not exist reloadable pages.");
        }
    }

    @Override
    public void onSpringEnable(){
        boolean hasMainPage = false;

        File f = new File("spring_custom_pages");

        if(f.isDirectory()){
            for (String ff:f.list()){
                if(f.isDirectory()) {
                    if (ff.equals("main.html")) hasMainPage = true;

                    pages.put(ff.substring(0, ff.indexOf('.')),
                            new ReloadablePage("spring_custom_pages" + File.separator + ff));
                }
            }
        }

        if(!hasMainPage){
            try {
                File dir = new File("spring_custom_pages"); boolean isCreated = dir.mkdir();
                File fl = new File("spring_custom_pages"+ File.separator+"main.html");
                if(!fl.exists())fl.createNewFile();
                //if(fl.mkdir());
                FileWriter writer = new FileWriter(
                        fl,
                        false);
                writer.append(Dpage);
                writer.flush();
                pages.put("main", new ReloadablePage(fl));
            } catch (Exception er){
                er.printStackTrace();
            }
        }

    }
    @Override
    public void onSpringDisable(){
        pages.clear();
    }

    @Override
    public void onPluginEnable() {
    }

    @Override
    public void onPluginDisable() {
    }

    @Override
    public void reloadConfig() {
    }

    @RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String sendPage(              //http://localhost/?page=test
            @RequestParam(
                    value = "page",
                    defaultValue = "main"
            ) String page) {
        int sep = page.indexOf(':');
        if(sep>-1){
            return pages.getOrDefault(
                    page.substring(0, sep),
                    pages.get("main")
            ).toStringPage(page.substring(sep+1));
        } else
            return pages.getOrDefault(page, pages.get("main")).toStringPage();
    }


    private static final String Dpage =
            "<img src=\"https://sun9-13.userapi.com/impg/BIsTy5I7Xvk-LTuIR-UQhB3_eXKUpEaFBlZy0g/nmBy6U1pBeU.jpg?size=1920x1000&amp;quality=96&amp;sign=b8778d39ec7cb651c4871ee0523e3f21&amp;type=album\"" +
                    "width=\"100%\" height=\"auto\" left:50% top:50% " +
                    "alt=\"YandeRe Framework default [CustomPages] page\">"
            ;
}
