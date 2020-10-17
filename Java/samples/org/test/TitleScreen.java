package org.test;

import loon.Stage;
import loon.component.LButton;
import loon.component.LClickButton;
import loon.component.LPaper;
import loon.events.ActionKey;
import loon.events.ActionUpdate;

public class TitleScreen extends Stage {

	public void create() {

		final LButton start, end;

		final LPaper title;

		// add(MultiScreenTest.getBackButton(this,1));
		setBackground("assets/avg/back1.png");

		start = new LButton("assets/avg/title_start.png", 191, 57) {

			ActionKey action = new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);

			public void doClick() {
				if (!action.isPressed()) {
					action.press();
					// 随机使用一个Screen替换效果
					replaceScreen(new MyAVGScreen());
					// 使用固定Screen过渡效果
					// replaceScreen(new MyAVGScreen(), PageMethod.ZoomOut);
					// 使用单纯Screen移动效果
					// replaceScreen(new MyAVGScreen(),MoveMethod.FROM_LEFT);
				}
			}
		};

		start.setLocation(2, 5);

		start.setEnabled(false);

		add(start);

		LButton btn2 = new LButton("assets/avg/title_load.png", 160, 56);

		btn2.setLocation(2, start.getY() + start.getHeight() + 20);

		btn2.setEnabled(false);

		add(btn2);

		LButton btn3 = new LButton("assets/avg/title_option.png", 215, 57);

		btn3.setLocation(2, btn2.getY() + btn2.getHeight() + 20);

		btn3.setEnabled(false);

		add(btn3);

		end = new LButton("assets/avg/title_end.png", 142, 57) {
			public void doClick() {

			}
		};

		end.setLocation(2, btn3.getY() + btn3.getHeight() + 20);

		end.setEnabled(false);

		add(end);

		title = new LPaper("assets/avg/title.png", -200, 0);

		add(title);

		LClickButton click = MultiScreenTest.getBackButton(this, 1);
		add(click);

		addProcess(new ActionUpdate() {

			@Override
			public void action(Object a) {
				if (title.getScreenX() + title.getWidth() + 25 <= getWidth()) {
					title.move_right(3);
				} else {
					start.setEnabled(true);
					end.setEnabled(true);
				}
			}

			@Override
			public boolean completed() {
				return start.isEnabled() && end.isEnabled();
			}
		});

	}

}
