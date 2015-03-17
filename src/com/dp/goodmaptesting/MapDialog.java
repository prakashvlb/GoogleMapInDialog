package com.dp.goodmaptesting;

import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapDialog extends DialogFragment {

	// Reference URL
	// https://code.google.com/p/gmaps-api-issues/issues/detail?id=4902
	private MapView mMapView;

	private GoogleMap googleMap;
	private String locationAddress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mMapView = new MapView(this.getActivity(), new GoogleMapOptions());
		this.mMapView.onCreate(savedInstanceState);

		Bundle bundle = getArguments();
		locationAddress = bundle.getString("LocationAddress");// no i18n
		System.out.println("------LocationAddress:" + locationAddress);

		googleMap = mMapView.getMap();
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		MapsInitializer.initialize(getActivity());

		LatLng lstLong = getLocationFromAddress(locationAddress, getActivity());
		if (lstLong != null) {
			googleMap.addMarker(new MarkerOptions().position(lstLong).title(locationAddress)).showInfoWindow();
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lstLong, 15));
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return this.mMapView;
	}

	@Override
	public void onResume() {
		super.onResume();
		this.mMapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		this.mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		this.mMapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		this.mMapView.onLowMemory();
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		this.mMapView.onSaveInstanceState(savedInstanceState);
	}

	private LatLng getLocationFromAddress(String strAddress, Context context) {
		Geocoder coder = new Geocoder(context);
		List<Address> address;
		LatLng latLong = null;

		try {
			address = coder.getFromLocationName(strAddress, 5);
			if (address == null) {
				return null;
			}

			Address location = address.get(0);
			location.getLatitude();
			location.getLongitude();

			latLong = new LatLng(location.getLatitude(), location.getLongitude());
		} catch (Exception ex) {
			System.out.println("-------Exception:" + ex.toString());
		}
		return latLong;
	}
}