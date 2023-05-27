package hello.embed;

import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class EmbedTomcatSpringMain {
    public static void main(String[] args) throws LifecycleException {
        System.out.println("EmbedTomcatSpringMain.main");

        // Tomcat
        Tomcat tomcat = new Tomcat();

        // Connector setting
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);
        
        // Spring Container
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        // Spring MVC 디스패처 서블릿 생성, 스프링 컨테이너 연결
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        // Dispatcher Servlet 등록
        Context context = tomcat.addContext("", "/");

        //== 코드 추가 시작==
        File docBaseFile = new File(context.getDocBase());

        if (!docBaseFile.isAbsolute()) {

            docBaseFile = new File(((org.apache.catalina.Host) context.getParent()).getAppBaseFile(), docBaseFile.getPath());

        }

        docBaseFile.mkdirs();
        //== 코드 추가 종료==

        tomcat.addServlet("", "dispatcher", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcher");

        tomcat.start();
    }
}
