package bg.softuni.movies.config;

import bg.softuni.movies.interceptor.PageVisitationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final LocaleChangeInterceptor localeChangeInterceptor;

    public InterceptorConfig(LocaleChangeInterceptor localeChangeInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
    }


    public void addInterceptor(InterceptorRegistry registry) {

        registry.addInterceptor(new PageVisitationInterceptor()).addPathPatterns("/movies");
        WebMvcConfigurer.super.addInterceptors(registry);

    }
}
