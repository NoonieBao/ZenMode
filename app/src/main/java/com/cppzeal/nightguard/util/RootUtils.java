package com.cppzeal.nightguard.util;
import android.util.Log;
import java.io.DataOutputStream;
import java.io.IOException;
public class RootUtils {
    //Execute root shell
    public static boolean executeCommand(String command) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
            Log.i("TAG", "executeCommand: FS");
            return true; // 执行成功
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false; // 执行失败
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
