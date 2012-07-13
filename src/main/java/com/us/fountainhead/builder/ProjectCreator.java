package com.us.fountainhead.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

/**
 * Sets up the file structure and build files for a project
 * 
 * @author Andrews
 */
public class ProjectCreator {
    
    private static final String DELIMITER = "/";
    private static final String ZIP = ".zip";
    private static final String PROJECT_TEMPLATE_URL = "https://s3.amazonaws.com/atlas.mda.project.templates/";
    private static final String META_PATH = "/meta";
    private static final String TEMPLATES_PATH = "/meta/templates";
    private String templatePath;
    private String metaPath;
    private Project project;
    private Context context;

    public void create(Project project) {
        this.project=project;
        setupProject();
        generateTargets();
        File meta = new File(metaPath);
        if(meta.exists() && meta.isDirectory()) {
            try {
                FileUtils.deleteDirectory(meta);
            } catch (IOException ex) {
                Logger.getLogger(ProjectCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void setupProject() {
        try {
            URL url = new URL(PROJECT_TEMPLATE_URL + project.getArchetype() + ZIP);
            File target = new File(project.getPath());
            Unzipper.unpackArchive(url, target);
            String projectPath = target.getAbsolutePath();
            templatePath = projectPath + TEMPLATES_PATH;
            metaPath = projectPath + META_PATH;
            context = new Context(metaPath);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProjectCreator.class.getName()).log(Level.SEVERE, "No such archetype: " + project.getArchetype(), ex);
        } catch (IOException ex) {
            Logger.getLogger(ProjectCreator.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    private void generateTargets() {
        FileWriter fw = null;
        TemplateManager templateManager = new TemplateManager(templatePath);
        List<Target> targets = context.getTargets();

        try {
            for (Target target : targets) {  
                String outputFilePath = project.getPath() + DELIMITER + target.getOutputPath();
                Logger.getLogger(Context.class.getName()).log(Level.INFO, "Generating  {0}/{1} using template {2}" , new Object[]{outputFilePath, target.getOutputFile(), target.getTemplate()});
                File file = new File(outputFilePath, target.getOutputFile());
                file.getParentFile().mkdirs();
                fw = new FileWriter(file, false);
                VelocityContext vc = new VelocityContext();
                vc.put("p", project);
                Template template = templateManager.getTemplate(target.getTemplate());
                template.merge(vc, fw);
                fw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ProjectCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
