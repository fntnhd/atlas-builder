package org.codetitan.atlas.builder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrews
 */
public class Context {

    private static final String TARGET = "context/targets/target";
    private static final String TEMPLATE = "template";
    private static final String OUTPUT_FILE = "outputFile";
    private static final String OUTPUT_PATH = "outputPath";
    private static final String ADD_TARGET = "addTarget";
    private static final String CONTEXT_FILE = "/context.xml";
    
    private List<Target> targets = new ArrayList<Target>();
     
    private static Context INSTANCE = null;

    public Context(String metaPath) {
        load(metaPath + CONTEXT_FILE);
    }
    
    
    private void load(String context) {
        try {
            InputStream s = new FileInputStream(context);
            Digester d = new Digester();
            d.push(this);
            d.addObjectCreate(TARGET, Target.class);
            d.addSetProperties(TARGET, new String[] {TEMPLATE, OUTPUT_FILE, OUTPUT_PATH}, new String[] {TEMPLATE, OUTPUT_FILE, OUTPUT_PATH});
            d.addSetNext(TARGET, ADD_TARGET);

            d.parse(s);

        } catch (IOException ex) {
            Logger.getLogger(Context.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Context.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addTarget(Target target) {
        targets.add(target);
    }

    public List<Target> getTargets() {
        return targets;
    }
}
