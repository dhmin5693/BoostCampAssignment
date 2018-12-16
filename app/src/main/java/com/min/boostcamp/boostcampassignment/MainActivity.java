package com.min.boostcamp.boostcampassignment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.min.boostcamp.boostcampassignment.adapter.RecyclerViewAdapter;
import com.min.boostcamp.boostcampassignment.common.BaseActivity;
import com.min.boostcamp.boostcampassignment.databinding.ActivityMainBinding;
import com.min.boostcamp.boostcampassignment.util.ApiClinet;
import com.min.boostcamp.boostcampassignment.util.NaverApiInterface;
import com.min.boostcamp.boostcampassignment.vo.Repo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    
    private NaverApiInterface naverApiInterface;

    private String clientId;
    private String clientSecret;

    private RecyclerViewAdapter recyclerViewAdapter;
    private String mKeyword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // string resource로 저장된 url에 연결
        naverApiInterface = ApiClinet.getClient(getString(R.string.properties_url))
                .create(NaverApiInterface.class);

        // string resource로 저장된 client id, secret을 할당
        clientId = getString(R.string.properties_client_id);
        clientSecret = getString(R.string.properties_client_secret);

        // 엔터 키를 누르면 키보드가 사라짐
        getBinding().etTitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    getBinding().btnSearch.performClick();
                    return true;
                }

                return false;
            }
        });

        // 검색 버튼 클릭 시 검색 결과를 호출함
        getBinding().btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mKeyword = getBinding().etTitle.getText().toString();
                callApi(mKeyword, 1, true);

                // Hide the keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        // 최하단으로 스크롤을 내리면 검색 결과 추가 호출
        getBinding().recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemCount = recyclerViewAdapter.getItemCount() - 1;

                // 아이템이 10의 배수이며 최하단에서 새로운 목록을 로드함
                if (!recyclerViewAdapter.isLoading() && ((lastPosition + 1) % 10 == 0) && lastPosition == itemCount) {
                    recyclerViewAdapter.setLoading(true);
                    callApi(mKeyword, lastPosition + 2, false);
                }
            }
        });
    }

    /**
     * 검색 결과를 호출한다.
     * @param keyword 검색어
     */
    private void callApi(String keyword, int start, boolean isButtonClicked) {

        if (!ApiClinet.isNetworkConnected(getApplicationContext())) {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.msg_network_error),
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        // 검색 전엔 리사이클러뷰가 필요 없으므로 첫 검색 시에 할당
        if (recyclerViewAdapter == null) {
            recyclerViewAdapter = new RecyclerViewAdapter(
                    getApplicationContext(), getBinding().recyclerView);
            getBinding().recyclerView.setAdapter(recyclerViewAdapter);
            getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }

        if (isButtonClicked) {
           recyclerViewAdapter.reset();
        }

        Call<Repo> call = naverApiInterface.getMovieList(clientId, clientSecret, keyword, start);
        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(@NonNull Call<Repo> call, @NonNull Response<Repo> response) {

                if(response.isSuccessful()) {
                    if (response.body() != null && response.raw().code() == 200) {
                        Log.d("onResponse", "success");
                        recyclerViewAdapter.addItems(response.body().getItems());
                    }
                } else {
                    Log.d("onResponse", "fail");
                    Toast.makeText(
                            getApplicationContext(),
                            getString(R.string.msg_unknown_error),
                            Toast.LENGTH_SHORT
                    ).show();
                }

                recyclerViewAdapter.setLoading(false);
            }

            @Override
            public void onFailure(@NonNull Call<Repo> call, @NonNull Throwable t) {

                Log.d("onFailure", "fail");
                Toast.makeText(
                        getApplicationContext(),
                        getString(R.string.msg_unknown_error),
                        Toast.LENGTH_SHORT
                ).show();

                recyclerViewAdapter.setLoading(false);
            }
        });
    }
}
