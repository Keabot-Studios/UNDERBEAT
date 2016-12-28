package net.keabotstudios.ub.game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.java.games.input.Component;
import net.keabotstudios.superin.InputAxis;

public class GameDefaults {

	public static final boolean DEBUG_MODE = false, FULLSCREEN = false, USE_XINPUT = true;
	private static final float DEADZONE = 0.125f;
	public static InputAxis[] CONTROLS = new InputAxis[] {
			new InputAxis("ENTER", KeyEvent.VK_W, Component.Identifier.Axis.Y, -DEADZONE, InputAxis.EMPTY),
			new InputAxis("BACK", KeyEvent.VK_S, Component.Identifier.Axis.Y, DEADZONE, InputAxis.EMPTY),
			new InputAxis("NOTE_1", KeyEvent.VK_A, Component.Identifier.Axis.X, -DEADZONE, InputAxis.EMPTY),
			new InputAxis("NOTE_2", KeyEvent.VK_D, Component.Identifier.Axis.X, DEADZONE, InputAxis.EMPTY),
			new InputAxis("NOTE_3", KeyEvent.VK_Q, Component.Identifier.Axis.RX, -DEADZONE, InputAxis.EMPTY),
			new InputAxis("NOTE_4", KeyEvent.VK_E, Component.Identifier.Axis.RX, DEADZONE, InputAxis.EMPTY),
			new InputAxis("NOTE_5", KeyEvent.VK_SHIFT, Component.Identifier.Button._8, 1.0f, InputAxis.EMPTY),
			new InputAxis("NOTE_6", KeyEvent.VK_CONTROL, Component.Identifier.Button._2, 1.0f, InputAxis.EMPTY),
			new InputAxis("NOTE_7", InputAxis.EMPTY, Component.Identifier.Button._6, 1.0f, MouseEvent.BUTTON1),
	};

}
