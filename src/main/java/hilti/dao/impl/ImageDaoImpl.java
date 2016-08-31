package hilti.dao.impl;

import hilti.dao.AbstractDao;
import hilti.dao.ImageDao;
import org.springframework.stereotype.Repository;
import hilti.model.Image;

@Repository("imageDao")
public class ImageDaoImpl extends AbstractDao<Integer, Image> implements ImageDao {

    public void saveImage(Image image) {
        persist(image);
    }

    public Image getImage(int id) {
        Image image = (Image) getByKey(id);
        return image;
    }

}
