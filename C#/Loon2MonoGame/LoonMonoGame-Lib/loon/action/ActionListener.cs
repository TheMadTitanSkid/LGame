﻿namespace loon.action
{
	public interface ActionListener
	{
		 void Start(ActionBind o);

		 void Process(ActionBind o);

		 void Stop(ActionBind o);
	}

}
