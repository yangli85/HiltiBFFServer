package hilti.service;

import hilti.model.Image;

/**
 * Created by yangli on 8/29/16.
 */
public interface ImageService {
    void saveImage(Image image);

    Image getImage(int id);
}
