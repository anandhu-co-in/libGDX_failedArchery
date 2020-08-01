package com.lotusgames;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class myGameClass extends ApplicationAdapter{

OrthographicCamera cam;
ShapeRenderer sr;
Vector3 pos;
int textposx=100;
int textposy=450;
int hbdsize = 1;
int x1=0,y1=0,x2=50,y2=50,t=1;
String wish = "HELLO ";

	Vector2 gravity;
	Vector2 initialVelocity;
	float throwAngle=50;
	float deltaTime=2;

	private SpriteBatch batch;
	private BitmapFont font;
	private BitmapFont font2;

	public void create(){

		sr=new ShapeRenderer();
		cam=new OrthographicCamera();
		pos= new Vector3(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
		cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		batch=new SpriteBatch();
		font=new BitmapFont();
		font2 = new BitmapFont();

		font.setColor(Color.GREEN);
		font.getData().setScale(5);
		font2.getData().setScale(hbdsize);

		font2.setColor(Color.BLUE);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/");
		Date date = new Date();

		String temp=formatter.format(date);

		if(temp.equals("31/08/")) {
			//wish = formatter.format(date);
			wish="HAPPY B'DAY!!!";
		}


		//wish="hello";
		//wish=strDate;

		gravity=new Vector2(0, -Gdx.graphics.getHeight()*.05f);
		float throwVelocity=Gdx.graphics.getWidth()*.2f;
		initialVelocity=new Vector2((float)(throwVelocity*Math.sin(throwAngle * Math.PI / 180)),(float)(throwVelocity*Math.cos(throwAngle * Math.PI / 180)));



	}

	public void render(){
		cam.update();

		if(Gdx.input.isTouched()){
			pos.set(Gdx.input.getX(),Gdx.input.getY(),0);
			cam.unproject(pos);

			textposx=textposx+3;
			textposy=textposy+1;

			hbdsize++;


			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/");
			Date date = new Date();

			String temp=formatter.format(date);

			if(temp.equals("31/08/")) {
				//wish = formatter.format(date);
				wish="HAPPY B'DAY!!!";
			}


			x1=0;
			y1=0;
			float throwVelocity=Gdx.graphics.getWidth()*.2f;
			initialVelocity=new Vector2((float)(throwVelocity*Math.sin(throwAngle * Math.PI / 180)),(float)(throwVelocity*Math.cos(throwAngle * Math.PI / 180)));


		}


		if(pos.y > Gdx.graphics.getHeight())
        {
		    pos.y=0;
		    textposx=100;
		    textposy = 450;
		    hbdsize=1;
        }

		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.circle(pos.x,pos.y,30);
		sr.end();






		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.circle(pos.x-50,pos.y,30);
		sr.end();





		float delta=Gdx.graphics.getDeltaTime();

		initialVelocity.x=initialVelocity.x+gravity.x*delta*deltaTime;
		initialVelocity.y=initialVelocity.y+gravity.y*delta*deltaTime;

		x1=(int)(x1+initialVelocity.x * delta * deltaTime);
		y1=(int)(y1+initialVelocity.y * delta * deltaTime);
		x2=(int)(x1+initialVelocity.x* delta * (deltaTime*50));
		y2=(int)(y1+initialVelocity.y* delta * (deltaTime*50));
		double angle=Math.atan((double)(y2-y1)/(x2-x1));

		batch.begin();
		font.draw(batch,"Angle : "+ angle,textposx,textposy);
		batch.end();


		x2= (int) (500*Math.cos(angle)+x1);
		y2= (int) (500*Math.sin(angle)+y1);

		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.line(x1,y1,x2,y2);
		sr.end();


		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.circle(x2,y2,10);
		sr.end();


		pos.y=pos.y+5;


		//batch.begin();
		//font.draw(batch,"touch...",textposx,textposy);
		//batch.end();


		font2.getData().setScale(hbdsize);

		batch.begin();
		font2.draw(batch,wish,10,Gdx.graphics.getHeight()/2);
		batch.end();


	}

	public void dispose(){
		sr.dispose();
	}



}







/*
*
* extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
*
*
*
*
*
*
*
*
* */