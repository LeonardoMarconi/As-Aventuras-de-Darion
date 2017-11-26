
package br.com.API4CC;


import javafx.scene.media.AudioClip;
/**
 Cria os Sons
 */
public class Sons {
    private final String AUDIO_URL = getClass().getResource("/jogo.mp3").toString();
    private final String AUDIO_LIXO = getClass().getResource("/lixo.wav").toString();
    private final String AUDIO_FLORESTA = getClass().getResource("/Forest_Ambience.mp3").toString();
    private final String AUDIO_FIM = getClass().getResource("/fim.mp3").toString();
    private final String AUDIO_CORR = getClass().getResource("/Aplausos1.mp3").toString();
    private final String AUDIO_PAR = getClass().getResource("/Parabens.mp3").toString();
    private final String AUDIO_FINALE = getClass().getResource("/Face.mp3").toString();
    private final String AUDIO_ERRO = getClass().getResource("/Errado.wav").toString();
    

    AudioClip clip = new AudioClip(AUDIO_FLORESTA);

    AudioClip clip3 = new AudioClip(AUDIO_URL);

    AudioClip clip_fim = new AudioClip(AUDIO_FIM);

    AudioClip clip_lixo = new AudioClip(AUDIO_LIXO);

    AudioClip clip_Corr = new AudioClip(AUDIO_CORR);

    AudioClip clip_Finale = new AudioClip(AUDIO_FINALE);

    AudioClip clip_Parabens = new AudioClip(AUDIO_PAR);
    
    AudioClip clip_Erro = new AudioClip(AUDIO_ERRO);
}
