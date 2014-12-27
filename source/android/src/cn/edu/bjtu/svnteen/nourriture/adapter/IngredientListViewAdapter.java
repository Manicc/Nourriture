package cn.edu.bjtu.svnteen.nourriture.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.bean.IngredientDataModel;

public class IngredientListViewAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<IngredientDataModel> mList;

	public IngredientListViewAdapter(Context context,
			ArrayList<IngredientDataModel> list) {
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
		IngredientDataModel dataModel = mList.get(position);
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.ingredient_listview_item, null);
		TextView textView = (TextView) convertView.findViewById(R.id.textview);
		GridView gridView = (GridView) convertView.findViewById(R.id.gridview);
		textView.setText(dataModel.getName());
		gridView.setAdapter(new IngredientGridViewAdapter(mContext, dataModel));
		return convertView;
	}

}
