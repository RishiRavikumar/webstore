package com.tatacliq.cf.webstore.domain.entities;

import com.tatacliq.cf.webstore.domain.interfaces.IImages;

import java.util.Set;

public class Images implements IImages {
    private Set<Image> images;

    @Override
    public void addImage(Image image) {

    }

    @Override
    public void removeImage(Image image) {

    }

    @Override
    public boolean hasImage() {
        return false;
    }
}
