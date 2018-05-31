package com.thehecklers.edgeservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RefreshScope
@RestController
public class EdgeController {
    private String coffee;

    public EdgeController(@Value("${coffee:'Bottom of the Pot}") String coffee) {
        this.coffee = coffee;
    }

    @GetMapping("/houseblend")
    public Mono<Coffee> getHouseBlend() {
        return Mono.just(new Coffee("000houseblend999", coffee));
    }
}
