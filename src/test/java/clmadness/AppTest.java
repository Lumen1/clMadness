package clmadness;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void clCacheTest() throws Exception {
        ClassLoader thisCL = getClass().getClassLoader();

        File jar1 = new File(thisCL.getResource("1.jar").getFile());
        File jar2 = new File(thisCL.getResource("2.jar").getFile());

        URLClassLoader cl1 = new URLClassLoader(new URL[] {jar1.toURI().toURL()}, thisCL);
        URLClassLoader cl2 = new URLClassLoader(new URL[] {jar2.toURI().toURL()}, thisCL);

        Class c1 = Class.forName("clmadness.Mad", true, cl1);
        Class c2 = Class.forName("clmadness.Mad", true, cl2);

        Method m1 = c1.getDeclaredMethod("meth");
        Method m2 = c2.getDeclaredMethod("meth");

        System.out.println(m1.invoke(c1.newInstance()));
        System.out.println(m2.invoke(c2.newInstance()));
    }

}
