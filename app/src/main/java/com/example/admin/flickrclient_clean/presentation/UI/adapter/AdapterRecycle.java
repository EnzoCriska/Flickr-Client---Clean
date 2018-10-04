package com.example.admin.flickrclient_clean.presentation.UI.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.flickrclient_clean.R;
import com.example.admin.flickrclient_clean.domain.model.Photo;
import com.example.admin.flickrclient_clean.util.BitmapCache;
import com.squareup.picasso.Picasso;


public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.ViewHolder>{
    private IData iData;
    private OnClickPhoto onClickPhoto;
    private String TAG = "Adapter RecyclerView";
    private BitmapCache cache;
    private Context context;

    public AdapterRecycle( OnClickPhoto onClickPhoto, Context context) {
        this.onClickPhoto = onClickPhoto;
        cache = new BitmapCache(100);
        this.context = context;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setiData(IData iData) {
        this.iData = iData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{
        ImageView mImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            mImageView = itemView.findViewById(R.id.imageItem);
        }

        @Override
        public void onClick(View view) {
            onClickPhoto.onClickItemPhoto(this.getPosition());
        }

        public void bindData(Photo photo) {
            String url = photo.getUrlS();
//            if (cache.hasBitmap(url)){
//                mImageView.setImageBitmap(cache.getBitmap(url));
//            }else{
//                cache.setorDownBitmap(url,context, mImageView);
//            }
            try{
                Picasso.get()
                        .load(photo.getUrlS())
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .resize(Integer.parseInt(photo.getWidthS())*2,Integer.parseInt(photo.getHeightS())*2)
                        .centerCrop()
                        .into(mImageView);
                mImageView.setOnClickListener(this);
            }catch (IllegalArgumentException e){
                Log.e("ERROR", "Path is empty");
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select the Action");
//            menu.add(Menu.NONE, R.id.deleteReport, Menu.NONE,"Delete report");
//            menu.add(Menu.NONE, R.id.changereports, Menu.NONE, "Change Report");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View messView = inflater.inflate(R.layout.item_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(messView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Photo photo = iData.getItem(position);
        holder.bindData(photo);
    }

    @Override
    public int getItemCount() {
        return iData.getCount();
    }


    public interface IData {
        int getCount();

        Photo getItem(int pos);
    }

    public interface OnClickPhoto{
        void onClickItemPhoto(int position);
    }
}
