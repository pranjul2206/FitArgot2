package in.ac.ksit.android.fitargot.Util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class PermissionUtil {
    Context context;
    private  static PermissionUtil permissionObject=null;
    private PermissionUtil(Context context){
            this.context=context;
    }

    public static PermissionUtil getInstance(Context context) {
        if(permissionObject==null){
               synchronized (PermissionUtil.class){
                   if(permissionObject==null)
                       permissionObject=new PermissionUtil(context);
               }
        }
        return permissionObject;
     }




    public  ArrayList<String> permissionsToRequest(ArrayList<String> wantedPermissions) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wantedPermissions) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;

    }

    public  boolean checkPermissions(String permission)
    {

        if (ContextCompat.checkSelfPermission(context,
                permission) == PackageManager.PERMISSION_GRANTED) {
            return true;

        }else
            return false;
    }

    private  boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkPermissions(permission);
        }
        return true;
    }
}
