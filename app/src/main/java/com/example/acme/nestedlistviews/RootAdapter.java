package com.example.acme.nestedlistviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.nestedlistviews.R;

import java.lang.*;

public class RootAdapter extends BaseExpandableListAdapter {
  private Object root;

  private final LayoutInflater inflater;

  public class Entry {
    public final NestedExpandableListView nestedExpandableListView;
    public final SecondLevelAdapter secondLevelAdapter;

    public Entry(NestedExpandableListView nestedExpandableListView, SecondLevelAdapter secondLevelAdapter) {
      this.nestedExpandableListView = nestedExpandableListView;
      this.secondLevelAdapter = secondLevelAdapter;
    }
  }

  public Entry[] firstLevelEntries;

  public RootAdapter(Context context, Object root, ExpandableListView.OnGroupClickListener grpLst,
                     ExpandableListView.OnChildClickListener childLst, ExpandableListView.OnGroupExpandListener grpExpLst) {
    this.root = root;
    this.inflater = LayoutInflater.from(context);

    firstLevelEntries = new Entry[root.children.size()];

    for (int i = 0; i < root.children.size(); i++) {
      final NestedExpandableListView celv = new NestedExpandableListView(context);
      SecondLevelAdapter adp = new SecondLevelAdapter(root.children.get(i),context);
      celv.setAdapter(adp);
      celv.setGroupIndicator(null);
      celv.setOnChildClickListener(childLst);
      celv.setOnGroupClickListener(grpLst);
      celv.setOnGroupExpandListener(grpExpLst);

      firstLevelEntries[i] = new Entry(celv, adp);
    }

  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return root.children.get(groupPosition);
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                           View convertView, ViewGroup parent) {
    // second level list
    return firstLevelEntries[groupPosition].nestedExpandableListView;
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return 1;
  }

  @Override
  public Object getGroup(int groupPosition) {
    return root.children.get(groupPosition);
  }

  @Override
  public int getGroupCount() {
    return root.children.size();
  }

  @Override
  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                           ViewGroup parent) {

    // first level
    View layout = convertView;
    GroupViewHolder holder;
    final Object item = (Object) getGroup(groupPosition);

    if (layout == null) {
      layout = inflater.inflate(R.layout.item_root, parent, false);
      holder = new GroupViewHolder();
      holder.title = (TextView) layout.findViewById(R.id.itemRootTitle);
      layout.setTag(holder);
    } else {
      holder = (GroupViewHolder) layout.getTag();
    }

    holder.title.setText(item.title.trim());

    return layout;
  }

  private static class GroupViewHolder {
    TextView title;
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return true;
  }
}