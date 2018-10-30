package v2.trabalho.lfa.trabalho_lfav2;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Daniel Monteiro
 *
 * @since on 30/10/2018.
 */

public class Cache {

    public static void saveObjectCache(Context ctx, String key, Object object) throws Exception {
        FileOutputStream fos = ctx.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object getAtCache(Context ctx, String key) throws Exception {
        FileInputStream fis = ctx.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return ois.readObject();
    }

}
