package jrizani.jrspinner;
/*=============================*/
/*            AUTHOR           */
/*          JULIAN NR          */
/* juliannoorrizani@gmail.com  */
/*         20 Feb 2019         */
/*=============================*/

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Pair<Integer, String>> items = new ArrayList<>();
    private List<Pair<Integer, String>> allItems = new ArrayList<>();
    private List<Pair<Integer, String>> tempItems = new ArrayList<>();
    private Listener listener;
    private int selected;

    public Adapter(Listener listener) {
        this.listener = listener;
    }

    public void update(String[] items, int selected) {
        this.selected = selected;
        this.items.clear();
        this.allItems.clear();
        for (int i = 0; i < items.length; i++) {
            this.items.add(new Pair<>(i, items[i]));
            this.allItems.add(new Pair<>(i, items[i]));
        }
    }

    public void update(String query) {
        Pair<Integer, String> selectedItem;
        for (Pair<Integer, String> item : allItems) {
            if (item.second.toLowerCase().contains(query.toLowerCase())) {
                tempItems.add(item);
            }
        }

        items.clear();
        items.addAll(tempItems);
        tempItems.clear();
        notifyDataSetChanged();
    }

    public void reset() {
        items.clear();
        items.addAll(allItems);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jrspinner_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Pair<Integer, String> item = items.get(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView label;
        private ImageView ivSelected;

        ViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
            ivSelected = itemView.findViewById(R.id.selected);
        }

        void bind(final Pair<Integer, String> item) {
            if (item.first == selected){
                ivSelected.setVisibility(View.VISIBLE);
            }else{
                ivSelected.setVisibility(View.GONE);
            }

            label.setText(item.second);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(item, item.first);
                }
            });
        }
    }

    interface Listener {
        void onClick(Pair<Integer, String> item, int position);
    }
}
