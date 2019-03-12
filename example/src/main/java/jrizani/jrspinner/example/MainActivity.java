package jrizani.jrspinner.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import jrizani.jrspinner.JRSpinner;

public class MainActivity extends AppCompatActivity {

    private JRSpinner mJRSpinner;
    private TextView mSelectedIndex;
    private Button mBtnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJRSpinner = findViewById(R.id.JRSpinner);
        mSelectedIndex = findViewById(R.id.selectedIndex);
        mBtnReset = findViewById(R.id.btn_reset);

        mJRSpinner.setItems(getResources().getStringArray(R.array.tesItems));
        mJRSpinner.setTitle("Choose jrspinner_item programmatically");
        mJRSpinner.setExpandTint(R.color.jrspinner_color_default);

//        mJRSpinner.setOnItemClickListener(new JRSpinner.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                mSelectedIndex.setText(position + "");
//            }
//        });

        mJRSpinner.setOnSelectMultipleListener(new JRSpinner.OnSelectMultipleListener() {
            @Override
            public void onMultipleSelected(List<Integer> selectedPosition) {
                StringBuilder text = new StringBuilder();
                for (int item: selectedPosition){
                    text.append(item).append(" | ");
                }
                mSelectedIndex.setText(text);
            }
        }); //use this listener instead if you use multiple

        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mJRSpinner.clear();
                mSelectedIndex.setText("");
            }
        });
    }
}
