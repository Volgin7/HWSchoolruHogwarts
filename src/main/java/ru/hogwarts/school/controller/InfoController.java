package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.AvatarService;

import java.util.stream.Stream;

@RequestMapping("/info")
@RestController
public class InfoController {
    @Value("${server.port}")
    private int port;

    @GetMapping("/getPort")
    public int getPort () {
        return port;
    }


    @GetMapping("/getSum")
    public long getSum () {
        Long  sum = Long.valueOf(Stream.iterate(1, a-> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a,b) -> a + b));

        return sum;
    }
}