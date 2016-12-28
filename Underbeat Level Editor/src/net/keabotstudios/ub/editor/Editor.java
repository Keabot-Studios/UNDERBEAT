package net.keabotstudios.ub.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class Editor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabs;
	private VisualPane viewPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor frame = new Editor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Editor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editor.class.getResource("/icons/editorIcon64.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		setTitle("UNDERBEAT Level Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 500);
		setMinimumSize(new Dimension(800, 600));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setIcon(new ImageIcon(Editor.class.getResource("/icons/newMenuIcon.png")));
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon(Editor.class.getResource("/icons/openMenuIcon.png")));
		mnFile.add(mntmOpen);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setIcon(new ImageIcon(Editor.class.getResource("/icons/saveMenuIcon.png")));
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.setIcon(new ImageIcon(Editor.class.getResource("/icons/saveAsMenuIcon.png")));
		mnFile.add(mntmSaveAs);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnTest = new JMenu("Test");
		menuBar.add(mnTest);
		
		JMenu mnWindow = new JMenu("Window");
		menuBar.add(mnWindow);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabs = new JTabbedPane(JTabbedPane.BOTTOM);
		tabs.setBackground(Color.LIGHT_GRAY);
		tabs.setBorder(null);
		contentPane.add(tabs, BorderLayout.CENTER);
		
		SongPane songPane = new SongPane();
		tabs.addTab("Notes", new ImageIcon(Editor.class.getResource("/icons/notePane.png")), songPane, null);
		songPane.setLayout(new BorderLayout(0, 0));
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		songPane.add(scrollBar, BorderLayout.NORTH);
		
		JPanel eventPane = new JPanel();
		tabs.addTab("Events", new ImageIcon(Editor.class.getResource("/icons/eventPane.png")), eventPane, null);
		
		JPanel visualPane = new JPanel();
		tabs.addTab("Visual", new ImageIcon(Editor.class.getResource("/icons/visualPane.png")), visualPane, null);
		visualPane.setLayout(new BorderLayout(0, 0));
		
		viewPane = new VisualPane();
		visualPane.add(viewPane, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		visualPane.add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JToolBar visualToolBar = new JToolBar();
		visualToolBar.setOrientation(SwingConstants.VERTICAL);
		rightPanel.add(visualToolBar, BorderLayout.WEST);
		visualToolBar.setFloatable(false);
		
		JButton btnNewButton = new JButton("New Part");
		visualToolBar.add(btnNewButton);
		btnNewButton.setToolTipText("Add Part");
		btnNewButton.setIcon(new ImageIcon(Editor.class.getResource("/icons/addPart.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnRemovePart = new JButton("Remove Part");
		btnRemovePart.setToolTipText("Remove Part");
		btnRemovePart.setIcon(new ImageIcon(Editor.class.getResource("/icons/removePart.png")));
		visualToolBar.add(btnRemovePart);
		
		JButton btnDuplicate = new JButton("Duplicate Part");
		btnDuplicate.setIcon(new ImageIcon(Editor.class.getResource("/icons/duplicatePart.png")));
		visualToolBar.add(btnDuplicate);
		
		JButton btnSetSpritesheet = new JButton("Load Spritesheet");
		btnSetSpritesheet.setIcon(new ImageIcon(Editor.class.getResource("/icons/loadSpritesheet.png")));
		btnSetSpritesheet.setToolTipText("Load Spritesheet");
		visualToolBar.add(btnSetSpritesheet);
		
		JPanel listPanel = new JPanel();
		rightPanel.add(listPanel, BorderLayout.CENTER);
		listPanel.setLayout(new BorderLayout(0, 0));
		
		JList<String> partList = new JList<String>();
		partList.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		listPanel.add(partList);
		partList.setForeground(Color.WHITE);
		partList.setBackground(Color.BLACK);
		partList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		partList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		partList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"L Arm", "R Arm", "Torso"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		listPanel.add(horizontalGlue_2, BorderLayout.NORTH);
		
		SpriteSheetPane spriteSheetPane = new SpriteSheetPane();
		spriteSheetPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rightPanel.add(spriteSheetPane, BorderLayout.SOUTH);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(Color.BLACK);
		toolBar.setForeground(new Color(0, 0, 0));
		contentPane.add(toolBar, BorderLayout.SOUTH);
		
		JButton btnNew = new JButton("");
		btnNew.setSelectedIcon(new ImageIcon(Editor.class.getResource("/icons/newIconSel.png")));
		btnNew.setBackground(Color.BLACK);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);
		btnNew.setIcon(new ImageIcon(Editor.class.getResource("/icons/newIcon.png")));
		toolBar.add(btnNew);
		
		JButton btnOpen = new JButton("");
		btnOpen.setSelectedIcon(new ImageIcon(Editor.class.getResource("/icons/openIconSel.png")));
		btnOpen.setIcon(new ImageIcon(Editor.class.getResource("/icons/openIcon.png")));
		btnOpen.setBackground(Color.BLACK);
		toolBar.add(btnOpen);
		
		JButton btnSave = new JButton("");
		btnSave.setSelectedIcon(new ImageIcon(Editor.class.getResource("/icons/saveIconSel.png")));
		btnSave.setBackground(Color.BLACK);
		btnSave.setIcon(new ImageIcon(Editor.class.getResource("/icons/saveIcon.png")));
		toolBar.add(btnSave);
		
		JButton btnTest = new JButton("");
		btnTest.setSelectedIcon(new ImageIcon(Editor.class.getResource("/icons/testIconSel.png")));
		btnTest.setIcon(new ImageIcon(Editor.class.getResource("/icons/testIcon.png")));
		btnTest.setBackground(Color.BLACK);
		toolBar.add(btnTest);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue_1);
		
		setLocationRelativeTo(null);
	}
}
