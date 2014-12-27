package cn.edu.bjtu.svnteen.nourriture.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.bean.IngredientDataModel;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;

public class IngredientGridViewAdapter extends BaseAdapter {
	private Context mContext;
	private IngredientDataModel mIngredientDataModel;

	public IngredientGridViewAdapter(Context context,
			IngredientDataModel dataModel) {
		mContext = context;
		mIngredientDataModel = dataModel;
	}

	@Override
	public int getCount() {
		return mIngredientDataModel.getIngredientList().size();
	}

	@Override
	public Object getItem(int position) {
		return mIngredientDataModel.getIngredientList().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Ingredient ingredient = mIngredientDataModel.getIngredientList().get(
				position);
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.ingredient_gridview_item, null);
		TextView textView = (TextView) convertView.findViewById(R.id.name);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.imageView);
		textView.setText(ingredient.getName());
		ImageUtils.loadImage(ingredient.getImageURL(), imageView);
		return convertView;
	}

}
