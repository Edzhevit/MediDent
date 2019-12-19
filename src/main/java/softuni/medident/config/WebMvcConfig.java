package softuni.medident.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.medident.web.interceptors.LogoInterceptor;
import softuni.medident.web.interceptors.TitleInterceptor;

public class WebMvcConfig implements WebMvcConfigurer {

    private final TitleInterceptor titleInterceptor;
    private final LogoInterceptor logoInterceptor;

    @Autowired
    public WebMvcConfig(TitleInterceptor titleInterceptor, LogoInterceptor logoInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.logoInterceptor = logoInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(titleInterceptor);
        registry.addInterceptor(logoInterceptor);
    }

}
