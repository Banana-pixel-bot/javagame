package com.wxk.starwar.lwjgl3;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.IntMap;

public class Map {
    private  IntIntMap items = new IntIntMap();
    public int[] mapArray;
    public IntMap<Texture> tileTextures;//磚塊材料
    


    public Map(String fileName) {
        
        
        items.put(0xFFFFFF, 1);  //白色
        items.put(0xFF7F27, 2);  //土色
        items.put(0xFFF200, 3);  //yellow
        items.put(0x3F48CC, 4);  //藍色
        items.put(0x22B14C, 5);  //綠色

       
        tileTextures = new IntMap<>();

        tileTextures.put(1, new Texture("tree.png"));
        tileTextures.put(2, new Texture("road.png"));
        tileTextures.put(3, new Texture("box.png"));
        tileTextures.put(4, new Texture("house.png"));
        tileTextures.put(5, new Texture("wall.png"));


    try {
        // 讀取圖片檔案（路徑可換成你自己的圖片）
        File imageFile = new File(fileName);
        BufferedImage image = ImageIO.read(imageFile);
        int w=image.getWidth();
        int h=image.getHeight();
        

        mapArray = new int[w * h];


        // 取得該座標的 RGB 顏色

        image.getRGB(0,0,w,h,mapArray,0,w);
        for (int i = 0; i < mapArray.length; i++) {
            int rgb = mapArray[i];
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;   
            rgb = rgb & 0xffffff;
            mapArray[i]=items.get(rgb, 0);
            //System.out.printf("Pixel %d: R=%d, G=%d, B=%d%n", i, r, g, b);
            
            //System.out.println(items.get(rgb, 0));
        }
        
    
    } catch (IOException e) {
        e.printStackTrace();
    }
   
    }

    public void draw(SpriteBatch batch) {
        batch.begin();



        for(int i=0;i<mapArray.length;i++){
            int x=0,y=0;
            x=i%15;
            y=i/15;
            batch.draw(tileTextures.get(mapArray[i]), x*48+280, y*48,48,48);


        }
        
        



        batch.end();
    }

}