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

    /**
     * showed items in dialog
     */
    private List<Pair<Integer, String>> items = new ArrayList<>();
    /**
     * all items of the spinner
     */
    private List<Pair<Integer, String>> allItems = new ArrayList<>();
    /**
     * temporary items that not showed in dialog
     */
    private List<Pair<Integer, String>> tempItems = new ArrayList<>();
    /**
     * field to know is this multiple spinner or no
     */
    private boolean multiple;
    /**
     * the listener when item clicked
     */
    private Listener listener;
    /**
     * selected position when use non multiple spinner
     */
    private int selected;
    /**
     * selected positions when use multiple spinner
     */
    private List<Integer> multipleSelected;

    /**
     * the constructor to create adapter object
     * @param multiple property to know is this multiple spinner or no
     * @param listener the listener
     */
    public Adapter(boolean multiple, Listener listener) {
        this.multiple = multiple;
        this.listener = listener;
    }

    /**
     * method to store items of spinner when use non multiple spinner
     * @param items items of spinner
     * @param selected selected position
     */
    public void update(String[] items, int selected) {
        this.selected = selected;
        this.items.clear();
        this.allItems.clear();
        this.tempItems.clear();
        for (int i = 0; i < items.length; i++) {
            this.items.add(new Pair<>(i, items[i]));
            this.allItems.add(new Pair<>(i, items[i]));
        }
        notifyDataSetChanged();
    }

    /**
     * method to store items of spinner when use multiple spinner
     * @param items items of spinner
     * @param selected selected positions
     */
    public void update(String[] items, List<Integer> selected) {
        multipleSelected = new ArrayList<>(selected);
        this.items.clear();
        this.allItems.clear();
        this.tempItems.clear();
        for (int i = 0; i < items.length; i++) {
            this.items.add(new Pair<>(i, items[i]));
            this.allItems.add(new Pair<>(i, items[i]));
        }
        notifyDataSetChanged();
    }

    /**
     * method to update dialog when query of search change
     * @param query the query of search
     */
    public void update(String query) {
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

    /**
     * method to reset selected item(s)
     */
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

    /**
     * method to add selected items when use multiple spinner
     * @param selected the selected position
     */
    public void addSelect(int selected) {
        multipleSelected.add(selected);
        notifyDataSetChanged();
    }

    /**
     * method to remove selected items when use multiple spinner
     * @param selected the unSelected position
     */
    public void removeSelect(int selected) {
        for (int i = 0; i < multipleSelected.size(); i++) {
            if (multipleSelected.get(i).equals(selected)) {
                multipleSelected.remove(i);
                break;
            }
        }
        notifyDataSetChanged();
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
            if ((multiple && multipleSelected.contains(item.first)) || (!multiple && item.first == selected)) {
                ivSelected.setVisibility(View.VISIBLE);
            } else {
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
