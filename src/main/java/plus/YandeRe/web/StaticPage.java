package plus.YandeRe.web;

public class StaticPage implements Page{

    private final String content;
    public StaticPage(String content){
        this.content = content;
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
