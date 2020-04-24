package com.xuchengpu.myplugin;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 许成谱 on 2019/7/2 23:53.
 * qq:1550540124
 * for:热爱生活每一天！
 */
public class FileUtils {
    /**
     * 复制单个文件到缓存目录
     *
     * @param fileName String 要复制的文件名 如：abc.txt
     * @return <code>true</code> if and only if the file was copied; <code>false</code> otherwise
     */
    private boolean copyAssetsSingleFile(Context context, String fileName) {
        File file = context.getCacheDir();
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("--Method--", "copyAssetsSingleFile: cannot create directory.");
                return false;
            }
        }
        try {
            InputStream inputStream =context. getAssets().open(fileName);
            File outFile = new File(file, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            // Transfer bytes from inputStream to fileOutputStream
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = inputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
