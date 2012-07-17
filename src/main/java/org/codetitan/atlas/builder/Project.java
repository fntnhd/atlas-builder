package org.codetitan.atlas.builder;

/**
 *
 * @author Andrews
 */
public class Project {
    
    private String name;
    private String namespace;
    private String path;
    private String archetype;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }
    
    public String getBasePackage() {
        return getNamespace().replaceAll("\\.", "/");
    }
}
