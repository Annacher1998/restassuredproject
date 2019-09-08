package com.app.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {"html:target/default-cucumber-reports",
                    "json:target/cucumber.json",
                    "rerun:target/rerun.txt"
            },

            features = "src/test/resources/com/rest/features",
            glue = "src/test/java/com/app/step_definitions",
            tags = "@ApiPost",
            dryRun = true

    )
    public class CukessRunner {
    }

