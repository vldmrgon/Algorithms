package com.my.pro;

import java.io.OutputStream;
import java.util.*;

public class Configurator {

    public class BaseConfigElement {
    }

    public class ConfigElement extends BaseConfigElement {
    }

    public class DynamicConfigElement extends ConfigElement {
    }

    public class ConfigCreator {
        public OutputStream createConfig(List<? extends ConfigElement> elements) {
            return null;
        }
    }

    public class ConfigurationHelper {
        OutputStream createConfiguration(List<DynamicConfigElement> data) {
            ConfigCreator creator = new ConfigCreator();
            return creator.createConfig(data);
        }
    }

    public static String[] uniqueNames(String[] names1, String[] names2) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, names1);
        Collections.addAll(set, names2);
        return (String[]) set.toArray();
    }
}