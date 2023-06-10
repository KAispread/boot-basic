package hello.order.v0;

import hello.order.OrderService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV0 implements OrderService {
    // 멀티쓰레드 상황에서 값을 안전하게 증가, 감소할 수 있음
    private AtomicInteger stock = new AtomicInteger();

    @Override
    public void order() {
        log.info("주문");
        stock.incrementAndGet();
    }

    @Override
    public void cancel() {
        log.info("취소");
        stock.decrementAndGet();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
