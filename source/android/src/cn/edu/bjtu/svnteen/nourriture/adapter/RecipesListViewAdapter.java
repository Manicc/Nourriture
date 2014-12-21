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
import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;

public class RecipesListViewAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Recipe> mRecipesArrayList;

	public RecipesListViewAdapter(Context context,
			ArrayList<Recipe> recipesArrayList) {
		mContext = context;
		mRecipesArrayList = recipesArrayList;
	}

	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.home_listview_item, parent, false);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.home_item_listview);
			holder.nameTextView = (TextView) convertView
					.findViewById(R.id.home_item_nametextview);
			holder.desTextView = (TextView) convertView
					.findViewById(R.id.home_item_destextview);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}

	class ViewHolder {
		TextView nameTextView;
		TextView desTextView;
		ImageView imageView;
	}

}
