package com.tatacliq.cf.webstore.domain.interfaces;

import com.tatacliq.cf.webstore.domain.entities.Image;

public interface IImages {
    void addImage(Image image);
    void removeImage(Image image);
    boolean hasImage();
}
