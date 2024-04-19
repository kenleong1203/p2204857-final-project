package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import entity.Entity;
import objects.OBJ_Heart;
import objects.OBJ_Key;


public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	public int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: the first screen
	public UI(GamePanel gp){
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		// create HUD object
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}
	public void showMessage(String text){
		
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2){
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		// title state
		if(gp.gameState == gp.titleState){
			drawTitleScreen();
		}
		// play state
		if(gp.gameState == gp.playState){
			drawPlayerLife();
			
		}
		// pause state
		if(gp.gameState == gp.pauseState){
			drawPlayerLife();
			drawPauseScreen();
		}
		// dialogue state
		if(gp.gameState == gp.dialogueState){
			drawPlayerLife();
			drawDialogueScreen();
			}
	}
	public void drawPlayerLife(){
		
	//	gp.player.life = 6;
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
//		int iconSize = 32;
//		int manaStartX = (gp.tileSize/2)-5;
//		int manaStartY = 0;
		
		
		// draw max life
		while(i < gp.player.maxLife/2){
			g2.drawImage(heart_blank, x, y,null);
			i++;
			x += gp.tileSize;
//			manaStartY = y + 32;
			
		}
		// reset
		 x = gp.tileSize/2;
		 y = gp.tileSize/2;
		 i = 0;
		
		 // draw current life
		while(i < gp.player.life){
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life){
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
//		// draw max mana
//		x = (gp.tileSize/2)-5;
//		y = (int)(gp.tileSize*1.5);
//		i = 0;
//		while(i < gp.player.maxMana){
//			g2.drawImage(crystal_blank, x, y, null);
//			i++;
//			x += 35;
//		}
//		
//		// draw mana
//		x = (gp.tileSize/2) - 5;
//		y = (int)(gp.tileSize* 1.5);
//		i = 0;
//		while( i < gp.player.mana){
//			g2.drawImage(crystal_full, x, y, null);
//			i++;
//			x += 35;
//		}
	}
	public void drawTitleScreen(){
		
		if(titleScreenState == 0){
			g2.setColor(new Color(0,0,0));
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		// Title name
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,78F));
			String text = "Blue Boy Adventure";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			
			// shadow
			g2.setColor(Color.gray);
			g2.drawString(text, x+5, y+5);
			// Main color
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
					
			// Blue boy image
			x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			y += gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2,null);
					
			// Menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
					
			text = "New Game";
			x = getXforCenteredText(text);
			y += gp.tileSize*3.5;
			g2.drawString(text,x,y);
			if(commandNum == 0){
					g2.drawString(">",x-gp.tileSize,y);
					}
					
					text = "Load Game";
					x = getXforCenteredText(text);
					y += gp.tileSize;
					g2.drawString(text,x,y);
					if(commandNum == 1){
						g2.drawString(">",x-gp.tileSize,y);
					}
					
					text = "Quit";
					x = getXforCenteredText(text);
					y += gp.tileSize;
					g2.drawString(text,x,y);
					if(commandNum == 2){
						g2.drawString(">",x-gp.tileSize,y);
					}
				}
					else if (titleScreenState == 1){
						// class selection screen
						g2.setColor(Color.white);
						g2.setFont(g2.getFont().deriveFont(42F));
						
						String text = "Select your class!";
						int x = getXforCenteredText(text);
						int y = gp.tileSize*3;
						g2.drawString(text, x, y);
						
						text = "Fighter";
						x = getXforCenteredText(text);
						y += gp.tileSize*3;
						g2.drawString(text, x, y);
						if(commandNum == 0){
							g2.drawString(">", x-gp.tileSize, y);
						}
						text = "Thief";
						x = getXforCenteredText(text);
						y += gp.tileSize;
						g2.drawString(text, x, y);
						if(commandNum == 1){
							g2.drawString(">", x-gp.tileSize, y);
						}
						text = "Sorcerer";
						x = getXforCenteredText(text);
						y += gp.tileSize;
						g2.drawString(text, x, y);
						if(commandNum == 2){
							g2.drawString(">", x-gp.tileSize, y);
						}
						text = "Back";
						x = getXforCenteredText(text);
						y += gp.tileSize*2;
						g2.drawString(text, x, y);
						if(commandNum == 3){
							g2.drawString(">", x-gp.tileSize, y);
						}
					}
		
		
	}
	public void drawPauseScreen(){
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public void drawDialogueScreen(){
		

		// Window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x,y,width,height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F)); // marumonica
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")){
			g2.drawString(line, x, y);
			y += 40;
		}
	}
		

	public void drawSubWindow(int x, int y, int width, int height){
		
		Color c = new Color(0,0,0,205);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public int getXforCenteredText(String text){
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();		
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}