package com.test.pages;

import org.testcontainers.containers.GenericContainer;

public class TestInContainerWithHosts {

    public static void main(String[] args) {
        try (GenericContainer smokeTestContainer = new GenericContainer("testng-docker:latest")
//                .withExtraHost("", "")
                .withEnv("BROWSER", "chrome")
                .withEnv("LANGUAGE", "en")
                .withEnv("MODULE", "smoke.xml")
                .withNetworkAliases("default")
//                .withClasspathResourceMapping("/", "/", BindMode.READ_WRITE)
        ) {
            smokeTestContainer.start();
        }


    }
}

