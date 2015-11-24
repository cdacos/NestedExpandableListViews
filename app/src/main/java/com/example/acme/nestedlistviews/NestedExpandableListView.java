package com.example.acme.nestedlistviews;

import android.content.Context;
import android.widget.ExpandableListView;

public class NestedExpandableListView extends ExpandableListView {
  public NestedExpandableListView(Context context) {
    super(context);

  }

  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // the value (2000) should not be fixed and be calculated
    // as follows: cell_height x root_items_count x root_items_children_count
    heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }
}