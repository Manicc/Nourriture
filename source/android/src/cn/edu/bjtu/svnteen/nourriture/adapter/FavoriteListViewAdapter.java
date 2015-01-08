package cn.edu.bjtu.svnteen.nourriture.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.bean.Favorite;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.bean.Product;
import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;

public class FavoriteListViewAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<Ingredient> mIngredientList;
	private ArrayList<Product> mProductList;
	private ArrayList<Recipe> mRecipList;

	public FavoriteListViewAdapter(Context context, Favorite favorite) {
		mContext = context;
		mIngredientList = favorite.getIngredientList();
		mProductList = favorite.getProductList();
		mRecipList = favorite.getRecipeList();
	}

	@Override
	public int getCount() {
		return mIngredientList.size() + mProductList.size() + mRecipList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.favorite_listview_item, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.divider = convertView.findViewById(R.id.divider);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (position < mIngredientList.size()) {
			holder.name.setText(mIngredientList.get(position).getName());
		} else if (position < mIngredientList.size() + mProductList.size()) {
			holder.name.setText(mProductList.get(
					position - mIngredientList.size()).getName());
		} else {
			holder.name.setText(mRecipList.get(
					position - mIngredientList.size() - mProductList.size())
					.getName());
		}

		if (position == 0) {
			holder.title.setText("食材");
			holder.title.setVisibility(View.VISIBLE);
			holder.divider.setVisibility(View.VISIBLE);
		} else if (position == mIngredientList.size()) {
			holder.title.setText("产品");
			holder.title.setVisibility(View.VISIBLE);
			holder.divider.setVisibility(View.VISIBLE);
		} else if (position == mIngredientList.size() + mProductList.size()) {
			holder.title.setText("食谱");
			holder.title.setVisibility(View.VISIBLE);
			holder.divider.setVisibility(View.VISIBLE);
		} else {
			holder.title.setVisibility(View.GONE);
			holder.divider.setVisibility(View.GONE);
		}

		return convertView;
	}

	class ViewHolder {
		TextView name;
		TextView title;
		View divider;
	}

}
