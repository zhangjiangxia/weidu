package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bw.movie.R;
import com.bw.movie.model.bean.DetailsBean;

import java.util.List;

public class YugaoAdapter extends RecyclerView.Adapter<YugaoAdapter.VooHodel> {
    List<DetailsBean.ResultBean.ShortFilmListBean> shortFilmList;
    Context context;

    public YugaoAdapter(List<DetailsBean.ResultBean.ShortFilmListBean> shortFilmList, Context context) {
        this.shortFilmList = shortFilmList;
        this.context = context;
    }
    @NonNull
    @Override
    public VooHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shipin_itme, viewGroup, false);
        VooHodel vooHodel = new VooHodel(view);
        return vooHodel;
    }
    @Override
    public void onBindViewHolder(@NonNull VooHodel vooHodel, int i) {
        String videoUrl = shortFilmList.get(i).getVideoUrl();
        vooHodel.videoView.setVideoURI(Uri.parse(videoUrl));
        vooHodel.videoView.start();
    }

    @Override
    public int getItemCount() {
        return shortFilmList.size();
    }
    class  VooHodel extends RecyclerView.ViewHolder{
        private  VideoView videoView;

        public VooHodel(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);

            MediaController mediaController = new MediaController(context);
            videoView.setMediaController(mediaController);
            mediaController.setMediaPlayer(videoView);
            videoView.requestFocus();

        }

    }
}
