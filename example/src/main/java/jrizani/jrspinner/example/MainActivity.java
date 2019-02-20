package jrizani.jrspinner.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import jrizani.jrspinner.JRSpinner;

public class MainActivity extends AppCompatActivity {

    private JRSpinner mJRSpinner;
    private TextView mSelectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJRSpinner = findViewById(R.id.JRSpinner);
        mSelectedIndex = findViewById(R.id.selectedIndex);

        mJRSpinner.setItems(getResources().getStringArray(R.array.tesItems));
        mJRSpinner.setTitle("Choose item programmatically");
        mJRSpinner.setExpandTint(R.color.color_default);

        mJRSpinner.setOnItemClickListener(new JRSpinner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mSelectedIndex.setText(position + "");
            }
        });
    }
}
