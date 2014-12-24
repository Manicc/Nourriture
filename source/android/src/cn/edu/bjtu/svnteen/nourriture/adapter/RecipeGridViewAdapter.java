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
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;

public class RecipeGridViewAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<Recipe> mList;

	public RecipeGridViewAdapter(Context context, ArrayList<Recipe> list) {
		mContext = context;
		mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Recipe recipe = mList.get(position);
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.recipe_gridview_item, null);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.imageView);
		TextView textView = (TextView) convertView.findViewById(R.id.name);
		ImageUtils.loadImage(recipe.getImageUrl(), imageView);
		textView.setText(recipe.getName() );
		return convertView;
	}

}
