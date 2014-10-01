package com.aphidmobile.flip.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.aphidmobile.flip.FlipViewController;
import com.aphidmobile.flip.demo.adapter.TravelAdapter;
import com.aphidmobile.flipview.demo.R;


public class FlipDynamicAdapterActivity extends Activity {

  private FlipViewController flipView;

  private TravelAdapter adapter;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setTitle(R.string.activity_title);

    flipView = new FlipViewController(this);

    adapter = new TravelAdapter(this);
    flipView.setAdapter(adapter);

    flipView.setOnViewFlipListener(new FlipViewController.ViewFlipListener() {
      @Override
      public void onViewFlipped(View view, int position) {
        if (position == adapter.getCount() - 1) {//expand the data size on the last page 
          adapter.setRepeatCount(adapter.getRepeatCount() + 1);
          adapter.notifyDataSetChanged();
        }
      }
    });

    setContentView(flipView);
  }

  @Override
  protected void onResume() {
    super.onResume();
    flipView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    flipView.onPause();
  }
}
