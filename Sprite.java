package br.com.API4CC;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite
{
    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    public Sprite()
    {
        // posi��o e velocidade inicial
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
        
    }

    public void setImage(Image i)
    {
        // define a imagem e o seu tamanho
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        // pega o caminho da imagem
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y)
    {
        // define a posi��o no jogo
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        // define a velocidade para andar no jogo
        velocityX += x;
        velocityY += y;
    }

    public void update(double time)
    {
        // cria a atualiza��o da posi��o e o tempo
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc)
    {
        //cria a imagem
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        // retorna a imagem
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        // cria a inteseccao entre o personagem e o alvo
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]"
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }


}
