package com.example.finalprojectdiit;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.finalprojectdiit.Model.News;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewsFragment extends Fragment {

    public static final String PREF_NOTIFICATION = "prefNotification";
    public static final String CHECKBOX_NOTIFICATION = "checkboxNotification";

    private RecyclerView mNewsRV;
    private DatabaseReference newsRef;
    private FirebaseRecyclerAdapter<News, MainActivity.NewsViewHolder> mNewsRVAdapter;
    private String url;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor  editor;
    private boolean checkbox_state;

    private CheckBox checkbox_news_notification;
    private String date;
    private Date dateStart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        checkbox_news_notification = (CheckBox) view.findViewById(R.id.checkbox_news_notification);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        newsRef = database.getReference().child("News");
        newsRef.keepSynced(true);

        mNewsRV = (RecyclerView) view.findViewById(R.id.myRecycleView);

        mNewsRV.hasFixedSize();
        mNewsRV.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        checkMessageNotification();
        newsRecycleView();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mNewsRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mNewsRVAdapter.stopListening();
    }

    public void newsRecycleView(){

        Query newsQuery = newsRef.orderByKey();

        mNewsRV.hasFixedSize();
        mNewsRV.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        FirebaseRecyclerOptions newsOptions = new FirebaseRecyclerOptions.Builder<News>().setQuery(newsQuery, News.class).build();

        mNewsRVAdapter = new FirebaseRecyclerAdapter<News, MainActivity.NewsViewHolder>(newsOptions) {
            @Override
            protected void onBindViewHolder(@NonNull MainActivity.NewsViewHolder holder, final int position, final News model) {

                holder.setTitle(model.getTitle().replaceAll("\\n","\n"));
                holder.setDesc(model.getDesc().replaceAll("\\n","\n"));
                date = model.getDate_start();

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm'Z'", Locale.US);

                try {
                    dateStart = format.parse(date);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateStart);
                    holder.setDate_start(calendar.getTime()+"");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                holder.setImage(getActivity(), model.getImage());
                date = model.getDate_start();

                holder.mView_News.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        url = model.getUrl();
                        Intent intent = new Intent(getActivity(), NewsWebView.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public MainActivity.NewsViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {

                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.news_row, viewGroup, false);

                return new MainActivity.NewsViewHolder(view);
            }
        };

        mNewsRV.setAdapter(mNewsRVAdapter);
    }


    public void checkMessageNotification() {

        sharedPreferences = getActivity().getSharedPreferences(PREF_NOTIFICATION, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        checkbox_news_notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                FirebaseMessaging.getInstance().subscribeToTopic("NewsMessage");

                editor.putBoolean(CHECKBOX_NOTIFICATION, isChecked);
                editor.apply();

                Toast.makeText(getActivity(), "บันทึกการแจ้งเตือนข่าวสารและกิจกรรมสำเร็จ", Toast.LENGTH_SHORT).show();

                if (!isChecked) {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("NewsMessage");

                    editor.putBoolean(CHECKBOX_NOTIFICATION, false);
                    editor.apply();

                    Toast.makeText(getActivity(), "ยกเลิกการแจ้งเตือนข่าวสารและกิจกรรมสำเร็จ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkbox_state = sharedPreferences.getBoolean(CHECKBOX_NOTIFICATION, false);

        checkbox_news_notification.setChecked(checkbox_state);

    }
}
