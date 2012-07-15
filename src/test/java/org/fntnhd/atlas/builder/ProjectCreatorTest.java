package org.fntnhd.atlas.builder;

import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.fntnhd.atlas.builder.Project;
import org.fntnhd.atlas.builder.ProjectCreator;
import org.fntnhd.atlas.builder.Unzipper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrews
 */
public class ProjectCreatorTest {
    
    public ProjectCreatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createProject() {
        Project p = new Project();
        p.setName("sample");
        p.setNamespace("com.us.fountainhead.sample");
        p.setPath("target/sample");
        p.setArchetype("ea-spring-gwt");
    
        ProjectCreator pc = new ProjectCreator();
        pc.create(p);
    }
    
    @Test
    public void downloadZip() throws MalformedURLException, IOException  {
        URL url = new URL("https://s3.amazonaws.com/atlas.mda.project.templates/ea-spring-gwt.zip");
        Unzipper.unpackArchive(url, new File("target/unzip"));
    }
}
