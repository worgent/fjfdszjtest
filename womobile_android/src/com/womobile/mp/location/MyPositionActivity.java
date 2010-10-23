package com.womobile.mp.location;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.womobile.mp.R;

/**
 * @author lsr
 * 确定用户当前的位置
 */
public class MyPositionActivity extends MapActivity {

	LinearLayout linearLayout;
	MapView mapView;
	ZoomControls mZoom;
	private LocationManager locationManager;
	
	List<Overlay> mapOverlays;
	Drawable drawable;
	MyPositionItemizedOverlay itemizedOverlay;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("联通移动助理-我的位置");
		setContentView(R.layout.my_position);

		linearLayout = (LinearLayout) findViewById(R.id.zoomview);
		mapView = (MapView) findViewById(R.id.mapview);
		mZoom = (ZoomControls) mapView.getZoomControls();
		linearLayout.addView(mZoom);
		
		
		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		itemizedOverlay = new MyPositionItemizedOverlay(drawable);
		
		MapController controller = mapView.getController();
		
		GeoPoint point = locate(controller);
		
		OverlayItem overlayitem = new OverlayItem(point, "", "");

		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
	}

	private GeoPoint locate(MapController controller) {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		String provider = LocationManager.GPS_PROVIDER;
		Location location = locationManager.getLastKnownLocation(provider);
		
		double lat=0.0;
		double lng = 0.0;
		if (location != null) {
			 lat = location.getLatitude();
			 lng = location.getLongitude();
		}else{
			//泉州邮政局
			lat = 24.904158;
			lng = 118.58926;
		}
		
		GeoPoint point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
		controller.animateTo(point);
		controller.setZoom(18);
		return point;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
