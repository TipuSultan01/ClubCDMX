package app.club.com.clubapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by navi on 07/03/2018.
 */

public class AdapterDetails extends BaseAdapter{

    private List<RssFeedModel> mFeedModelList;
    Context context;
    private static LayoutInflater inflater = null;

    public AdapterDetails(List<RssFeedModel> modelList){
        this.context = context;

        this.mFeedModelList = modelList;

//        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mFeedModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, final ViewGroup viewGroup) {

        View vi = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup,false);

        TextView tv_text = (TextView)vi.findViewById(R.id.desctiption);
        ImageView img_photo = (ImageView)vi.findViewById(R.id.img_photo);

        Button btn_title = (Button)vi.findViewById(R.id.btn_title);


        String html;
//        if(Jsoup.parse(mFeedModelList.get(0).description).select("img").attr("src").equals("")){
//            html = mFeedModelList.get(i+1).description;
//        }else{
//            html = mFeedModelList.get(i).description;
//        }
        html = mFeedModelList.get(i).description;
        Document doc = Jsoup.parse(html);

        String imgLink = doc.select("img").attr("src");
        String description = ((Document) doc).text();
        final String link = mFeedModelList.get(i).link;
        String title = mFeedModelList.get(i).title;
        tv_text.setMovementMethod(new ScrollingMovementMethod());
        tv_text.setText(description);


        Glide.with(viewGroup.getContext()).load(imgLink).into(img_photo);

        btn_title.setText(title);

        btn_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                viewGroup.getContext().startActivity(intent);
            }
        });



        return vi;
    }
}
