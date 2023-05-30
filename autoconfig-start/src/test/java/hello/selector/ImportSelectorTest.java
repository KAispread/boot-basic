package hello.selector;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

public class ImportSelectorTest {
    // 특정 Bean 만 Import
    @Configuration
    @Import(HelloConfig.class)
    public static class StaticConfig {

    }
    @Test
    void staticConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StaticConfig.class);
        HelloBean bean = context.getBean(HelloBean.class);
        assertThat(bean).isNotNull();
    }

    // ImportSelector 사용
    // ImportSelector 의 메서드를 실행하여 해당 설정정보를 사용함
    @Configuration
    @Import(HelloImportSelector.class)
    public static class SelectorConfig {

    }
    @Test
    void selectorConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SelectorConfig.class);
        HelloBean bean = context.getBean(HelloBean.class);
        assertThat(bean).isNotNull();
    }
}
