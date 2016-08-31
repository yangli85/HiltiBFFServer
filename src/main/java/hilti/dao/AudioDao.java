package hilti.dao;

import hilti.model.Audio;

public interface AudioDao {

    void saveAudio(Audio image);
    Audio getAudio(int id);
}
