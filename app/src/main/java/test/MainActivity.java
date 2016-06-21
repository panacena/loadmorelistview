package test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zkk.loadmorelistviw.LoadMoreListView;
import com.zkk.loadmorelistviw.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LoadMoreListView listview;
    private Context context;
    private  Myadapter myadapter;
    List<String>  list=new ArrayList<String>();
    private boolean isLoad = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        listview=(LoadMoreListView)findViewById(R.id.listview);
        myadapter=new Myadapter();

        listview.setAdapter(myadapter);
        listview.setOnLastItemVisibleListener(new LoadMoreListView.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                if (isLoad) {
                    listview.setFooter(LoadMoreListView.Mode.NOMORE);
                    return;
                }
                isLoad = true;
                setDate();
                listview.setFooter(LoadMoreListView.Mode.LOAD);
            }
        });
        setDate();
    }


    void setDate(){
        isLoad=false;
        for(int i=0;i<30;i++){
            list.add(i+"");
        }
        myadapter.notifyDataSetChanged();
    }
    class  Myadapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view= LayoutInflater.from(context).inflate(R.layout.item,null);
            TextView textView=(TextView)view.findViewById(R.id.tv);

            textView.setText(list.get(position).toString());
            return view;
        }
    }
}
