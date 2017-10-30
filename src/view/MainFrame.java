package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JCheckBox;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final MainFrame MAIN_FRAME = new MainFrame();
	
	private JPanel panelSOM;
	private JTabbedPane tabbedPane;
	private JPanel panelKMeans;
	private JPanel panelUploader;
	private JTextField textFieldSOMFile;
	private JButton buttonSOMChooseFile;
	private JLabel labelSOMRows;
	private JSpinner spinnerSOMRows;
	private JLabel labelSOMCols;
	private JSpinner spinnerSOMCols;
	private JLabel labelSOMFinalRate;
	private JSpinner spinnerSOMFinalRate;
	private JButton buttonSOMStart;
	private JProgressBar progressBarSOMStatus;
	private JScrollPane scrollPaneSOMStatus;
	private JTextArea textAreaSOMStatus;
	private JTextField textFieldKMeansFile;
	private JButton buttonKMeansChooseFile;
	private JLabel labelKMeansK;
	private JSpinner spinnerKMeansK;
	private JLabel labelKMeansIterations;
	private JSpinner spinnerKMeansIterations;
	private JButton buttonKMeansStart;
	private JProgressBar progressBarKMeansStatus;
	private JScrollPane scrollPaneKMeansStatus;
	private JTextArea textAreaKMeansStatus;
	private JTextField textFieldUploaderFileWeights;
	private JButton buttonUploaderChooseFileWeights;
	private JTextField textFieldUploaderFileInstances;
	private JButton buttonUploaderChooseFileInstances;
	private JTextField textFieldUploaderPathSave;
	private JButton buttonUploaderChoosePathSave;
	private JScrollPane scrollPaneUploaderStatus;
	private JTextArea textAreaUploaderStatus;
	private JButton buttonUploaderStart;
	private JCheckBox checkboxSOMHeader;
	private JCheckBox checkboxKMeansHeader;
	private JLabel labelUploaderWeights;
	private JLabel labelUploaderInstances;
	private JLabel labelUploaderSave;
	private JPanel panelChi;
	private JTextField textFieldChiFiles;
	private JButton buttonChiChooseFiles;
	private JScrollPane scrollPaneChiStatus;
	private JTextArea textAreaChiStatus;
	private JButton buttonChiStart;

	private MainFrame() {
		setResizable(false);
		initComponents();
	}
	
	public static MainFrame getInstance() {
		return MainFrame.MAIN_FRAME;
	}
	
	private void initComponents() {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setTitle("Out of the Ordinary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		contentPane.add(tabbedPane, gbc_tabbedPane);
		
		panelSOM = new JPanel();
		panelSOM.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("SOM", null, panelSOM, null);
		GridBagLayout gbl_panelSOM = new GridBagLayout();
		gbl_panelSOM.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelSOM.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelSOM.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelSOM.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelSOM.setLayout(gbl_panelSOM);
		
		textFieldSOMFile = new JTextField();
		textFieldSOMFile.setOpaque(false);
		textFieldSOMFile.setEditable(false);
		GridBagConstraints gbc_textFieldSOMFile = new GridBagConstraints();
		gbc_textFieldSOMFile.gridwidth = 6;
		gbc_textFieldSOMFile.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSOMFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSOMFile.gridx = 0;
		gbc_textFieldSOMFile.gridy = 0;
		panelSOM.add(textFieldSOMFile, gbc_textFieldSOMFile);
		textFieldSOMFile.setColumns(10);
		
		buttonSOMChooseFile = new JButton("Choose File...");
		GridBagConstraints gbc_buttonSOMChooseFile = new GridBagConstraints();
		gbc_buttonSOMChooseFile.insets = new Insets(0, 0, 5, 0);
		gbc_buttonSOMChooseFile.gridx = 6;
		gbc_buttonSOMChooseFile.gridy = 0;
		panelSOM.add(buttonSOMChooseFile, gbc_buttonSOMChooseFile);
		
		checkboxSOMHeader = new JCheckBox("The file contains a header");
		checkboxSOMHeader.setSelected(true);
		checkboxSOMHeader.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_checkboxSOMHeader = new GridBagConstraints();
		gbc_checkboxSOMHeader.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkboxSOMHeader.gridwidth = 7;
		gbc_checkboxSOMHeader.insets = new Insets(0, 0, 5, 5);
		gbc_checkboxSOMHeader.gridx = 0;
		gbc_checkboxSOMHeader.gridy = 1;
		panelSOM.add(checkboxSOMHeader, gbc_checkboxSOMHeader);
		
		labelSOMRows = new JLabel("Rows:");
		GridBagConstraints gbc_labelSOMRows = new GridBagConstraints();
		gbc_labelSOMRows.anchor = GridBagConstraints.WEST;
		gbc_labelSOMRows.insets = new Insets(0, 0, 5, 5);
		gbc_labelSOMRows.gridx = 0;
		gbc_labelSOMRows.gridy = 2;
		panelSOM.add(labelSOMRows, gbc_labelSOMRows);
		
		spinnerSOMRows = new JSpinner();
		spinnerSOMRows.setModel(new SpinnerNumberModel(2, 2, 50, 1));
		GridBagConstraints gbc_spinnerSOMRows = new GridBagConstraints();
		gbc_spinnerSOMRows.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerSOMRows.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerSOMRows.gridx = 1;
		gbc_spinnerSOMRows.gridy = 2;
		panelSOM.add(spinnerSOMRows, gbc_spinnerSOMRows);
		
		labelSOMCols = new JLabel("Cols:");
		GridBagConstraints gbc_labelSOMCols = new GridBagConstraints();
		gbc_labelSOMCols.anchor = GridBagConstraints.WEST;
		gbc_labelSOMCols.insets = new Insets(0, 0, 5, 5);
		gbc_labelSOMCols.gridx = 2;
		gbc_labelSOMCols.gridy = 2;
		panelSOM.add(labelSOMCols, gbc_labelSOMCols);
		
		spinnerSOMCols = new JSpinner();
		spinnerSOMCols.setModel(new SpinnerNumberModel(2, 2, 50, 1));
		GridBagConstraints gbc_spinnerSOMCols = new GridBagConstraints();
		gbc_spinnerSOMCols.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerSOMCols.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerSOMCols.gridx = 3;
		gbc_spinnerSOMCols.gridy = 2;
		panelSOM.add(spinnerSOMCols, gbc_spinnerSOMCols);
		
		labelSOMFinalRate = new JLabel("Final Rate:");
		GridBagConstraints gbc_labelSOMFinalRate = new GridBagConstraints();
		gbc_labelSOMFinalRate.anchor = GridBagConstraints.WEST;
		gbc_labelSOMFinalRate.insets = new Insets(0, 0, 5, 5);
		gbc_labelSOMFinalRate.gridx = 4;
		gbc_labelSOMFinalRate.gridy = 2;
		panelSOM.add(labelSOMFinalRate, gbc_labelSOMFinalRate);
		
		spinnerSOMFinalRate = new JSpinner();
		spinnerSOMFinalRate.setModel(new SpinnerNumberModel(new Float((float) 0.1), new Float((float) 0.05), new Float(1), new Float((float) 0.05)));
		GridBagConstraints gbc_spinnerSOMFinalRate = new GridBagConstraints();
		gbc_spinnerSOMFinalRate.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerSOMFinalRate.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerSOMFinalRate.gridx = 5;
		gbc_spinnerSOMFinalRate.gridy = 2;
		panelSOM.add(spinnerSOMFinalRate, gbc_spinnerSOMFinalRate);
		
		buttonSOMStart = new JButton("Start");
		buttonSOMStart.setEnabled(false);
		GridBagConstraints gbc_buttonSOMStart = new GridBagConstraints();
		gbc_buttonSOMStart.insets = new Insets(0, 0, 5, 0);
		gbc_buttonSOMStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonSOMStart.gridx = 6;
		gbc_buttonSOMStart.gridy = 2;
		panelSOM.add(buttonSOMStart, gbc_buttonSOMStart);
		
		progressBarSOMStatus = new JProgressBar();
		GridBagConstraints gbc_progressBarSOMStatus = new GridBagConstraints();
		gbc_progressBarSOMStatus.insets = new Insets(0, 0, 5, 0);
		gbc_progressBarSOMStatus.fill = GridBagConstraints.BOTH;
		gbc_progressBarSOMStatus.gridwidth = 7;
		gbc_progressBarSOMStatus.gridx = 0;
		gbc_progressBarSOMStatus.gridy = 3;
		panelSOM.add(progressBarSOMStatus, gbc_progressBarSOMStatus);
		
		scrollPaneSOMStatus = new JScrollPane();
		GridBagConstraints gbc_scrollPaneSOMStatus = new GridBagConstraints();
		gbc_scrollPaneSOMStatus.gridwidth = 7;
		gbc_scrollPaneSOMStatus.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneSOMStatus.gridx = 0;
		gbc_scrollPaneSOMStatus.gridy = 4;
		panelSOM.add(scrollPaneSOMStatus, gbc_scrollPaneSOMStatus);
		
		textAreaSOMStatus = new JTextArea();
		textAreaSOMStatus.setWrapStyleWord(true);
		textAreaSOMStatus.setLineWrap(true);
		scrollPaneSOMStatus.setViewportView(textAreaSOMStatus);
		
		panelKMeans = new JPanel();
		panelKMeans.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("K-Means", null, panelKMeans, null);
		GridBagLayout gbl_panelKMeans = new GridBagLayout();
		gbl_panelKMeans.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelKMeans.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelKMeans.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelKMeans.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelKMeans.setLayout(gbl_panelKMeans);
		
		textFieldKMeansFile = new JTextField();
		textFieldKMeansFile.setOpaque(false);
		textFieldKMeansFile.setEditable(false);
		GridBagConstraints gbc_textFieldKMeansFile = new GridBagConstraints();
		gbc_textFieldKMeansFile.gridwidth = 5;
		gbc_textFieldKMeansFile.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldKMeansFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldKMeansFile.gridx = 0;
		gbc_textFieldKMeansFile.gridy = 0;
		panelKMeans.add(textFieldKMeansFile, gbc_textFieldKMeansFile);
		textFieldKMeansFile.setColumns(10);
		
		buttonKMeansChooseFile = new JButton("Choose File...");
		GridBagConstraints gbc_buttonKMeansChooseFile = new GridBagConstraints();
		gbc_buttonKMeansChooseFile.insets = new Insets(0, 0, 5, 0);
		gbc_buttonKMeansChooseFile.gridx = 5;
		gbc_buttonKMeansChooseFile.gridy = 0;
		panelKMeans.add(buttonKMeansChooseFile, gbc_buttonKMeansChooseFile);
		
		checkboxKMeansHeader = new JCheckBox("The file contains a header");
		checkboxKMeansHeader.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_checkboxKMeansHeader = new GridBagConstraints();
		gbc_checkboxKMeansHeader.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkboxKMeansHeader.gridwidth = 6;
		gbc_checkboxKMeansHeader.insets = new Insets(0, 0, 5, 5);
		gbc_checkboxKMeansHeader.gridx = 0;
		gbc_checkboxKMeansHeader.gridy = 1;
		panelKMeans.add(checkboxKMeansHeader, gbc_checkboxKMeansHeader);
		
		labelKMeansK = new JLabel("k:");
		GridBagConstraints gbc_labelKMeansK = new GridBagConstraints();
		gbc_labelKMeansK.anchor = GridBagConstraints.WEST;
		gbc_labelKMeansK.insets = new Insets(0, 0, 5, 5);
		gbc_labelKMeansK.gridx = 0;
		gbc_labelKMeansK.gridy = 2;
		panelKMeans.add(labelKMeansK, gbc_labelKMeansK);
		
		spinnerKMeansK = new JSpinner();
		spinnerKMeansK.setModel(new SpinnerNumberModel(new Integer(2), new Integer(2), null, new Integer(1)));
		GridBagConstraints gbc_spinnerKMeansK = new GridBagConstraints();
		gbc_spinnerKMeansK.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerKMeansK.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerKMeansK.gridx = 1;
		gbc_spinnerKMeansK.gridy = 2;
		panelKMeans.add(spinnerKMeansK, gbc_spinnerKMeansK);
		
		labelKMeansIterations = new JLabel("Iterations:");
		GridBagConstraints gbc_labelKMeansIterations = new GridBagConstraints();
		gbc_labelKMeansIterations.anchor = GridBagConstraints.WEST;
		gbc_labelKMeansIterations.insets = new Insets(0, 0, 5, 5);
		gbc_labelKMeansIterations.gridx = 3;
		gbc_labelKMeansIterations.gridy = 2;
		panelKMeans.add(labelKMeansIterations, gbc_labelKMeansIterations);
		
		spinnerKMeansIterations = new JSpinner();
		spinnerKMeansIterations.setModel(new SpinnerNumberModel(new Integer(100), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spinnerKMeansIterations = new GridBagConstraints();
		gbc_spinnerKMeansIterations.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerKMeansIterations.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerKMeansIterations.gridx = 4;
		gbc_spinnerKMeansIterations.gridy = 2;
		panelKMeans.add(spinnerKMeansIterations, gbc_spinnerKMeansIterations);
		
		buttonKMeansStart = new JButton("Start");
		buttonKMeansStart.setEnabled(false);
		GridBagConstraints gbc_buttonKMeansStart = new GridBagConstraints();
		gbc_buttonKMeansStart.insets = new Insets(0, 0, 5, 0);
		gbc_buttonKMeansStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonKMeansStart.gridx = 5;
		gbc_buttonKMeansStart.gridy = 2;
		panelKMeans.add(buttonKMeansStart, gbc_buttonKMeansStart);
		
		progressBarKMeansStatus = new JProgressBar();
		GridBagConstraints gbc_progressBarKMeansStatus = new GridBagConstraints();
		gbc_progressBarKMeansStatus.insets = new Insets(0, 0, 5, 0);
		gbc_progressBarKMeansStatus.fill = GridBagConstraints.BOTH;
		gbc_progressBarKMeansStatus.gridwidth = 6;
		gbc_progressBarKMeansStatus.gridx = 0;
		gbc_progressBarKMeansStatus.gridy = 3;
		panelKMeans.add(progressBarKMeansStatus, gbc_progressBarKMeansStatus);
		
		scrollPaneKMeansStatus = new JScrollPane();
		GridBagConstraints gbc_scrollPaneKMeansStatus = new GridBagConstraints();
		gbc_scrollPaneKMeansStatus.gridwidth = 6;
		gbc_scrollPaneKMeansStatus.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneKMeansStatus.gridx = 0;
		gbc_scrollPaneKMeansStatus.gridy = 4;
		panelKMeans.add(scrollPaneKMeansStatus, gbc_scrollPaneKMeansStatus);
		
		textAreaKMeansStatus = new JTextArea();
		textAreaKMeansStatus.setLineWrap(true);
		textAreaKMeansStatus.setWrapStyleWord(true);
		scrollPaneKMeansStatus.setViewportView(textAreaKMeansStatus);
		
		panelUploader = new JPanel();
		panelUploader.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("SOM Uploader", null, panelUploader, null);
		GridBagLayout gbl_panelUploader = new GridBagLayout();
		gbl_panelUploader.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelUploader.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelUploader.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelUploader.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelUploader.setLayout(gbl_panelUploader);
		
		labelUploaderWeights = new JLabel("Cluster Files:");
		GridBagConstraints gbc_labelUploaderWeights = new GridBagConstraints();
		gbc_labelUploaderWeights.fill = GridBagConstraints.BOTH;
		gbc_labelUploaderWeights.insets = new Insets(0, 0, 5, 5);
		gbc_labelUploaderWeights.gridx = 0;
		gbc_labelUploaderWeights.gridy = 0;
		panelUploader.add(labelUploaderWeights, gbc_labelUploaderWeights);
		
		textFieldUploaderFileWeights = new JTextField();
		textFieldUploaderFileWeights.setOpaque(false);
		textFieldUploaderFileWeights.setEditable(false);
		GridBagConstraints gbc_textFieldUploaderFileWeights = new GridBagConstraints();
		gbc_textFieldUploaderFileWeights.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUploaderFileWeights.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUploaderFileWeights.gridx = 1;
		gbc_textFieldUploaderFileWeights.gridy = 0;
		panelUploader.add(textFieldUploaderFileWeights, gbc_textFieldUploaderFileWeights);
		textFieldUploaderFileWeights.setColumns(10);
		
		buttonUploaderChooseFileWeights = new JButton("Choose Files...");
		GridBagConstraints gbc_buttonUploaderChooseFileWeights = new GridBagConstraints();
		gbc_buttonUploaderChooseFileWeights.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonUploaderChooseFileWeights.insets = new Insets(0, 0, 5, 0);
		gbc_buttonUploaderChooseFileWeights.gridx = 2;
		gbc_buttonUploaderChooseFileWeights.gridy = 0;
		panelUploader.add(buttonUploaderChooseFileWeights, gbc_buttonUploaderChooseFileWeights);
		
		labelUploaderInstances = new JLabel("Instances File:");
		GridBagConstraints gbc_labelUploaderInstances = new GridBagConstraints();
		gbc_labelUploaderInstances.fill = GridBagConstraints.BOTH;
		gbc_labelUploaderInstances.insets = new Insets(0, 0, 5, 5);
		gbc_labelUploaderInstances.gridx = 0;
		gbc_labelUploaderInstances.gridy = 1;
		panelUploader.add(labelUploaderInstances, gbc_labelUploaderInstances);
		
		textFieldUploaderFileInstances = new JTextField();
		textFieldUploaderFileInstances.setEditable(false);
		textFieldUploaderFileInstances.setOpaque(false);
		GridBagConstraints gbc_textFieldUploaderFileInstances = new GridBagConstraints();
		gbc_textFieldUploaderFileInstances.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUploaderFileInstances.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUploaderFileInstances.gridx = 1;
		gbc_textFieldUploaderFileInstances.gridy = 1;
		panelUploader.add(textFieldUploaderFileInstances, gbc_textFieldUploaderFileInstances);
		textFieldUploaderFileInstances.setColumns(10);
		
		buttonUploaderChooseFileInstances = new JButton("Choose File...");
		GridBagConstraints gbc_buttonUploaderChooseFileInstances = new GridBagConstraints();
		gbc_buttonUploaderChooseFileInstances.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonUploaderChooseFileInstances.insets = new Insets(0, 0, 5, 0);
		gbc_buttonUploaderChooseFileInstances.gridx = 2;
		gbc_buttonUploaderChooseFileInstances.gridy = 1;
		panelUploader.add(buttonUploaderChooseFileInstances, gbc_buttonUploaderChooseFileInstances);
		
		labelUploaderSave = new JLabel("Save Path:");
		GridBagConstraints gbc_labelUploaderSave = new GridBagConstraints();
		gbc_labelUploaderSave.fill = GridBagConstraints.BOTH;
		gbc_labelUploaderSave.insets = new Insets(0, 0, 5, 5);
		gbc_labelUploaderSave.gridx = 0;
		gbc_labelUploaderSave.gridy = 2;
		panelUploader.add(labelUploaderSave, gbc_labelUploaderSave);
		
		textFieldUploaderPathSave = new JTextField();
		textFieldUploaderPathSave.setOpaque(false);
		textFieldUploaderPathSave.setEditable(false);
		GridBagConstraints gbc_textFieldUploaderPathSave = new GridBagConstraints();
		gbc_textFieldUploaderPathSave.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUploaderPathSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUploaderPathSave.gridx = 1;
		gbc_textFieldUploaderPathSave.gridy = 2;
		panelUploader.add(textFieldUploaderPathSave, gbc_textFieldUploaderPathSave);
		textFieldUploaderPathSave.setColumns(10);
		
		buttonUploaderChoosePathSave = new JButton("Choose Path...");
		GridBagConstraints gbc_buttonUploaderChoosePathSave = new GridBagConstraints();
		gbc_buttonUploaderChoosePathSave.insets = new Insets(0, 0, 5, 0);
		gbc_buttonUploaderChoosePathSave.gridx = 2;
		gbc_buttonUploaderChoosePathSave.gridy = 2;
		panelUploader.add(buttonUploaderChoosePathSave, gbc_buttonUploaderChoosePathSave);
		
		buttonUploaderStart = new JButton("Start");
		buttonUploaderStart.setEnabled(false);
		GridBagConstraints gbc_buttonUploaderStart = new GridBagConstraints();
		gbc_buttonUploaderStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonUploaderStart.insets = new Insets(0, 0, 5, 0);
		gbc_buttonUploaderStart.gridx = 2;
		gbc_buttonUploaderStart.gridy = 3;
		panelUploader.add(buttonUploaderStart, gbc_buttonUploaderStart);
		
		scrollPaneUploaderStatus = new JScrollPane();
		GridBagConstraints gbc_scrollPaneUploaderStatus = new GridBagConstraints();
		gbc_scrollPaneUploaderStatus.gridwidth = 3;
		gbc_scrollPaneUploaderStatus.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneUploaderStatus.gridx = 0;
		gbc_scrollPaneUploaderStatus.gridy = 4;
		panelUploader.add(scrollPaneUploaderStatus, gbc_scrollPaneUploaderStatus);
		
		textAreaUploaderStatus = new JTextArea();
		textAreaUploaderStatus.setLineWrap(true);
		textAreaUploaderStatus.setWrapStyleWord(true);
		scrollPaneUploaderStatus.setViewportView(textAreaUploaderStatus);
		
		panelChi = new JPanel();
		panelChi.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Chi Test", null, panelChi, null);
		GridBagLayout gbl_panelChi = new GridBagLayout();
		gbl_panelChi.columnWidths = new int[]{0, 0, 0};
		gbl_panelChi.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelChi.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelChi.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelChi.setLayout(gbl_panelChi);
		
		textFieldChiFiles = new JTextField();
		textFieldChiFiles.setOpaque(false);
		textFieldChiFiles.setEditable(false);
		GridBagConstraints gbc_textFieldChiFiles = new GridBagConstraints();
		gbc_textFieldChiFiles.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldChiFiles.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldChiFiles.gridx = 0;
		gbc_textFieldChiFiles.gridy = 0;
		panelChi.add(textFieldChiFiles, gbc_textFieldChiFiles);
		textFieldChiFiles.setColumns(10);
		
		buttonChiChooseFiles = new JButton("Choose Files...");
		GridBagConstraints gbc_buttonChiChooseFiles = new GridBagConstraints();
		gbc_buttonChiChooseFiles.fill = GridBagConstraints.BOTH;
		gbc_buttonChiChooseFiles.insets = new Insets(0, 0, 5, 0);
		gbc_buttonChiChooseFiles.gridx = 1;
		gbc_buttonChiChooseFiles.gridy = 0;
		panelChi.add(buttonChiChooseFiles, gbc_buttonChiChooseFiles);
		
		buttonChiStart = new JButton("Start");
		buttonChiStart.setEnabled(false);
		GridBagConstraints gbc_buttonChiStart = new GridBagConstraints();
		gbc_buttonChiStart.fill = GridBagConstraints.BOTH;
		gbc_buttonChiStart.insets = new Insets(0, 0, 5, 0);
		gbc_buttonChiStart.gridx = 1;
		gbc_buttonChiStart.gridy = 1;
		panelChi.add(buttonChiStart, gbc_buttonChiStart);
		
		scrollPaneChiStatus = new JScrollPane();
		GridBagConstraints gbc_scrollPaneChiStatus = new GridBagConstraints();
		gbc_scrollPaneChiStatus.gridwidth = 2;
		gbc_scrollPaneChiStatus.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneChiStatus.gridx = 0;
		gbc_scrollPaneChiStatus.gridy = 2;
		panelChi.add(scrollPaneChiStatus, gbc_scrollPaneChiStatus);
		
		textAreaChiStatus = new JTextArea();
		textAreaChiStatus.setLineWrap(true);
		textAreaChiStatus.setWrapStyleWord(true);
		scrollPaneChiStatus.setViewportView(textAreaChiStatus);
		
	}

	public JTextField getTextFieldSOMFile() {
		return textFieldSOMFile;
	}

	public JButton getButtonSOMChooseFile() {
		return buttonSOMChooseFile;
	}

	public JSpinner getSpinnerSOMRows() {
		return spinnerSOMRows;
	}

	public JSpinner getSpinnerSOMCols() {
		return spinnerSOMCols;
	}

	public JSpinner getSpinnerSOMFinalRate() {
		return spinnerSOMFinalRate;
	}

	public JButton getButtonSOMStart() {
		return buttonSOMStart;
	}

	public JProgressBar getProgressBarSOMStatus() {
		return progressBarSOMStatus;
	}

	public JTextArea getTextAreaSOMStatus() {
		return textAreaSOMStatus;
	}

	public JTextField getTextFieldKMeansFile() {
		return textFieldKMeansFile;
	}

	public JButton getButtonKMeansChooseFile() {
		return buttonKMeansChooseFile;
	}

	public JSpinner getSpinnerKMeansK() {
		return spinnerKMeansK;
	}

	public JSpinner getSpinnerKMeansIterations() {
		return spinnerKMeansIterations;
	}

	public JButton getButtonKMeansStart() {
		return buttonKMeansStart;
	}

	public JProgressBar getProgressBarKMeansStatus() {
		return progressBarKMeansStatus;
	}

	public JTextArea getTextAreaKMeansStatus() {
		return textAreaKMeansStatus;
	}

	public JTextField getTextFieldUploaderFileWeights() {
		return textFieldUploaderFileWeights;
	}

	public JButton getButtonUploaderChooseFileWeights() {
		return buttonUploaderChooseFileWeights;
	}

	public JTextField getTextFieldUploaderFileInstances() {
		return textFieldUploaderFileInstances;
	}

	public JButton getButtonUploaderChooseFileInstances() {
		return buttonUploaderChooseFileInstances;
	}

	public JTextField getTextFieldUploaderPathSave() {
		return textFieldUploaderPathSave;
	}

	public JButton getButtonUploaderChoosePathSave() {
		return buttonUploaderChoosePathSave;
	}

	public JTextArea getTextAreaUploaderStatus() {
		return textAreaUploaderStatus;
	}

	public JButton getButtonUploaderStart() {
		return buttonUploaderStart;
	}
	
	public JCheckBox getCheckboxSOMHeader() {
		return checkboxSOMHeader;
	}
	
	public JCheckBox getCheckBoxKMeansHeader() {
		return checkboxKMeansHeader;
	}
	
	public JTextField getTextFieldChiFiles() {
		return textFieldChiFiles;
	}
	
	public JButton getButtonChiChooseFiles() {
		return buttonChiChooseFiles;
	}
	
	public JTextArea getTextAreaChiStatus() {
		return textAreaChiStatus;
	}
	
	public JButton getButtonChiStart() {
		return buttonChiStart;
	}

}
