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

/*=============================*/
/*            AUTHOR           */
/*          JULIAN NR          */
/* juliannoorrizani@gmail.com  */
/*         20 Feb 2019         */
/*=============================*/

public class JRSpinner extends android.support.v7.widget.AppCompatEditText {

    private String[] items;
    private int expandTint;
    private String title = "Select";
    private OnItemClickListener onItemClickListener;
    private Drawable expandDrawable;
    private int selected = -1;

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
                typedArray.recycle();
            }
        }
        expandDrawable = ContextCompat.getDrawable(getContext(), R.drawable.jrspinnericon_expand);
        expandDrawable.setColorFilter(new PorterDuffColorFilter(expandTint, PorterDuff.Mode.SRC_IN));
        setIcon();
    }

    private void setIcon() {
        Drawable[] drawables = getCompoundDrawables();

        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], expandDrawable, drawables[3]);
    }

    public void setExpandTint(int expandTint) {
        this.expandTint = expandTint;
        postInvalidate();
    }

    public void setItems(String[] items) {
        this.items = items;
        postInvalidate();
    }

    public void setTitle(String title) {
        this.title = title;
        postInvalidate();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public boolean isSuggestionsEnabled() {
        return false;
    }

    @Override
    public void postInvalidate() {
        super.postInvalidate();
        expandDrawable.setColorFilter(new PorterDuffColorFilter(expandTint, PorterDuff.Mode.SRC_IN));
        setIcon();
    }

    protected void setSelected(int selected){
        this.selected = selected;
    }

    @Override
    public boolean performClick() {
        Dialog dialog = Dialog.newInstance(title, items, selected);
        dialog.setListener(onItemClickListener, JRSpinner.this);
        dialog.show(findActivity(getContext()).getSupportFragmentManager(), dialog.getTag());
        return super.performClick();
    }

    public void clear(){
        selected = -1;
        setText("");
    }

    public static <T extends FragmentActivity> T findActivity(Context context){
        if (context == null){
            throw new IllegalArgumentException("Context cannot be null");
        }

        if (context instanceof FragmentActivity){
            return (T) context;
        }else{
            ContextWrapper contextWrapper = (ContextWrapper) context;
            Context baseContext = contextWrapper.getBaseContext();
            if (baseContext == null){
                throw new IllegalStateException("Activity was not found as base context of view!");
            }
            return findActivity(baseContext);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
