package com.test.pages;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.OutputFrame;

import java.util.function.Consumer;

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

