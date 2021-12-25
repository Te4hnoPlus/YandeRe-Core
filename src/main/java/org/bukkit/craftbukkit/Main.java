//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.bukkit.craftbukkit;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.minecraft.util.ExceptionSuppressor;
import net.minecraft.world.level.lighting.LightEngineLayerEventListener;
import net.minecraft.world.level.lighting.LightEngineLayerEventListener.Void;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;

import plus.YandeRe.SpringConfig;
import plus.YandeRe.SpringWebServer;


public class Main {
    public static SpringConfig springConfig;
    public static boolean useJline = true;
    public static boolean useConsole = true;

    public Main() {
    }

    public static void main(String[] args) {
        String warnWhenLegacyFormattingDetected = String.join(".", "net", "kyori", "adventure", "text", "warnWhenLegacyFormattingDetected");
        if (System.getProperty("jdk.nio.maxCachedBufferSize") == null) {
            System.setProperty("jdk.nio.maxCachedBufferSize", "262144");
        }

        OptionParser parser = new OptionParser() {
            {
                this.acceptsAll(Main.asList("?", "help"), "Show the help");
                this.acceptsAll(Main.asList("c", "config"), "Properties file to use").withRequiredArg().ofType(File.class).defaultsTo(new File("server.properties"), new File[0]).describedAs("Properties file");
                this.acceptsAll(Main.asList("P", "plugins"), "Plugin directory to use").withRequiredArg().ofType(File.class).defaultsTo(new File("plugins"), new File[0]).describedAs("Plugin directory");
                this.acceptsAll(Main.asList("h", "host", "server-ip"), "Host to listen on").withRequiredArg().ofType(String.class).describedAs("Hostname or IP");
                this.acceptsAll(Main.asList("W", "world-dir", "universe", "world-container"), "World container").withRequiredArg().ofType(File.class).defaultsTo(new File("."), new File[0]).describedAs("Directory containing worlds");
                this.acceptsAll(Main.asList("w", "world", "level-name"), "World name").withRequiredArg().ofType(String.class).describedAs("World name");
                this.acceptsAll(Main.asList("p", "port", "server-port"), "Port to listen on").withRequiredArg().ofType(Integer.class).describedAs("Port");
                this.acceptsAll(Main.asList("o", "online-mode"), "Whether to use online authentication").withRequiredArg().ofType(Boolean.class).describedAs("Authentication");
                this.acceptsAll(Main.asList("s", "size", "max-players"), "Maximum amount of players").withRequiredArg().ofType(Integer.class).describedAs("Server size");
                this.acceptsAll(Main.asList("d", "date-format"), "Format of the date to display in the console (for log entries)").withRequiredArg().ofType(SimpleDateFormat.class).describedAs("Log date format");
                this.acceptsAll(Main.asList("log-pattern"), "Specfies the log filename pattern").withRequiredArg().ofType(String.class).defaultsTo("server.log", new String[0]).describedAs("Log filename");
                this.acceptsAll(Main.asList("log-limit"), "Limits the maximum size of the log file (0 = unlimited)").withRequiredArg().ofType(Integer.class).defaultsTo(0, new Integer[0]).describedAs("Max log size");
                this.acceptsAll(Main.asList("log-count"), "Specified how many log files to cycle through").withRequiredArg().ofType(Integer.class).defaultsTo(1, new Integer[0]).describedAs("Log count");
                this.acceptsAll(Main.asList("log-append"), "Whether to append to the log file").withRequiredArg().ofType(Boolean.class).defaultsTo(true, new Boolean[0]).describedAs("Log append");
                this.acceptsAll(Main.asList("log-strip-color"), "Strips color codes from log file");
                this.acceptsAll(Main.asList("b", "bukkit-settings"), "File for bukkit settings").withRequiredArg().ofType(File.class).defaultsTo(new File("bukkit.yml"), new File[0]).describedAs("Yml file");
                this.acceptsAll(Main.asList("C", "commands-settings"), "File for command settings").withRequiredArg().ofType(File.class).defaultsTo(new File("commands.yml"), new File[0]).describedAs("Yml file");
                this.acceptsAll(Main.asList("forceUpgrade"), "Whether to force a world upgrade");
                this.acceptsAll(Main.asList("eraseCache"), "Whether to force cache erase during world upgrade");
                this.acceptsAll(Main.asList("nogui"), "Disables the graphical console");
                this.acceptsAll(Main.asList("nojline"), "Disables jline and emulates the vanilla console");
                this.acceptsAll(Main.asList("noconsole"), "Disables the console");
                this.acceptsAll(Main.asList("v", "version"), "Show the CraftBukkit Version");
                this.acceptsAll(Main.asList("demo"), "Demo mode");
                this.acceptsAll(Main.asList("S", "spigot-settings"), "File for spigot settings").withRequiredArg().ofType(File.class).defaultsTo(new File("spigot.yml"), new File[0]).describedAs("Yml file");
                this.acceptsAll(Main.asList("paper", "paper-settings"), "File for paper settings").withRequiredArg().ofType(File.class).defaultsTo(new File("paper.yml"), new File[0]).describedAs("Yml file");
                this.acceptsAll(Main.asList("jettpack", "jettpack-settings"), "File for jettpack settings").withRequiredArg().ofType(File.class).defaultsTo(new File("jettpack.yml"), new File[0]).describedAs("Yml file");
                this.acceptsAll(Main.asList("server-name"), "Name of the server").withRequiredArg().ofType(String.class).defaultsTo("Unknown Server", new String[0]).describedAs("Name");
                this.acceptsAll(Main.asList("add-plugin", "add-extra-plugin-jar"), "Specify paths to extra plugin jars to be loaded in addition to those in the plugins folder. This argument can be specified multiple times, once for each extra plugin jar path.").withRequiredArg().ofType(File.class).defaultsTo(new File[0]).describedAs("Jar file");
            }
        };
        OptionSet options = null;
        tryPreloadClass("org.apache.logging.log4j.core.Core");
        tryPreloadClass("org.apache.logging.log4j.core.appender.AsyncAppender");
        tryPreloadClass("org.apache.logging.log4j.core.Appender");
        tryPreloadClass("org.apache.logging.log4j.core.ContextDataInjector");
        tryPreloadClass("org.apache.logging.log4j.core.Filter");
        tryPreloadClass("org.apache.logging.log4j.core.ErrorHandler");
        tryPreloadClass("org.apache.logging.log4j.core.LogEvent");
        tryPreloadClass("org.apache.logging.log4j.core.Logger");
        tryPreloadClass("org.apache.logging.log4j.core.LoggerContext");
        tryPreloadClass("org.apache.logging.log4j.core.LogEventListener");
        tryPreloadClass("org.apache.logging.log4j.core.AbstractLogEvent");
        tryPreloadClass("org.apache.logging.log4j.message.AsynchronouslyFormattable");
        tryPreloadClass("org.apache.logging.log4j.message.FormattedMessage");
        tryPreloadClass("org.apache.logging.log4j.message.ParameterizedMessage");
        tryPreloadClass("org.apache.logging.log4j.message.Message");
        tryPreloadClass("org.apache.logging.log4j.message.MessageFactory");
        tryPreloadClass("org.apache.logging.log4j.message.TimestampMessage");
        tryPreloadClass("org.apache.logging.log4j.message.SimpleMessage");
        tryPreloadClass("org.apache.logging.log4j.core.async.AsyncLogger");
        tryPreloadClass("org.apache.logging.log4j.core.async.AsyncLoggerContext");
        tryPreloadClass("org.apache.logging.log4j.core.async.AsyncQueueFullPolicy");
        tryPreloadClass("org.apache.logging.log4j.core.async.AsyncLoggerDisruptor");
        tryPreloadClass("org.apache.logging.log4j.core.async.RingBufferLogEvent");
        tryPreloadClass("org.apache.logging.log4j.core.async.DisruptorUtil");
        tryPreloadClass("org.apache.logging.log4j.core.async.RingBufferLogEventHandler");
        tryPreloadClass("org.apache.logging.log4j.core.impl.ThrowableProxy");
        tryPreloadClass("org.apache.logging.log4j.core.impl.ExtendedClassInfo");
        tryPreloadClass("org.apache.logging.log4j.core.impl.ExtendedStackTraceElement");

        try {
            options = parser.parse(args);
        } catch (OptionException var11) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, var11.getLocalizedMessage());
        }

        if (options != null && !options.has("?")) {
            if (options.has("v")) {
                System.out.println(CraftServer.class.getPackage().getImplementationVersion());
            } else {
                String path = (new File(".")).getAbsolutePath();
                if (path.contains("!") || path.contains("+")) {
                    System.err.println("Cannot run server in a directory with ! or + in the pathname. Please rename the affected folders and try again.");
                    return;
                }

                float javaVersion = Float.parseFloat(System.getProperty("java.class.version"));
                if ((double)javaVersion < 60.0D) {
                    System.err.println("Unsupported Java detected (" + javaVersion + "). This version of Minecraft requires at least Java 16. Check your Java version with the command 'java -version'.");
                    return;
                }

                if ((double)javaVersion > 61.0D) {
                    System.err.println("Unsupported Java detected (" + javaVersion + "). Only up to Java 17 is supported.");
                    if (!Boolean.getBoolean("Paper.IgnoreJavaVersion")) {
                        return;
                    }
                }

                try {
                    if (options.has("nojline")) {
                        System.setProperty("terminal.jline", "false");
                        useJline = false;
                    }

                    if (options.has("noconsole")) {
                        useConsole = false;
                        useJline = false;
                        System.setProperty("terminal.jline", "false");
                    }

                    if (Main.class.getPackage().getImplementationVendor() != null && System.getProperty("IReallyKnowWhatIAmDoingISwear") == null) {
                        Date buildDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z")).parse(Main.class.getPackage().getImplementationVendor());
                        Calendar deadline = Calendar.getInstance();
                        deadline.add(6, -28);
                        if (buildDate.before(deadline.getTime())) {
                            System.err.println("*** Warning, you've not updated in a while! ***");
                            System.err.println("*** Please download a new build as per instructions from https://papermc.io/downloads ***");
                        }
                    }

                    RuntimeMXBean runtimeMX = ManagementFactory.getRuntimeMXBean();
                    OperatingSystemMXBean osMX = ManagementFactory.getOperatingSystemMXBean();
                    if (runtimeMX != null && osMX != null) {
                        String var10000 = runtimeMX.getSpecVersion();
                        String javaInfo = "Java " + var10000 + " (" + runtimeMX.getVmName() + " " + runtimeMX.getVmVersion() + ")";
                        var10000 = osMX.getName();
                        String osInfo = "Host: " + var10000 + " " + osMX.getVersion() + " (" + osMX.getArch() + ")";
                        System.out.println("System Info: " + javaInfo + " " + osInfo);
                    } else {
                        System.out.println("Unable to read system info");
                    }

                    System.setProperty("library.jansi.version", "Paper");
                    System.out.println("Loading libraries, please wait...");
                    net.minecraft.server.Main.main(options);
                } catch (Throwable var12) {
                    var12.printStackTrace();
                }

                tryPreloadClass("com.destroystokyo.paper.util.SneakyThrow");
                tryPreloadClass("com.google.common.collect.Iterators$PeekingImpl");
                tryPreloadClass("com.google.common.collect.MapMakerInternalMap$Values");
                tryPreloadClass("com.google.common.collect.MapMakerInternalMap$ValueIterator");
                tryPreloadClass("com.google.common.collect.MapMakerInternalMap$WriteThroughEntry");
                tryPreloadClass("com.google.common.collect.Iterables");

                for(int i = 1; i <= 15; ++i) {
                    tryPreloadClass("com.google.common.collect.Iterables$" + i, false);
                }

                tryPreloadClass("org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableBoolean");
                tryPreloadClass("org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt");
                tryPreloadClass("org.bukkit.craftbukkit.libs.jline.terminal.impl.MouseSupport");
                tryPreloadClass("org.bukkit.craftbukkit.libs.jline.terminal.impl.MouseSupport$1");
                tryPreloadClass("org.bukkit.craftbukkit.libs.jline.terminal.Terminal$MouseTracking");
                tryPreloadClass("co.aikar.timings.TimingHistory");
                tryPreloadClass("co.aikar.timings.TimingHistory$MinuteReport");
                tryPreloadClass("io.netty.channel.AbstractChannelHandlerContext");
                tryPreloadClass("io.netty.channel.AbstractChannelHandlerContext$11");
                tryPreloadClass("io.netty.channel.AbstractChannelHandlerContext$12");
                tryPreloadClass("io.netty.channel.AbstractChannel$AbstractUnsafe$8");
                tryPreloadClass("io.netty.util.concurrent.DefaultPromise");
                tryPreloadClass("io.netty.util.concurrent.DefaultPromise$1");
                tryPreloadClass("io.netty.util.internal.PromiseNotificationUtil");
                tryPreloadClass("io.netty.util.internal.SystemPropertyUtil");
                tryPreloadClass("org.bukkit.craftbukkit.v1_17_R1.scheduler.CraftScheduler");
                tryPreloadClass("org.bukkit.craftbukkit.v1_17_R1.scheduler.CraftScheduler$1");
                tryPreloadClass("org.bukkit.craftbukkit.v1_17_R1.scheduler.CraftScheduler$2");
                tryPreloadClass("org.bukkit.craftbukkit.v1_17_R1.scheduler.CraftScheduler$3");
                tryPreloadClass("org.bukkit.craftbukkit.v1_17_R1.scheduler.CraftScheduler$4");
                tryPreloadClass("org.slf4j.helpers.MessageFormatter");
                tryPreloadClass("org.slf4j.helpers.FormattingTuple");
                tryPreloadClass("org.slf4j.helpers.BasicMarker");
                tryPreloadClass("org.slf4j.helpers.Util");
                tryPreloadClass("com.destroystokyo.paper.event.player.PlayerConnectionCloseEvent");
                tryPreloadClass("com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent");
                tryPreloadClass(Void.class.getName());
                tryPreloadClass(LightEngineLayerEventListener.class.getName());
                tryPreloadClass(ExceptionSuppressor.class.getName());
            }
        } else {
            try {
                parser.printHelpOn(System.out);
            } catch (IOException var10) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String)null, var10);
            }
        }

        springConfig = new SpringConfig();
        class Spring extends Thread {
            @Override
            public void run() {
                try {
                    org.springframework.boot.loader.JarLauncher.main(new String[0]);
                    while (true){
                        Thread.sleep(15000);
                        if(Bukkit.isStopping());
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    SpringWebServer.disable();
                }
            }
        }
        Thread thread = new Spring();
        thread.start();



    }

    private static void tryPreloadClass(String className) {
        tryPreloadClass(className, true);
    }

    private static void tryPreloadClass(String className, boolean printError) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException var3) {
            if (printError) {
                System.err.println("An expected class  " + className + " was not found for preloading: " + var3.getMessage());
            }
        }

    }

    private static List<String> asList(String... params) {
        return Arrays.asList(params);
    }
}
