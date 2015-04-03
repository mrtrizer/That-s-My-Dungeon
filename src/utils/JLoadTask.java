
package utils;

/**
 *
 * @author yew_mentzaki & whizzpered
 */
public abstract class JLoadTask {
  
    String name;

    public JLoadTask(String name) {
        this.name = name;
    }

    public abstract void load();
}
