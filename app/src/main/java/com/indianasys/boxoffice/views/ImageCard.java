package com.indianasys.boxoffice.views;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fima.cardsui.objects.RecyclableCard;
import com.indianasys.boxoffice.R;


/**
 * Created by jide on 28/10/14.
 */
public class ImageCard extends RecyclableCard {

    Bitmap poster;

    public ImageCard(String title, String desc, Bitmap poster){
        super(title, desc);
        this.poster = poster;
    }

    @Override
    protected int getCardLayoutId() {
        return R.layout.card_picture;
    }

    @Override
    protected void applyTo(View convertView) {
        ((TextView) convertView.findViewById(R.id.title)).setText(title);
        ((ImageView) convertView.findViewById(R.id.imageView1)).setImageBitmap(poster);
        ((TextView) convertView.findViewById(R.id.description)).setText(desc);
    }





}
