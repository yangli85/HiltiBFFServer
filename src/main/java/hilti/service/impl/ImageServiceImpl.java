package hilti.service.impl;

import hilti.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import hilti.dao.ImageDao;
import hilti.model.Image;

/**
 * Created by yangli on 8/29/16.
 */
@Service("imageService")
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao dao;

    public void saveImage(Image image) {
        dao.saveImage(image);
    }

    public Image getImage(int id) {
        return dao.getImage(id);
    }
}
