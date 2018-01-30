package com.downloadmaps.com.downloadmaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;


public class RegionsActivity extends AppCompatActivity {
    TextView freeMemoryString;
    ProgressBar freeMemoryBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regions);

        freeMemoryBar = (ProgressBar) findViewById(R.id.free_memory_bar);
        freeMemoryBar.setMax((int) DeviceMemory.getTotalBlocks());
        freeMemoryBar.setProgress((int) DeviceMemory.getFreeBlocks());
        freeMemoryString = (TextView) findViewById(R.id.free_memory);
        freeMemoryString.setText(getString(R.string.free_memory,DeviceMemory.formatSize(DeviceMemory.getAvailableInternalMemorySize())));

    }


}
