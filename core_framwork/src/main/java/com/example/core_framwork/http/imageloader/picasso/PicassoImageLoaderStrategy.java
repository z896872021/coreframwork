package com.example.core_framwork.http.imageloader.picasso;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.core_framwork.http.imageloader.BaseImageLoaderStrategy;
import com.squareup.picasso.Picasso;

public class PicassoImageLoaderStrategy implements BaseImageLoaderStrategy<PicassoImageConfig> {
    @Override
    public void loadImage(Context ctx, PicassoImageConfig config) {
        Picasso.with(ctx)
                .load(config.getUrl())
                .into(config.getImageView());
    }

    @Override
    public void clear(@Nullable Context ctx, @Nullable PicassoImageConfig config) {
    }
}
