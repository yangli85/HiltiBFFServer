package hilti.dao.impl;

import hilti.dao.AbstractDao;
import hilti.dao.AudioDao;
import hilti.model.Audio;
import org.springframework.stereotype.Repository;

@Repository("audioDao")
public class AudioDaoImpl extends AbstractDao<Integer, Audio> implements AudioDao {

    public void saveAudio(Audio audio) {
        persist(audio);
    }

    public Audio getAudio(int id) {
        Audio audio = (Audio) getByKey(id);
        return audio;
    }

}
