package hilti.service;

import hilti.model.Audio;

/**
 * Created by yangli on 8/29/16.
 */
public interface AudioService {
    void saveAudio(Audio audio);

    Audio getAudio(int id);
}
