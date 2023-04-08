package org.khasanof.springworkingyaml;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyApplication implements CommandLineRunner {

    private final YAMLConfig yamlConfig;
    private final MyBean myBean;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("yamlConfig.getEnvironment() = " + yamlConfig.getEnvironment());
        System.out.println("yamlConfig.getName() = " + yamlConfig.getName());
        System.out.println("yamlConfig.getServers() = " + yamlConfig.getServers());
        System.out.println("yamlConfig.isEnabled() = " + yamlConfig.isEnabled());

        System.out.println("myBean = " + myBean);
    }


}
