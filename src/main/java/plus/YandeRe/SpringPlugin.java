package plus.YandeRe;

public interface SpringPlugin {
    void onSpringEnable();

    void onSpringDisable();

    void onPluginEnable();

    void onPluginDisable();

    void reloadConfig();
}
