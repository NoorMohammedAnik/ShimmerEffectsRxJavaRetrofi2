package com.app.shimmereffects;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ShimmerFrameLayout mShimmerViewContainer;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private List<Contacts> contactsList;
    private ApiInterface apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // apiService = ApiClient.getApiClient().create(ApiInterface.class);

        getProductData("users", "");
    }


//    public void fetchData(String type, String key){
//        Call<List<Contacts>> call = apiService.getContact(type,key);
//        call.enqueue(new Callback<List<Contacts>>() {
//            @Override
//            public void onResponse(Call<List<Contacts>> call, Response<List<Contacts>> response) {
//
//
//                if (response.isSuccessful())
//                {
//                    if (response.code()==200)
//                    {
//                        contactsList = response.body();
//                        adapter = new Adapter(MainActivity.this, contactsList);
//                        recyclerView.setAdapter(adapter);
//
//                        adapter.notifyDataSetChanged();//for search
//                    }
//
////                     Stopping Shimmer Effect 's animation after data is loaded to ListView
//                    mShimmerViewContainer.stopShimmer();
//                    mShimmerViewContainer.setVisibility(View.GONE);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Contacts>> call, Throwable t) {
//
//                Toast.makeText(MainActivity.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//


    public void getProductData(String type, String key) {

        apiService = ApiClient.getApiClient().create(ApiInterface.class);

        Observable<List<Contacts>> observable = apiService.getproductdata(type, key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<List<Contacts>>() {
            @Override
            public void onCompleted() {

                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Contacts> list) {


//                contactsList = response.body();
//                adapter = new Adapter(MainActivity.this, contactsList);
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();//for search

                contactsList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {

                    Contacts myList = new Contacts();
                    myList.setName(list.get(i).getName());
                    myList.setEmail(list.get(i).getEmail());
                    contactsList.add(myList);


                }

                adapter = new Adapter(MainActivity.this, contactsList);
//                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivity.this, 2);
//             //   recyclerView.addItemDecoration(new GridSpacingdecoration(2, dpToPx(10), true));
//                recyclerView.setLayoutManager(recyce);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }

//
//    public class GridSpacingdecoration extends RecyclerView.ItemDecoration {
//
//        private int span;
//        private int space;
//        private boolean include;
//
//        public GridSpacingdecoration(int span, int space, boolean include) {
//            this.span = span;
//            this.space = space;
//            this.include = include;
//        }
//
//        @Override
//        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            int position = parent.getChildAdapterPosition(view);
//            int column = position % span;
//
//            if (include) {
//                outRect.left = space - column * space / span;
//                outRect.right = (column + 1) * space / span;
//
//                if (position < span) {
//                    outRect.top = space;
//                }
//                outRect.bottom = space;
//            } else {
//                outRect.left = column * space / span;
//                outRect.right = space - (column + 1) * space / span;
//                if (position >= span) {
//                    outRect.top = space;
//                }
//            }
//        }
//
//    }
//
//    private int dpToPx(int dp) {
//        Resources r = getResources();
//        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
//    }


    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmer();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmer();
        super.onPause();
    }
}