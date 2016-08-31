package hilti.dao;

import hilti.model.Image;

public interface ImageDao {

    void saveImage(Image image);
    Image getImage(int id);
}
