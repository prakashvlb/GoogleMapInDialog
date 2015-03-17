package com.dp.goodmaptesting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class TestFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_layout, container, false);

		Button button = (Button) view.findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MapDialog dialogFragment = new MapDialog();
				Bundle args = new Bundle();
				args.putString("LocationAddress", "Houston");// no i18n
				dialogFragment.setArguments(args);
				dialogFragment.show(getChildFragmentManager(), "map");
			}
		});

		return view;
	}
}
