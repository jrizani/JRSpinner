package jrizani.jrspinner;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/*=============================*/
/*            AUTHOR           */
/*          JULIAN NR          */
/* juliannoorrizani@gmail.com  */
/*         20 Feb 2019         */
/*=============================*/

/**
 * custom view that used as spinner
 */
public class JRSpinner extends android.support.v7.widget.AppCompatEditText {

    /**
     * all items text to show in spinner dialog
     */
    private String[] items;
    /**
     * tint of expand icon
     */
    private int expandTint;
    /**
     * the title of spinner dialog
     */
    private String title = "Select";
    /**
     * listener to listen when item click (used when use non multiple spinner)
     */
    private OnItemClickListener onItemClickListener;
    /**
     * icon of expand
     */
    private Drawable expandDrawable;
    /**
     * selected position of non multiple spinner
     */
    private int selected = -1;
    /**
     * the field to know that this spinner is multiple or no
     */
    private boolean multiple = false;
    /**
     * selected position of multiple listener
     */
    private List<Integer> multipleSelected = new ArrayList<>();
    /**
     * listener to listen when multiple item selected (used when use multiple spinner)
     */
    private JRSpinner.OnSelectMultipleListener onSelectMultipleListener;

    public JRSpinner(Context context) {
        super(context);
        init(null);
    }

    public JRSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public JRSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * method that initialize the view
     *
     * @param attrs attribute of view
     */
    private void init(AttributeSet attrs) {
        setLongClickable(false);
        setFocusable(false);
        setSingleLine(true);
        expandTint = ContextCompat.getColor(getContext(), R.color.jrspinner_color_default);
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.JRSpinner);
            if (typedArray != null) {
                title = typedArray.getString(R.styleable.JRSpinner_jrs_title) == null ? "Select" : typedArray.getString(
                        R.styleable.JRSpinner_jrs_title);
                expandTint = typedArray.getColor(R.styleable.JRSpinner_jrs_icon_tint, expandTint);
                multiple = typedArray.getBoolean(R.styleable.JRSpinner_jrs_multiple, false);
                typedArray.recycle();
            }
        }
        expandDrawable = ContextCompat.getDrawable(getContext(), R.drawable.jrspinnericon_expand);
        expandDrawable.setColorFilter(new PorterDuffColorFilter(expandTint, PorterDuff.Mode.SRC_IN));
        setIcon();
    }

    /**
     * method to set the icon of expand
     */
    private void setIcon() {
        Drawable[] drawables = getCompoundDrawables();

        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], expandDrawable, drawables[3]);
    }

    /**
     * method to set tint of expand icon
     *
     * @param expandTint this is the tint
     */
    public void setExpandTint(int expandTint) {
        this.expandTint = expandTint;
        postInvalidate();
    }

    /**
     * method to set the item to show in spinner dialog
     *
     * @param items all items to show
     */
    public void setItems(String[] items) {
        this.items = items;
        postInvalidate();
    }

    /**
     * method to set the title of spinner dialog
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
        postInvalidate();
    }

    /**
     * method to set use multiple spinner or no
     *
     * @param multiple use multiple spinner or no
     */
    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    /**
     * add the item click listener when use non multiple spinner
     *
     * @param onItemClickListener the listener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * add the select multiple item listener when use multiple spinner
     *
     * @param onSelectMultipleListener the listener
     */
    public void setOnSelectMultipleListener(OnSelectMultipleListener onSelectMultipleListener) {
        this.onSelectMultipleListener = onSelectMultipleListener;
    }

    /**
     * it is used to disabled auto spelling check
     *
     * @return suggestion is enabled
     */
    @Override
    public boolean isSuggestionsEnabled() {
        return false;
    }

    /**
     * call when property change by method
     */
    @Override
    public void postInvalidate() {
        super.postInvalidate();
        expandDrawable.setColorFilter(new PorterDuffColorFilter(expandTint, PorterDuff.Mode.SRC_IN));
        setIcon();
    }

    /**
     * set the selected item position when use non multiple spinner
     *
     * @param selected the position
     */
    protected void setSelected(int selected) {
        this.selected = selected;
    }


    /**
     *  set selected position. Can use for set default selected position
     * @param position selected position
     */
    public void select(int position){
        if (!multiple){
            selected = position;
            setText(items[position]);
        }else{
            multipleSelected.add(position);
            setText(items[position]);
        }

        if (onItemClickListener != null){
            onItemClickListener.onItemClick(selected);
        }

        if (onSelectMultipleListener != null){
            onSelectMultipleListener.onMultipleSelected(multipleSelected);
        }
    }

    /**
     * call when click on spinner view and show the dialog
     */
    @Override
    public boolean performClick() {
        if (!multiple) {
            Dialog dialog = Dialog.newInstance(title, items, selected);
            dialog.setListener(onItemClickListener, JRSpinner.this);
            dialog.show(findActivity(getContext()).getSupportFragmentManager(), dialog.getTag());
        } else {
            MultipleDialog dialog = MultipleDialog.newInstance(title, items, multipleSelected);
            dialog.setListener(onSelectMultipleListener, JRSpinner.this);
            dialog.show(findActivity(getContext()).getSupportFragmentManager(), dialog.getTag());
        }
        return super.performClick();
    }

    /**
     * method to clear selected item(s)
     */
    public void clear() {
        if (multiple) {
            multipleSelected.clear();
        } else {
            selected = -1;
        }
        setText("");
    }

    /**
     * method to get activity of the view
     *
     * @param context context where view found
     * @param <T>     the return must be extend FragmentActivity
     * @return object that extend FragmentActivity
     */
    public static <T extends FragmentActivity> T findActivity(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }

        if (context instanceof FragmentActivity) {
            return (T) context;
        } else {
            ContextWrapper contextWrapper = (ContextWrapper) context;
            Context baseContext = contextWrapper.getBaseContext();
            if (baseContext == null) {
                throw new IllegalStateException("Activity was not found as base context of view!");
            }
            return findActivity(baseContext);
        }
    }

    /**
     * set the selected items position when use multiple spinner
     *
     * @param selected selected positions
     */
    protected void setSelected(List<Integer> selected) {
        multipleSelected.clear();
        multipleSelected.addAll(selected);
    }

    /**
     * the item click listener
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * the select multiple listener
     */
    public interface OnSelectMultipleListener {
        void onMultipleSelected(List<Integer> selectedPosition);
    }
}
