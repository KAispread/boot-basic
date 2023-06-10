package hello.order.v3;

import hello.order.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
public class OrderServiceV3 implements OrderService {
    // 멀티쓰레드 상황에서 값을 안전하게 증가, 감소할 수 있음
    private final MeterRegistry registry;
    private AtomicInteger stock = new AtomicInteger();

    @Override
    public void order() {
        Timer timer = Timer.builder("my.order")
                .tags("class", this.getClass().getName())
                .tag("method", "order")
                .description("order")
                .register(registry);

        timer.record(() -> {
            log.info("주문");
            stock.incrementAndGet();
            sleep(200);
        });
    }

    @Override
    public void cancel() {
        Timer timer = Timer.builder("my.order")
                .tags("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("order")
                .register(registry);

        timer.record(() -> {
            log.info("취소");
            stock.decrementAndGet();
            sleep(100);
        });
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }

    private void sleep(int l) {
        try {
            Thread.sleep(500 + new Random().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
