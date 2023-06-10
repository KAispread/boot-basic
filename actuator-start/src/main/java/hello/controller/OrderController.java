package hello.controller;

import hello.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {
    public final OrderService orderService;

    @PostMapping("/order")
    public String order() {
        log.info("order");
        orderService.order();
        return "order";
    }

    @PostMapping("/cancel")
    public String cancel() {
        log.info("cancel");
        orderService.cancel();
        return "cancel";
    }

    @GetMapping("/stock")
    public int stock() {
        log.info("stock");
        return orderService.getStock().get();
    }

}
