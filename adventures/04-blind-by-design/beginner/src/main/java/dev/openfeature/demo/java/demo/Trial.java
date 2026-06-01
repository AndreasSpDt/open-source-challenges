package dev.openfeature.demo.java.demo;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.FlagEvaluationDetails;
import dev.openfeature.sdk.OpenFeatureAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Trial {

    private final Client client;

    public Trial(OpenFeatureAPI openFeatureAPI) {
        this.client = openFeatureAPI.getClient();
    }

    @GetMapping("/")
    public FlagEvaluationDetails<String> evaluateVisionState() {
        FlagEvaluationDetails<String> details =
                client.getStringDetails("vision_state", "unknown");

        System.out.printf(
                "key=%s  variant=%s  value=%s  reason=%s%n",
                details.getFlagKey(),
                details.getVariant(),
                details.getValue(),
                details.getReason()
        );

        return details;
    }

    @GetMapping("/static")
    public String observeSubjectStatic() {
        // The lab is reading from a hard-coded label, not from the chart.
        // Wire OpenFeature in and resolve the "vision_state" flag from flags.json instead.
        return "untreated";
    }
}
