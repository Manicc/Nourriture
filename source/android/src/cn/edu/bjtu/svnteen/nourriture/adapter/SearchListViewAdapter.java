package cn.edu.bjtu.svnteen.nourriture.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.bean.SearchItem;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;

public class SearchListViewAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<SearchItem> mArrayList;

	public SearchListViewAdapter(Context context, ArrayList<SearchItem> list) {
		mContext = context;
		mArrayList = list;
	}

	@Override
	public int getCount() {
		return mArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return mArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.search_listview_item_content, null);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.imageivew);
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.textview);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ImageUtils.loadImage(mArrayList.get(position).getImageUrl(),
				viewHolder.imageView);
		viewHolder.textView.setText(mArrayList.get(position).getName());
		return convertView;
	}

	class ViewHolder {
		TextView textView;
		ImageView imageView;
	}

}
