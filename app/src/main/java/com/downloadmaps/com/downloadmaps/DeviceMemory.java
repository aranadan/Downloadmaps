package com.downloadmaps.com.downloadmaps;


import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;

public class DeviceMemory {
    private static File path = Environment.getDataDirectory();
    private static StatFs stat = new StatFs(path.getPath());
    private static long blockSize = stat.getBlockSizeLong();

    public static long getAvailableInternalMemorySize() {
        return getAvailableBlocks() * blockSize;
    }

    public static long getTotalInternalMemorySize() {

        return getTotalBlocks() * blockSize;
    }

    public static long getTotalBlocks(){
        return stat.getBlockCountLong();
    }
    public static long getAvailableBlocks(){
        return stat.getAvailableBlocksLong();
    }

    public static long getFreeBlocks(){
        return getTotalBlocks() - getAvailableBlocks();
    }

    public static String formatSize(double size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = " KB";
            size /= 1024;
            if (size >= 1024) {
                suffix = " MB";
                size /= 1024;
                if (size >= 1024) {
                    suffix = " GB";
                    size /= 1024;
                }
            }
        }

        String s = String.format("%.2f", size);
        if (suffix != null) s = s + suffix;
        return s;
    }
}
