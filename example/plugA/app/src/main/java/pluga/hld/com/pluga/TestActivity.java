package pluga.hld.com.pluga;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.dynamicutils.DynamicParantActivity;

public class TestActivity extends DynamicParantActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView =new ImageView(getContext());
        imageView.setImageResource(R.mipmap.test);
        setContentView(imageView);
    }
}
