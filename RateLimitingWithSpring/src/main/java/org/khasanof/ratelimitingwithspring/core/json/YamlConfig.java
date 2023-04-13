package org.khasanof.ratelimitingwithspring.core.json;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/13/2023
 * <br/>
 * Time: 11:58 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.core.json
 */
@Getter
@Setter
@Service
public class YamlConfig {

    @Value("${api.limit.path}")
    private String path;

    @Autowired
    private Environment environment;

    private final YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();

    private List<ReadLimit> paths;

    public void run() throws IOException {
        Resource resource = new ClassPathResource(path);
        Properties properties = loadYamlProperties(resource);

        System.out.println("Yaml");

        StandardEnvironment environment = new StandardEnvironment();
        PropertySource<?> propertySource = new PropertiesPropertySource("yamlProperties", properties);
        environment.getPropertySources().addFirst(propertySource);

        String propertyValue = environment.getProperty("application.paths");
        System.out.println(propertyValue);
    }

    private static Properties loadYamlProperties(Resource resource) throws IOException {
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.load(resource.getInputStream());
        Properties properties = new Properties();
        flattenMap("", map, properties);
        return properties;
    }

    private static void flattenMap(String path, Map<String, Object> map, Properties properties) {
        map.forEach((key, value) -> {
            String propertyPath = StringUtils.hasText(path) ? path + "." + key : key;
            if (value instanceof Map) {
                flattenMap(propertyPath, (Map<String, Object>) value, properties);
            } else {
                properties.setProperty(propertyPath, String.valueOf(value));
            }
        });
    }
}
