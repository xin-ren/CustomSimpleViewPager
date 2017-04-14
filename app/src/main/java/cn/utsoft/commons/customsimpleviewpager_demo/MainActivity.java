package cn.utsoft.commons.customsimpleviewpager_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ViewPagerDialog dialog;
    private UltraPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ViewPagerDialog(this);
        adapter = new UltraPagerAdapter(false);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(adapter);
            }
        });
    }
}
