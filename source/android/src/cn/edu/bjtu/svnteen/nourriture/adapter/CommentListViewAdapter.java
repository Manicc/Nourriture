package cn.edu.bjtu.svnteen.nourriture.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.bean.Comment;

public class CommentListViewAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Comment> mList;

	public CommentListViewAdapter(Context context, ArrayList<Comment> list) {
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
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		Comment comment = mList.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.comment_listview_item, null);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.content = (TextView) convertView.findViewById(R.id.content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(comment.getName());
		holder.time.setText(comment.getTime());
		holder.content.setText(comment.getContent());
		return convertView;
	}

	class ViewHolder {
		TextView name;
		TextView time;
		TextView content;
	}

}
