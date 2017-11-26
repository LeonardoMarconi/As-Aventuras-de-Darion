package br.com.API4CC;

import javafx.scene.image.Image;

public class AnimatedImage
{
    // assume loops de animação,
    //  atualiza a imagem por tempo
    public Image[] frames;
    public double duration;
    
    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
}