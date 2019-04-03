package tema43;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Ejercicio1 {

    private static ConcurrentMap<String, String> duplicates = new ConcurrentHashMap<String, String>();
    private static Object screenLock = new Object();

    public static void findDuplicates(File root) {
        if (root.isDirectory()) {
            for (File file : root.listFiles()) {
                if (file.isDirectory()) {
                    findDuplicates(file);
                } else {

                    String path = duplicates.putIfAbsent(file.getName(), file.getAbsolutePath());

                    if(path != null){
                        synchronized (screenLock) {
                            System.out.println("Found duplicate file: "
                                    + file.getName());
                            System.out.println("    " + path);
                            System.out.println("    " + file.getAbsolutePath());
                        }
                    }

					/*String path = duplicates.get(file.getName());
					if (path == null) {
						duplicates.put(file.getName(), file.getAbsolutePath());
					} else {
						System.out.println("Found duplicate file: "
								+ file.getName());
						System.out.println("    " + path);
						System.out.println("    " + file.getAbsolutePath());
					}*/
                }
            }
        }
    }

    public static void main(String[] args) {

        File root = new File(
                ".");

        File[] files = root.listFiles();

        for (final File file : files){
            new Thread(() -> findDuplicates(file)).start();
        }
    }
}
