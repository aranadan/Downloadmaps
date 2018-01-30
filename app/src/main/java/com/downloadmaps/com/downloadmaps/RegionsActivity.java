package com.downloadmaps.com.downloadmaps;

import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;

public class RegionsActivity extends AppCompatActivity {
    TextView freeMemoryString;
    ProgressBar freeMemoryBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regions);
        Log.e("INT MEM", getAvailableInternalMemorySize());
        Log.e("TOTAL MEM", getTotalInternalMemorySize());

        freeMemoryString = (TextView) findViewById(R.id.free_memory);
        freeMemoryString.setText(getString(R.string.free_memory,getAvailableInternalMemorySize()));

        freeMemoryBar = (ProgressBar) findViewById(R.id.free_memory_bar);
        freeMemoryBar.setMax(10);
        freeMemoryBar.setProgress(9);
    }

    public static String getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return formatSize(availableBlocks * blockSize);
    }

    public static String getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        return formatSize(totalBlocks * blockSize);
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
