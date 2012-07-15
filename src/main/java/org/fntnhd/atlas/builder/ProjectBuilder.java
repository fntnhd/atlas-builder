package org.fntnhd.atlas.builder;

/**
 *
 * @author Andrews
 */
public class ProjectBuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length != 4) {
            System.out.println("Required arguments:");
            System.out.println("name: Abbreviated name for your app.");
            System.out.println("namespace: Namespace for your app.");
            System.out.println("path: Location for your app.");
            System.out.println("archetype: Archetype for your app.");
            
            System.out.println("Example: sample com.mycompany.sample /Users/Bubba/Projects/sample ea-spring-gwt");
            
            return;
        }
        
        
        Project project = new Project();
        project.setName(args[0]);
        project.setNamespace(args[1]);
        project.setPath(args[2]);
        project.setArchetype(args[3]);
        
        new ProjectCreator().create(project);
        
    }
}
