package ru.filislav.spring.SpringMVCApp.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MySpringMVCAppServlerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //два следующих метода определяют фильт для мониторинга POST запросов на наличие PATCH,PUT,DELETE
    //т.к. html5 не поддерживает работу жтих методо а поддерживает только GET,POST и потом если он
    //находит скрытое поле в таком методе то он рпеобразует его в PATCH и т.п.
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException{
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }
    private void registerHiddenFieldFilter(ServletContext aContext){
        aContext.addFilter("hiddenHttpMethodFilter",new HiddenHttpMethodFilter())
                .addMappingForUrlPatterns(null,true,"/*");
        //т.к. в urlPatterns стоит /* то фильтр будет работать для все адресов в нашем приложении
    }
}
