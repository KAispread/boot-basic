package hello.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TrafficController {
    private final DataSource dataSource;
    private List<String> list = new ArrayList<>();

    // CPU 사용량 증가
    @GetMapping("/cpu")
    public String cpu() {
        log.info("cpu");
        long value = 0;

        for (long i = 0; i < 1000000000000L; i++) {
            value++;
        }

        return "ok value=" + value;
    }

    // jvm 메모리 사용량 증가
    @GetMapping("jvm")
    public String jvm() {
        log.info("jvm");

        for (int i = 0; i < 10000000L; i++) {
            list.add("hello jvm! " + i);
        }
        return "jvm = " + list.size();
    }

    // Connection Pool 반환 X
    @GetMapping("/jdbc")
    public String jdbc() throws SQLException {
        log.info("jdbc");
        Connection conn = dataSource.getConnection();
        log.info("connection info= {}", conn);

        // conn.close() -> 커넥션을 반환하지 않는다.
        return "ok";
    }

    // error log 급증
    @GetMapping("/error-log")
    public String errorLog() {
        log.error("error");
        return "error";
    }
}
