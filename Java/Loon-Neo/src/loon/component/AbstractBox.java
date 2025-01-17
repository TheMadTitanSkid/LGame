/**
 * Copyright 2008 - 2015 The Loon Game Engine Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @project loon
 * @author cping
 * @email：javachenpeng@yahoo.com
 * @version 0.5
 */
package loon.component;

import loon.LSysException;
import loon.LTexture;
import loon.canvas.LColor;
import loon.font.IFont;
import loon.geom.Vector2f;
import loon.geom.XY;
import loon.opengl.GLEx;

public abstract class AbstractBox implements XY {

	protected int _width;
	protected int _height;
	protected int _boxWidth;
	protected int _boxHeight;
	
	protected float _boxX;
	protected float _boxY;
	protected float _borderW;

	protected float _alpha = 1f;
	
	protected LColor borderColor = LColor.white.cpy();
	protected LColor fontColor = LColor.white.cpy();
	
	protected IFont font;
	
	protected LTexture _textureBox;
	protected int _radius;

	protected AbstractBox(IFont font) {
		if (font == null) {
			throw new LSysException("IFont is null");
		}
		this.setFont(font);
	}

	protected void init(int w, int h) {
		this._width = w;
		this._height = h;
		this._alpha = 0.65f;
		this._borderW = 3f;
		this._radius = 0;
		this.dirty();
	}

	public abstract void dirty();

	public AbstractBox setFont(IFont fn) {
		if (fn == null) {
			return this;
		}
		this.font = fn;
		dirty();
		return this;
	}

	public AbstractBox setBorderWidth(float b) {
		this._borderW = b;
		dirty();
		return this;
	}

	public void setBoxAlpha(float alpha) {
		this._alpha = alpha;
		dirty();
	}

	public void setCornerRadius(int r) {
		this._radius = r;
	}

	@Override
	public float getX() {
		return this._boxX;
	}

	@Override
	public float getY() {
		return this._boxY;
	}

	public int getWidth() {
		return this._boxWidth;
	}

	public int getHeight() {
		return this._boxHeight;
	}

	public void setLocation(float x, float y) {
		this._boxX = x;
		this._boxY = y;
	}

	public AbstractBox setLocation(Vector2f pos) {
		this._boxX = pos.x;
		this._boxY = pos.y;
		return this;
	}

	protected void drawBorder(GLEx g, float x, float y, LColor c) {
		if (this._textureBox != null) {
			g.draw(_textureBox, x, y, c);
		}
	}

	public LColor getFontColor() {
		return this.fontColor.cpy();
	}

	public void setFontColor(LColor color) {
		this.fontColor = color;
	}

	public LColor getBorderColor() {
		return this.borderColor.cpy();
	}

	public void setBorderColor(LColor color) {
		this.borderColor = color;
	}
}
