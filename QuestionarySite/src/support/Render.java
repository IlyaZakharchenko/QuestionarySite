package support;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;


public class Render {

    private static Configuration config;

    private static Configuration getConfig() throws IOException {
        if (config == null) {
            config = new Configuration(Configuration.VERSION_2_3_26);
            config.setDirectoryForTemplateLoading(new File("D:/IdeaProjects/InterviewSite/web/templates"));
            config.setDefaultEncoding("UTF-8");
            config.setLocale(Locale.US);
            config.setEncoding(Locale.US, "UTF-8");
            config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        }

        return config;
    }

    public static void render(HttpServletResponse response, Map<String, Object> context, String templateName) throws IOException, TemplateException {
        Configuration config = getConfig();
        Template template = config.getTemplate(templateName);
        response.setCharacterEncoding("UTF-8");
        template.process(context, response.getWriter());
    }
}
