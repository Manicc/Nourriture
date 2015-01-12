package cn.edu.bjtu.svnteen.nourriture.ingredient;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.adapter.CommentListViewAdapter;
import cn.edu.bjtu.svnteen.nourriture.bean.Comment;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.fragment.BaseFragment;
import cn.edu.bjtu.svnteen.nourriture.mine.MineFragment;
import cn.edu.bjtu.svnteen.nourriture.observer.ICommentObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.CommentUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.PreferenceUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.UIUtils;

public class IngredientDetailFragment extends BaseFragment implements
		ICommentObserver {

	private Context mContext;

	private View mRootView;
	private ImageView mImageView;
	private TextView mTextView;
	private ListView mListView;
	private CommentListViewAdapter mAdapter;
	private EditText mEditText;
	private Button mButton;
	private Button mCollectButton;

	public Ingredient mIngredient;
	private boolean mIsCollected;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		MessageManager.getInstance().attachMessage(MessageID.OBSERVER_COMMENT,
				this);// 在onAttach中注册消息
	}

	@Override
	public void onDetach() {
		super.onDetach();
		MessageManager.getInstance().detachMessage(MessageID.OBSERVER_COMMENT,
				this);// 在onDetach中解除消息
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		mRootView = inflater.inflate(R.layout.ingredient_detail, null);
		mImageView = (ImageView) mRootView.findViewById(R.id.imageView);
		mTextView = (TextView) mRootView.findViewById(R.id.textview);
		mListView = (ListView) mRootView.findViewById(R.id.listview);
		mCollectButton = (Button) mRootView.findViewById(R.id.collectButton);
		ImageUtils.loadImage(mIngredient.getImageURL(), mImageView);
		mTextView.setText(mIngredient.getName());

		mEditText = (EditText) mRootView.findViewById(R.id.edittext);
		mButton = (Button) mRootView.findViewById(R.id.button);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String content = mEditText.getText().toString();
				if (!TextUtils.isEmpty(content)) {
					CommentUtils.sendCommend(mIngredient, content);
					mButton.setClickable(false);
					mButton.setBackgroundResource(R.drawable.input_send);
				}
			}
		});
		mCollectButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String toastMsg;
				if (mIsCollected) {
					toastMsg = "取消收藏成功";
					mCollectButton.setBackgroundResource(R.drawable.sc_icon);
				} else {
					toastMsg = "收藏成功";
					mCollectButton.setBackgroundResource(R.drawable.sced_icon);
				}
				Toast.makeText(mContext, toastMsg, Toast.LENGTH_SHORT).show();
				mIsCollected = !mIsCollected;
			}
		});

		if (!PreferenceUtils.getLogin()) {
			mEditText.setVisibility(View.GONE);// 未登录用户不显示评论框
			mButton.setVisibility(View.GONE);
			mCollectButton.setVisibility(View.GONE);
		} else {
			for (Ingredient ing : MineFragment.mFavorite.getIngredientList()) {
				if (mIngredient.getId() == ing.getId()) {
					mIsCollected = true;
					mCollectButton.setBackgroundResource(R.drawable.sced_icon);
				}
			}
		}
		CommentUtils.getComment(mIngredient);
		return mRootView;
	}

	@Override
	public void ICommentObserver_success(ArrayList<Comment> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		mAdapter = new CommentListViewAdapter(mContext, list);
		mListView.setAdapter(mAdapter);
		UIUtils.setListViewHeightBasedOnChildren(mListView);
		mAdapter.notifyDataSetChanged();
		mEditText.setText("");
		mButton.setClickable(true);
		mButton.setBackgroundResource(R.drawable.input_send_pressed);
	}

	@Override
	public void ICommentObserver_failed() {

	}

	@Override
	public void ICommentObserver_sendcommend_success() {
		CommentUtils.getComment(mIngredient);
	}

	@Override
	public void ICommentObserver_sendcommend_failed() {
		// TODO Auto-generated method stub

	}
}
