package org.thejavaguy;

import com.beust.jcommander.JCommander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger("App");

    public App() {
    }

    public static void main(String[] args) {
        LOGGER.debug("Hello World! START");
        App app = new App();
        Args objArgs = new Args();
        new JCommander(objArgs, args);
        System.out.println(objArgs.getVerbose() + " " + objArgs.getGroups());
    }
}
