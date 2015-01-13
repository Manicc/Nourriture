package cn.edu.bjtu.svnteen.nourriture.adapter;

import java.util.ArrayList;

import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SimpleImageViewAdapter extends BaseAdapter {
	public Context mContext;
	public ArrayList<String> mImageUrlList;

	public SimpleImageViewAdapter(Context context,
			ArrayList<String> imageUrlList) {
		mContext = context;
		mImageUrlList = imageUrlList;

	}

	@Override
	public int getCount() {
		return mImageUrlList.size();
	}

	@Override
	public Object getItem(int position) {
		return mImageUrlList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.item_imageview, null);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.item_imageview);
		ImageUtils.loadImage(mImageUrlList.get(position), imageView);
		return convertView;
	}

}
