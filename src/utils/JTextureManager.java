

package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author yew_mentzaki & whizzpered
 */
public class JTextureManager {
    public static ArrayList<JLoadTask> load(){
        ArrayList<JLoadTask> jlt = getTasks(new File("res/textures"));
        textures = new Texture[jlt.size()];
        names = new String[jlt.size()];
        return jlt;
    }
    private static ArrayList<JLoadTask> getTasks(File file){
        File[] files = file.listFiles();
        ArrayList<JLoadTask> jlt = new ArrayList<JLoadTask>();
        for (File f : files) {
            if(f.isDirectory()){
                for (JLoadTask task : getTasks(f)) {
                    jlt.add(task);
                }
            }else{
                final File imageFile = f;
                
                jlt.add(new JLoadTask("Loading texture \"" + imageFile.getAbsolutePath().replace(new File("res/textures").getAbsolutePath()+System.getProperty("file.separator"), "") + "\"...") {

                    @Override
                    public void load() {
                        loadImage(imageFile);  
                    }
                });
            }
        }
       
        return jlt;
    }
    
    private static int number = 0;
    private static Texture[] textures;
    private static String[] names;
    public static Texture getTexture(String name){
        
        for (int i = 0; i < names.length; i++) {
            if(name.equals(names[i]))return textures[i];
        }
        return null;
    }
    public static void loadImage(File image){
        String shortName = image.getAbsolutePath().replace(new File("res/textures").getAbsolutePath()+System.getProperty("file.separator"), "");
        String format = shortName.substring(shortName.lastIndexOf(".")+1, shortName.length()).toUpperCase();
        try {
            textures[number] = TextureLoader.getTexture(format, new FileInputStream(image));
            textures[number].setTextureFilter(GL11.GL_NEAREST);
            names[number] = shortName.replace(System.getProperty("file.separator"), "/");
        } catch (IOException ex) {
            
        }
        number++;
    } 
}
