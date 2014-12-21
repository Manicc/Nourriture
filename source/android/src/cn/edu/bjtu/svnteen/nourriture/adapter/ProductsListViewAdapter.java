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
import cn.edu.bjtu.svnteen.nourriture.adapter.RecipesListViewAdapter.ViewHolder;
import cn.edu.bjtu.svnteen.nourriture.bean.Product;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;

public class ProductsListViewAdapter extends BaseAdapter{
	private Context mContext;
	private ArrayList<Product> mProductList;
	
	public ProductsListViewAdapter(Context context, ArrayList<Product> products){
		mContext = context;
		mProductList = products;
	}

	@Override
	public int getCount() {
		return mProductList.size();
	}

	@Override
	public Object getItem(int position) {
		return mProductList.get(position);
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
					R.layout.product_listview_item, parent, false);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.product_item_imageview);
			holder.nameTextView = (TextView) convertView
					.findViewById(R.id.product_item_nametextview);
			holder.desTextView = (TextView) convertView
					.findViewById(R.id.product_item_destextview);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageUtils.loadImage(mProductList.get(position).getImageUrl(), holder.imageView);
		holder.nameTextView.setText(mProductList.get(position).getName());

		return convertView;
	}

	class ViewHolder {
		TextView nameTextView;
		TextView desTextView;
		ImageView imageView;
	}

}
