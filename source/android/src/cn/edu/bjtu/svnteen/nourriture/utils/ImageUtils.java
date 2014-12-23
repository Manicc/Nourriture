package cn.edu.bjtu.svnteen.nourriture.utils;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.widget.ImageView;

/**
 * ͼƬ�첽���ع����� 
 * @author Tans
 */
public class ImageUtils {

	private static Context mContext;

	public static void init(Context context) {
		mContext = context;
	}

	public static void loadImage(String url, ImageView imageView) {
		Picasso.with(mContext).load(url).into(imageView);
	}
}
