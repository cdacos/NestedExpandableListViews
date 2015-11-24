package com.example.acme.nestedlistviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.nestedlistviews.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Object  obj = new Object();
    obj.children =  new ArrayList<Object>();
    for(int i = 0;i<Constant.state.length;i++)
    {
      Object root =  new Object();
      root.title = Constant.state[i];
      root.children =  new ArrayList<Object>();
      for(int j=0;j<Constant.parent[i].length;j++)
      {
        Object parent =  new Object();
        parent.title=Constant.parent[i][j];
        parent.children =  new ArrayList<Object>();
        for(int k=0;k<Constant.child[i][j].length;k++)
        {
          Object child =  new Object();
          child.title =Constant.child[i][j][k];
          parent.children.add(child);
        }
        root.children.add(parent);
      }
      obj.children.add(root);
    }

    if (!obj.children.isEmpty()) {
      final ExpandableListView elv = (ExpandableListView) findViewById(R.id.expList);

      elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {
          return false/* or false depending on what you need */;
        }
      });


      ExpandableListView.OnGroupClickListener grpLst = new ExpandableListView.OnGroupClickListener() {
        @Override
        public boolean onGroupClick(ExpandableListView eListView, View view, int groupPosition,
                                    long id) {
          return false/* or false depending on what you need */;
        }
      };


      ExpandableListView.OnChildClickListener childLst = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView eListView, View view, int groupPosition,
                                    int childPosition, long id) {
          return false/* or false depending on what you need */;
        }
      };

      ExpandableListView.OnGroupExpandListener grpExpLst = new ExpandableListView.OnGroupExpandListener() {
        @Override
        public void onGroupExpand(int groupPosition) {

        }
      };

      final RootAdapter adapter = new RootAdapter(this, obj, grpLst, childLst, grpExpLst);
      elv.setAdapter(adapter);
    }
  }
}