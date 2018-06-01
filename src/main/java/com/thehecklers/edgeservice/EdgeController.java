package com.thehecklers.edgeservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RefreshScope
@RestController
public class EdgeController {
    private String coffee;
    private Source source;

    public EdgeController(@Value("${coffee:'Bottom of the Pot}") String coffee, Source source) {
        this.coffee = coffee;
        this.source = source;
    }

    @GetMapping("/houseblend")
    public Mono<Coffee> getHouseBlend() {
        return Mono.just(new Coffee("000houseblend999", coffee));
    }

    @PostMapping("/newcoffee")
    public void addCoffee(@RequestBody Coffee coffee) {
        source.output().send(MessageBuilder.withPayload(coffee).build());
    }
}
