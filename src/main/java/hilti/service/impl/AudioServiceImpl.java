package hilti.service.impl;

import hilti.dao.AudioDao;
import hilti.model.Audio;
import hilti.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yangli on 8/29/16.
 */
@Service("audioService")
@Transactional
public class AudioServiceImpl implements AudioService {

    @Autowired
    private AudioDao dao;

    public void saveAudio(Audio audio) {
        dao.saveAudio(audio);
    }

    public Audio getAudio(int id) {
        return dao.getAudio(id);
    }
}
