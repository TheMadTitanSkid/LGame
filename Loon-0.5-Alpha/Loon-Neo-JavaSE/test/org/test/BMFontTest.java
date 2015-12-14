package org.test;

import loon.LSetting;
import loon.LTransition;
import loon.LazyLoading;
import loon.Screen;
import loon.event.GameTouch;
import loon.font.LFont;
import loon.javase.Loon;
import loon.opengl.BMFont;
import loon.opengl.GLEx;
import loon.utils.timer.LTimerContext;

public class BMFontTest extends Screen {

	BMFont font;

	@Override
	public LTransition onTransition() {
		return LTransition.newEmpty();
	}

	@Override
	public void draw(GLEx g) {
		if (font != null) {
			font.drawString(g, 60, 66, "ABCFGHAX");
			font.drawString(g, 60, 166, "ZXXC\n01234");
		}
	}

	@Override
	public void onLoad() {
		// 设置默认字体大小为20号字
		LFont.setDefaultFont(LFont.getFont(20));
		try {
			font = new BMFont("info.fnt", "info.png");
			// 放大2倍
			font.setFontScale(2f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		add(MultiScreenTest.getBackButton(this));
	}

	@Override
	public void alter(LTimerContext timer) {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void touchDown(GameTouch e) {

	}

	@Override
	public void touchUp(GameTouch e) {

	}

	@Override
	public void touchMove(GameTouch e) {

	}

	@Override
	public void touchDrag(GameTouch e) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void close() {

	}

	public static void main(String[] args) {
		LSetting setting = new LSetting();
		setting.isFPS = true;
		setting.isLogo = false;
		setting.logoPath = "loon_logo.png";
		setting.fps = 60;
		setting.fontName = "黑体";
		setting.appName = "test";
		setting.emulateTouch = false;
		setting.width = 640;
		setting.height = 480;

		Loon.register(setting, new LazyLoading.Data() {

			@Override
			public Screen onScreen() {
				return new BMFontTest();
			}
		});
	}

}
