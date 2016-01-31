package net.richstudios.ub.editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.omg.PortableServer.ServantRetentionPolicyOperations;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Editor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabs;
	
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
		setMinimumSize(new Dimension(400, 300));
		
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
		tabs.addTab("Song", new ImageIcon(Editor.class.getResource("/icons/notePane.png")), songPane, null);
		
		JPanel eventPane = new JPanel();
		tabs.addTab("Events", new ImageIcon(Editor.class.getResource("/icons/eventPane.png")), eventPane, null);
		
		JPanel visualPane = new JPanel();
		tabs.addTab("Visual", new ImageIcon(Editor.class.getResource("/icons/visualPane.png")), visualPane, null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(Color.BLACK);
		toolBar.setForeground(new Color(0, 0, 0));
		contentPane.add(toolBar, BorderLayout.NORTH);
		
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
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue_1);
		
		setLocationRelativeTo(null);
	}
}
