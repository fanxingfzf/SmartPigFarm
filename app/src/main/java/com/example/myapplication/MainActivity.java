package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.Adapter.MyFragmentAdapter;
import com.example.myapplication.activity.DataActivity;
import com.example.myapplication.ui.dashboard.DashboardFragment;
import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Switch swh_status;
    private MyFragmentAdapter myFragmentAdapter;//适配器
    private ViewPager viewPager; // 要使用的ViewPager

    private View page1, page2, page3; // ViewPager包含的页面

    private List<Fragment> fragmentList; // ViewPager包含的页面列表，一般给adapter传的是一个list

    private Intent intent;
    private Button mGetData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        //设置打开时的默认页面
        init();
        initView();
        mGetData.setOnClickListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                navView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageSelected(int position) {
                navView.getMenu().getItem(position).setChecked(true);
                //写滑动页面后做的事，使每一个fragmen与一个page相对应


            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void init() {
        intent=new Intent();
    }

    //实现上面切换观测台，操作台，home页面

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载布局，使用菜单特有方法getMenInfater，或Inflate对象
        //参数：1.菜单显示的布局  2.固定menu
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    //在每个if条件下填充相应的操作代码
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_dashboard) {
            //观测台
            Toast.makeText(getApplicationContext(), "观测台", Toast.LENGTH_SHORT).show();

            return true;
        } else if (itemId == R.id.navigation_notifications) {
            //操作台
            Toast.makeText(getApplicationContext(), "操作台", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, OperatingFloorActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.navigation_home) {

            //我的
            Toast.makeText(getApplicationContext(), "我的", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent();
            intent2.setClass(MainActivity.this, HomeActivity.class);
            startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView() {
        // TODO Auto-generated method stub
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        // ViewPager中包含的页面为Fragment，用法与前面的普通适配器一模一样

        DashboardFragment myFragment1 = new DashboardFragment();
        NotificationsFragment myFragment2 = new NotificationsFragment();
        HomeFragment myFragment3 = new HomeFragment();

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(myFragment1);
        fragmentList.add(myFragment2);
        fragmentList.add(myFragment3);

        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(myFragmentAdapter);
        viewPager.setCurrentItem(0);

        mGetData=findViewById(R.id.GetData);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.GetData:
                intent.setClass(MainActivity.this, DataActivity.class);
                startActivity(intent);
                break;
        }
    }
}