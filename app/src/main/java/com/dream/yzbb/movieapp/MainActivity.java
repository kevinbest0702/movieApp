package com.dream.yzbb.movieapp;

import com.dream.yzbb.movieapp.databinding.ActivityMainBinding;
import com.dream.yzbb.movieapp.entity.User;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mTextView;
    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.bind(findViewById(R.id.root_view));
        mButton = (Button) findViewById(R.id.action);
        mTextView = (TextView) findViewById(R.id.content);
//        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        activityMainBinding.setUser(mUser);
    }

    public void assignValue(View view) {
        Toast.makeText(this, "Set user's name", Toast.LENGTH_SHORT).show();
        mUser.setName("Xianpzha");
//        mUser.name.set("Xianpzha");
    }
}
