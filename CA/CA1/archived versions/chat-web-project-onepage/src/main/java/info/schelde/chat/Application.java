package info.schelde.chat;

/**
 * Created by scheldejonas on 20/02/17.
 */
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setAdditionalProfiles("dev");
        app.run(args);
    }
}

