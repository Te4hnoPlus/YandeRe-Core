package plus.YandeRe.web;


import plus.utl.Reloadable;

import java.io.File;
import java.nio.file.Files;

public class ReloadablePage implements Reloadable, Page {

    private String content;
    private File page;

    public ReloadablePage(File file) {
        page = file;
        Reload();
    }
    public ReloadablePage(String filePatch) {
        page = new File(filePatch);
        Reload();
    }

    @Override
    public void Reload() {
        try {
            content = new String(Files.readAllBytes(page.toPath()));
        } catch (Exception e){
            content = "Error initializing!";
            System.out.println(String.format("File [%s] not founded!", page.toPath().toString()));
            e.printStackTrace();
        }
    }

    @Override
    public String toStringPage(String RawCashe) {
        return content;
    }

    @Override
    public String toStringPage() {
        return content;
    }
}
