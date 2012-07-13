package com.us.fountainhead.builder;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * Responsible for getting templates into the transformation engine
 *
 * @author andrews
 */
public class TemplateManager {

    private static final String DELIMITER = "/";
    private VelocityEngine velocityEngine = null;
    private String templatePath;

    public TemplateManager(String templatePath) {
        this.templatePath = templatePath;
    }

    private VelocityEngine getEngine() {
        if (velocityEngine == null) {
            try {
                Properties props = new Properties();
                props.setProperty("resource.loader", "file");
                props.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
                props.setProperty("file.resource.loader.cache", "true");
                props.setProperty("file.resource.loader.path", templatePath);
                velocityEngine = new VelocityEngine();
                velocityEngine.init(props);
            } catch (Exception ex) {
                Logger.getLogger(TemplateManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return velocityEngine;
    }

    public Template getTemplate(String templateName) {
        Template template = null;

        try {
            template = getEngine().getTemplate(templateName);
        } catch (ResourceNotFoundException ex) {
            Logger.getLogger(TemplateManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseErrorException ex) {
            Logger.getLogger(TemplateManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TemplateManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return template;
    }
}
