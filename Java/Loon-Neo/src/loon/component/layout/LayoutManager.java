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
package loon.component.layout;

import loon.LObject;
import loon.Screen;
import loon.action.ActionBind;
import loon.component.LClickButton;
import loon.component.LComponent;
import loon.component.LContainer;
import loon.event.ClickListener;
import loon.geom.Circle;
import loon.geom.RectBox;
import loon.geom.SizeValue;
import loon.utils.MathUtils;
import loon.utils.TArray;

public abstract class LayoutManager {

	public final static void elementsCircle(final Screen root, final TArray<ActionBind> objs, float x, float y,
			float radius) {
		elementsCircle(root, objs, Circle.at(x, y, radius), -1f, -1f, 0f, 0f);
	}

	public final static void elementsCircle(final Screen root, final TArray<ActionBind> objs, Circle circle,
			float startAngle, float endAngle) {
		elementsCircle(root, objs, circle, startAngle, endAngle, 0f, 0f);
	}

	/**
	 * 绘制一个圆形区域,让集合中的动作元素围绕这一圆形对象按照指定的startAngle到endAngle范围环绕
	 * 
	 * @param root
	 * @param objs
	 * @param circle
	 * @param startAngle
	 * @param endAngle
	 * @param offsetX
	 * @param offsetY
	 */
	public final static void elementsCircle(final Screen root, final TArray<ActionBind> objs, Circle circle,
			float startAngle, float endAngle, float offsetX, float offsetY) {
		if (startAngle == -1f) {
			startAngle = 0;
		}
		if (endAngle == -1f) {
			endAngle = 6.28f;
		}
		int size = objs.size;
		float angle = startAngle;
		float angleStep = (endAngle - startAngle) / size;
		for (int i = 0; i < size; i++) {
			float newX = circle.x + ((circle.radius * MathUtils.cos(angle)) + circle.radius);
			float newY = circle.y + ((circle.radius * MathUtils.sin(angle)) + circle.radius);
			ActionBind obj = objs.get(i);
			obj.setLocation(newX + offsetX, newY + offsetY);
			if (!root.contains(obj)) {
				root.add(obj);
			}
			angle += angleStep;
		}
	}

	public final static void elements(final Screen root, final TArray<ActionBind> objs, RectBox rectView) {
		elements(root, objs, rectView, 1f, 1f);
	}

	public final static void elements(final Screen root, final TArray<ActionBind> objs, RectBox rectView,
			float cellWidth, float cellHeight) {
		elements(root, objs, rectView, 1f, 1f, 2f, 2f);
	}

	/**
	 * 把指定动作对象进行布局在指定的RectBox范围内部署,并注入Screen
	 * 
	 * @param root
	 *            Screen对象
	 * @param objs
	 *            要布局的对象集合
	 * @param rectView
	 *            显示范围
	 * @param cellWidth
	 *            单独对象的默认width(如果对象有width,并且比cellWidth大,则以对象自己的为主)
	 * @param cellHeight
	 *            单独对象的默认height(如果对象有width,并且比cellWidth大,则以对象自己的为主)
	 * @param offsetX
	 *            显示坐标偏移x轴
	 * @param offsetY
	 *            显示坐标偏移y轴
	 */
	public final static void elements(final Screen root, final TArray<ActionBind> objs, RectBox rectView,
			float cellWidth, float cellHeight, float offsetX, float offsetY) {
		float blockWidth;
		float blockHeight;
		float x = rectView.x, y = rectView.y;
		for (int i = 0; i < objs.size; i++) {
			ActionBind obj = objs.get(i);
			blockWidth = MathUtils.max(cellWidth, obj.getWidth());
			blockHeight = MathUtils.max(cellHeight, obj.getHeight());
			obj.setLocation(x + offsetX, y + offsetY);
			if (!root.contains(obj)) {
				root.add(obj);
			}
			y += blockHeight + offsetY;
			if (y >= rectView.getHeight()) {
				y = rectView.y();
				x += blockWidth + offsetX;
			}
			if (x >= rectView.getWidth()) {
				x = rectView.x();
			}
		}
	}

	public final static void elements(final Screen root, final TArray<LObject<?>> objs, int sx, int sy, int cellWidth,
			int cellHeight, int maxHeight) {
		final int offsetX = 2;
		final int offsetY = 2;
		elements(root, objs, sx, sy, cellWidth, cellHeight, offsetX, offsetY, maxHeight);
	}

	public final static void elements(final Screen root, final TArray<LObject<?>> objs, int sx, int sy, int cellWidth,
			int cellHeight) {
		final int offsetX = 2;
		final int offsetY = 2;
		elements(root, objs, sx, sy, cellWidth, cellHeight, offsetX, offsetY,
				(root.getHeight() - cellHeight - offsetY));
	}

	public final static void elements(final Screen root, final TArray<LObject<?>> objs, int sx, int sy, int cellWidth,
			int cellHeight, int offsetX, int offsetY, int maxHeight) {
		int x = sx;
		int y = sy;
		for (int i = 0; i < objs.size; i++) {
			LObject<?> obj = objs.get(i);
			obj.setLocation(x + offsetX, y + offsetY);
			if (!root.contains(obj)) {
				root.add(obj);
			}
			y += cellHeight + offsetY;
			if (y >= maxHeight) {
				y = sy;
				x += cellWidth + offsetX;
			}
		}
	}

	public final static TArray<LClickButton> elementButtons(final Screen root, final String[] names, int sx, int sy,
			int cellWidth, int cellHeight, ClickListener listener, int maxHeight) {
		final int offsetX = 2;
		final int offsetY = 2;
		return elementButtons(root, names, sx, sy, cellWidth, cellHeight, offsetX, offsetY, listener, maxHeight);
	}

	public final static TArray<LClickButton> elementButtons(final Screen root, final String[] names, int sx, int sy,
			int cellWidth, int cellHeight, ClickListener listener) {
		final int offsetX = 2;
		final int offsetY = 2;
		return elementButtons(root, names, sx, sy, cellWidth, cellHeight, offsetX, offsetY, listener,
				(root.getHeight() - cellHeight - offsetY - sy));
	}

	public final static TArray<LClickButton> elementButtons(final Screen root, final String[] names, int sx, int sy,
			int cellWidth, int cellHeight, int offsetX, int offsetY, ClickListener listener, int maxHeight) {
		int x = sx;
		int y = sy;
		TArray<LClickButton> clicks = new TArray<LClickButton>(names.length);
		for (int i = 0; i < names.length; i++) {
			LClickButton click = LClickButton.make(names[i], cellWidth, cellHeight);
			click.setLocation(x + offsetX, y + offsetY);
			click.S(listener);
			if (!root.contains(click)) {
				root.add(click);
			}
			clicks.add(click);
			y += cellHeight + offsetY;
			if (y >= maxHeight) {
				y = sy;
				x += cellWidth + offsetX;
			}
		}
		return clicks;
	}

	protected boolean _allow = true;

	public final LayoutManager setChangeSize(boolean allow) {
		this._allow = allow;
		return this;
	}

	public final boolean isAllowChangeSize() {
		return _allow;
	}

	public final LayoutManager layoutElements(final Screen root, final LComponent... children) {
		int size = children.length;
		LayoutPort[] ports = new LayoutPort[size];
		for (int i = 0; i < size; i++) {
			ports[i] = children[i].getLayoutPort();
		}
		return layoutElements(root.getLayoutPort(), ports);
	}

	public final LayoutManager layoutElements(final LContainer root, final LComponent... children) {
		int size = children.length;
		LayoutPort[] ports = new LayoutPort[size];
		for (int i = 0; i < size; i++) {
			ports[i] = children[i].getLayoutPort();
		}
		layoutElements(root.getLayoutPort(), ports);
		return this;
	}

	public abstract LayoutManager layoutElements(LayoutPort root, LayoutPort... children);

	abstract SizeValue calculateConstraintWidth(LayoutPort root, TArray<LayoutPort> children);

	abstract SizeValue calculateConstraintHeight(LayoutPort root, TArray<LayoutPort> children);
}
