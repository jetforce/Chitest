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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JTextField textFieldFile1;
	private JButton buttonChooseFile1;
	private JComboBox cmbTestType;
	private JTextField textFieldFile2;
	private JButton buttonChooseFile2;
	

	private JScrollPane scrollPaneChiStatus;
	private JTextArea textAreaChiStatus;
	private JButton buttonChiStart;
	private JPanel panelDescriptor;
	private JLabel labelDescriptorVarFile;
	private JTextField textFieldDescriptorVarFile;
	private JButton buttonDescriptorVarFileChooseFile;
	private JLabel labelDescriptorValFile;
	private JTextField textFieldDescriptorValFile;
	private JButton buttonDescriptorValFileChooseFile;
	private JButton buttonDescriptorStart;
	private JScrollPane scrollPaneDescriptorStatus;
	private JTextArea textAreaDescriptorStatus;
	private JPanel panelPreprocessor;
	private JLabel labelPreprocessorVarDesFile;
	private JLabel labelPreprocessorRawFile;
	private JTextField textFieldPreprocessorVarDesFile;
	private JTextField textFieldPreprocessorRawFile;
	private JButton buttonPreprocessorVarDesFileChooseFile;
	private JButton buttonPreprocessorRawFileChooseFile;
	private JButton buttonPreprocessorStart;
	private JScrollPane scrollPanePreprocessorStatus;
	private JTextArea textAreaPreprocessorStatus;
	private JLabel lblNewLabel;
	

	private JTextField textFieldFeature;
	private JButton buttonEnterFeature;
	private JList listAttributes;
	private JTextArea textAreaFeature;
	private JButton buttonStore;
	private JComboBox cmbSEPFeatures;
	private JLabel lblGetSamplesBy;

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JComboBox getCmbTestType() {
		return cmbTestType;
	}

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
		setBounds(100, 100, 613, 640);
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
		
		panelDescriptor = new JPanel();
		panelDescriptor.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Variable Descriptor", null, panelDescriptor, null);
		GridBagLayout gbl_panelDescriptor = new GridBagLayout();
		gbl_panelDescriptor.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelDescriptor.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelDescriptor.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelDescriptor.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelDescriptor.setLayout(gbl_panelDescriptor);
		
		labelDescriptorVarFile = new JLabel("Variable File:");
		GridBagConstraints gbc_labelDescriptorVarFile = new GridBagConstraints();
		gbc_labelDescriptorVarFile.fill = GridBagConstraints.BOTH;
		gbc_labelDescriptorVarFile.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescriptorVarFile.gridx = 0;
		gbc_labelDescriptorVarFile.gridy = 0;
		panelDescriptor.add(labelDescriptorVarFile, gbc_labelDescriptorVarFile);
		
		textFieldDescriptorVarFile = new JTextField();
		GridBagConstraints gbc_textFieldDescriptorVarFile = new GridBagConstraints();
		gbc_textFieldDescriptorVarFile.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescriptorVarFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescriptorVarFile.gridx = 1;
		gbc_textFieldDescriptorVarFile.gridy = 0;
		panelDescriptor.add(textFieldDescriptorVarFile, gbc_textFieldDescriptorVarFile);
		textFieldDescriptorVarFile.setColumns(10);
		
		buttonDescriptorVarFileChooseFile = new JButton("Choose File...");
		GridBagConstraints gbc_buttonDescriptorVarFileChooseFile = new GridBagConstraints();
		gbc_buttonDescriptorVarFileChooseFile.fill = GridBagConstraints.BOTH;
		gbc_buttonDescriptorVarFileChooseFile.insets = new Insets(0, 0, 5, 0);
		gbc_buttonDescriptorVarFileChooseFile.gridx = 2;
		gbc_buttonDescriptorVarFileChooseFile.gridy = 0;
		panelDescriptor.add(buttonDescriptorVarFileChooseFile, gbc_buttonDescriptorVarFileChooseFile);
		
		labelDescriptorValFile = new JLabel("Values File:");
		GridBagConstraints gbc_labelDescriptorValFile = new GridBagConstraints();
		gbc_labelDescriptorValFile.fill = GridBagConstraints.BOTH;
		gbc_labelDescriptorValFile.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescriptorValFile.gridx = 0;
		gbc_labelDescriptorValFile.gridy = 1;
		panelDescriptor.add(labelDescriptorValFile, gbc_labelDescriptorValFile);
		
		textFieldDescriptorValFile = new JTextField();
		GridBagConstraints gbc_textFieldDescriptorValFile = new GridBagConstraints();
		gbc_textFieldDescriptorValFile.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescriptorValFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescriptorValFile.gridx = 1;
		gbc_textFieldDescriptorValFile.gridy = 1;
		panelDescriptor.add(textFieldDescriptorValFile, gbc_textFieldDescriptorValFile);
		textFieldDescriptorValFile.setColumns(10);
		
		buttonDescriptorValFileChooseFile = new JButton("Choose File...");
		GridBagConstraints gbc_buttonDescriptorValFileChooseFile = new GridBagConstraints();
		gbc_buttonDescriptorValFileChooseFile.insets = new Insets(0, 0, 5, 0);
		gbc_buttonDescriptorValFileChooseFile.fill = GridBagConstraints.BOTH;
		gbc_buttonDescriptorValFileChooseFile.gridx = 2;
		gbc_buttonDescriptorValFileChooseFile.gridy = 1;
		panelDescriptor.add(buttonDescriptorValFileChooseFile, gbc_buttonDescriptorValFileChooseFile);
		
		buttonDescriptorStart = new JButton("Start");
		buttonDescriptorStart.setEnabled(false);
		GridBagConstraints gbc_buttonDescriptorStart = new GridBagConstraints();
		gbc_buttonDescriptorStart.insets = new Insets(0, 0, 5, 0);
		gbc_buttonDescriptorStart.fill = GridBagConstraints.BOTH;
		gbc_buttonDescriptorStart.gridx = 2;
		gbc_buttonDescriptorStart.gridy = 2;
		panelDescriptor.add(buttonDescriptorStart, gbc_buttonDescriptorStart);
		
		scrollPaneDescriptorStatus = new JScrollPane();
		GridBagConstraints gbc_scrollPaneDescriptorStatus = new GridBagConstraints();
		gbc_scrollPaneDescriptorStatus.gridwidth = 3;
		gbc_scrollPaneDescriptorStatus.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneDescriptorStatus.gridx = 0;
		gbc_scrollPaneDescriptorStatus.gridy = 3;
		panelDescriptor.add(scrollPaneDescriptorStatus, gbc_scrollPaneDescriptorStatus);
		
		textAreaDescriptorStatus = new JTextArea();
		scrollPaneDescriptorStatus.setViewportView(textAreaDescriptorStatus);
		
		panelPreprocessor = new JPanel();
		panelPreprocessor.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Preprocessor", null, panelPreprocessor, null);
		GridBagLayout gbl_panelPreprocessor = new GridBagLayout();
		gbl_panelPreprocessor.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelPreprocessor.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelPreprocessor.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelPreprocessor.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelPreprocessor.setLayout(gbl_panelPreprocessor);
		
		labelPreprocessorVarDesFile = new JLabel("Variable Description");
		GridBagConstraints gbc_labelPreprocessorVarDesFile = new GridBagConstraints();
		gbc_labelPreprocessorVarDesFile.fill = GridBagConstraints.BOTH;
		gbc_labelPreprocessorVarDesFile.insets = new Insets(0, 0, 5, 5);
		gbc_labelPreprocessorVarDesFile.gridx = 0;
		gbc_labelPreprocessorVarDesFile.gridy = 0;
		panelPreprocessor.add(labelPreprocessorVarDesFile, gbc_labelPreprocessorVarDesFile);
		
		textFieldPreprocessorVarDesFile = new JTextField();
		GridBagConstraints gbc_textFieldPreprocessorVarDesFile = new GridBagConstraints();
		gbc_textFieldPreprocessorVarDesFile.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPreprocessorVarDesFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPreprocessorVarDesFile.gridx = 1;
		gbc_textFieldPreprocessorVarDesFile.gridy = 0;
		panelPreprocessor.add(textFieldPreprocessorVarDesFile, gbc_textFieldPreprocessorVarDesFile);
		textFieldPreprocessorVarDesFile.setColumns(10);
		
		buttonPreprocessorVarDesFileChooseFile = new JButton("Choose File...");
		GridBagConstraints gbc_buttonPreprocessorVarDesFileChooseFile = new GridBagConstraints();
		gbc_buttonPreprocessorVarDesFileChooseFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPreprocessorVarDesFileChooseFile.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPreprocessorVarDesFileChooseFile.gridx = 2;
		gbc_buttonPreprocessorVarDesFileChooseFile.gridy = 0;
		panelPreprocessor.add(buttonPreprocessorVarDesFileChooseFile, gbc_buttonPreprocessorVarDesFileChooseFile);
		
		labelPreprocessorRawFile = new JLabel("Raw Dataset");
		GridBagConstraints gbc_labelPreprocessorRawFile = new GridBagConstraints();
		gbc_labelPreprocessorRawFile.fill = GridBagConstraints.BOTH;
		gbc_labelPreprocessorRawFile.insets = new Insets(0, 0, 5, 5);
		gbc_labelPreprocessorRawFile.gridx = 0;
		gbc_labelPreprocessorRawFile.gridy = 1;
		panelPreprocessor.add(labelPreprocessorRawFile, gbc_labelPreprocessorRawFile);
		
		textFieldPreprocessorRawFile = new JTextField();
		GridBagConstraints gbc_textFieldPreprocessorRawFile = new GridBagConstraints();
		gbc_textFieldPreprocessorRawFile.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPreprocessorRawFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPreprocessorRawFile.gridx = 1;
		gbc_textFieldPreprocessorRawFile.gridy = 1;
		panelPreprocessor.add(textFieldPreprocessorRawFile, gbc_textFieldPreprocessorRawFile);
		textFieldPreprocessorRawFile.setColumns(10);
		
		buttonPreprocessorRawFileChooseFile = new JButton("Choose File...");
		GridBagConstraints gbc_buttonPreprocessorRawFileChooseFile = new GridBagConstraints();
		gbc_buttonPreprocessorRawFileChooseFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPreprocessorRawFileChooseFile.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPreprocessorRawFileChooseFile.gridx = 2;
		gbc_buttonPreprocessorRawFileChooseFile.gridy = 1;
		panelPreprocessor.add(buttonPreprocessorRawFileChooseFile, gbc_buttonPreprocessorRawFileChooseFile);
		
		buttonPreprocessorStart = new JButton("Start");
		buttonPreprocessorStart.setEnabled(false);
		GridBagConstraints gbc_buttonPreprocessorStart = new GridBagConstraints();
		gbc_buttonPreprocessorStart.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPreprocessorStart.fill = GridBagConstraints.BOTH;
		gbc_buttonPreprocessorStart.gridx = 2;
		gbc_buttonPreprocessorStart.gridy = 2;
		panelPreprocessor.add(buttonPreprocessorStart, gbc_buttonPreprocessorStart);
		
		scrollPanePreprocessorStatus = new JScrollPane();
		GridBagConstraints gbc_scrollPanePreprocessorStatus = new GridBagConstraints();
		gbc_scrollPanePreprocessorStatus.gridwidth = 3;
		gbc_scrollPanePreprocessorStatus.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePreprocessorStatus.gridx = 0;
		gbc_scrollPanePreprocessorStatus.gridy = 3;
		panelPreprocessor.add(scrollPanePreprocessorStatus, gbc_scrollPanePreprocessorStatus);
		
		textAreaPreprocessorStatus = new JTextArea();
		scrollPanePreprocessorStatus.setViewportView(textAreaPreprocessorStatus);
		
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
		gbl_panelChi.columnWidths = new int[]{306, 181, 0};
		gbl_panelChi.rowHeights = new int[]{0, 0, 0, 33, 30, 59, 0, 181, 241, 0};
		gbl_panelChi.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelChi.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panelChi.setLayout(gbl_panelChi);
		
		textFieldFile1 = new JTextField();
		textFieldFile1.setOpaque(false);
		textFieldFile1.setEditable(false);
		GridBagConstraints gbc_textFieldFile1 = new GridBagConstraints();
		gbc_textFieldFile1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFile1.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFile1.gridx = 0;
		gbc_textFieldFile1.gridy = 0;
		panelChi.add(textFieldFile1, gbc_textFieldFile1);
		textFieldFile1.setColumns(10);
		
		buttonChooseFile1 = new JButton("Choose Dataset 1...");
		GridBagConstraints gbc_buttonChooseFile1 = new GridBagConstraints();
		gbc_buttonChooseFile1.fill = GridBagConstraints.BOTH;
		gbc_buttonChooseFile1.insets = new Insets(0, 0, 5, 0);
		gbc_buttonChooseFile1.gridx = 1;
		gbc_buttonChooseFile1.gridy = 0;
		panelChi.add(buttonChooseFile1, gbc_buttonChooseFile1);
		
		textFieldFile2 = new JTextField();
		GridBagConstraints gbc_textFieldFile2 = new GridBagConstraints();
		gbc_textFieldFile2.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFile2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFile2.gridx = 0;
		gbc_textFieldFile2.gridy = 1;
		panelChi.add(textFieldFile2, gbc_textFieldFile2);
		textFieldFile2.setColumns(10);
		
		buttonChooseFile2 = new JButton("Choose Dataset 2...");
		GridBagConstraints gbc_buttonChooseFile2 = new GridBagConstraints();
		gbc_buttonChooseFile2.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonChooseFile2.insets = new Insets(0, 0, 5, 0);
		gbc_buttonChooseFile2.gridx = 1;
		gbc_buttonChooseFile2.gridy = 1;
		panelChi.add(buttonChooseFile2, gbc_buttonChooseFile2);
		
		cmbTestType = new JComboBox();
		cmbTestType.setModel(new DefaultComboBoxModel(new String[] {"Chi-Test", "Z-Score Test of Independence of Pooled Proportions (Subgroup vs Subgroup)", "Standard Error of Population (Population vs Subgroup)"}));
		GridBagConstraints gbc_cmbTestType = new GridBagConstraints();
		gbc_cmbTestType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTestType.insets = new Insets(0, 0, 5, 5);
		gbc_cmbTestType.gridx = 0;
		gbc_cmbTestType.gridy = 2;
		panelChi.add(cmbTestType, gbc_cmbTestType);
		
		buttonChiStart = new JButton("Start");
		buttonChiStart.setEnabled(false);
		GridBagConstraints gbc_buttonChiStart = new GridBagConstraints();
		gbc_buttonChiStart.fill = GridBagConstraints.BOTH;
		gbc_buttonChiStart.insets = new Insets(0, 0, 5, 0);
		gbc_buttonChiStart.gridx = 1;
		gbc_buttonChiStart.gridy = 2;
		panelChi.add(buttonChiStart, gbc_buttonChiStart);
		
		lblGetSamplesBy = new JLabel("Generate samples by:");
		GridBagConstraints gbc_lblGetSamplesBy = new GridBagConstraints();
		gbc_lblGetSamplesBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblGetSamplesBy.anchor = GridBagConstraints.EAST;
		gbc_lblGetSamplesBy.gridx = 0;
		gbc_lblGetSamplesBy.gridy = 3;
		panelChi.add(lblGetSamplesBy, gbc_lblGetSamplesBy);
		
		cmbSEPFeatures = new JComboBox();
		GridBagConstraints gbc_cmbSEPFeatures = new GridBagConstraints();
		gbc_cmbSEPFeatures.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbSEPFeatures.insets = new Insets(0, 0, 5, 0);
		gbc_cmbSEPFeatures.gridx = 1;
		gbc_cmbSEPFeatures.gridy = 3;
		panelChi.add(cmbSEPFeatures, gbc_cmbSEPFeatures);
		
		lblNewLabel = new JLabel("Enter Feature Code : ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		panelChi.add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldFeature = new JTextField();
		GridBagConstraints gbc_textFieldFeature = new GridBagConstraints();
		gbc_textFieldFeature.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFeature.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFeature.gridx = 1;
		gbc_textFieldFeature.gridy = 4;
		panelChi.add(textFieldFeature, gbc_textFieldFeature);
		textFieldFeature.setColumns(10);
		
		textAreaFeature = new JTextArea();
		textAreaFeature.setLineWrap(true);
		GridBagConstraints gbc_textAreaFeature = new GridBagConstraints();
		gbc_textAreaFeature.gridheight = 2;
		gbc_textAreaFeature.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaFeature.fill = GridBagConstraints.BOTH;
		gbc_textAreaFeature.gridx = 0;
		gbc_textAreaFeature.gridy = 5;
		panelChi.add(textAreaFeature, gbc_textAreaFeature);
		
		buttonEnterFeature = new JButton("Enter Code");
		GridBagConstraints gbc_buttonEnterFeature = new GridBagConstraints();
		gbc_buttonEnterFeature.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonEnterFeature.insets = new Insets(0, 0, 5, 0);
		gbc_buttonEnterFeature.gridx = 1;
		gbc_buttonEnterFeature.gridy = 5;
		panelChi.add(buttonEnterFeature, gbc_buttonEnterFeature);
		
		buttonStore = new JButton("Start");
		GridBagConstraints gbc_buttonStore = new GridBagConstraints();
		gbc_buttonStore.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonStore.insets = new Insets(0, 0, 5, 0);
		gbc_buttonStore.gridx = 1;
		gbc_buttonStore.gridy = 6;
		panelChi.add(buttonStore, gbc_buttonStore);
		
		listAttributes = new JList();
		GridBagConstraints gbc_listAttributes = new GridBagConstraints();
		gbc_listAttributes.insets = new Insets(0, 0, 5, 5);
		gbc_listAttributes.fill = GridBagConstraints.HORIZONTAL;
		gbc_listAttributes.gridx = 0;
		gbc_listAttributes.gridy = 7;
		panelChi.add(listAttributes, gbc_listAttributes);
		
		scrollPaneChiStatus = new JScrollPane();
		GridBagConstraints gbc_scrollPaneChiStatus = new GridBagConstraints();
		gbc_scrollPaneChiStatus.gridwidth = 2;
		gbc_scrollPaneChiStatus.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneChiStatus.gridx = 0;
		gbc_scrollPaneChiStatus.gridy = 8;
		panelChi.add(scrollPaneChiStatus, gbc_scrollPaneChiStatus);
		
		textAreaChiStatus = new JTextArea();
		textAreaChiStatus.setLineWrap(true);
		textAreaChiStatus.setWrapStyleWord(true);
		scrollPaneChiStatus.setViewportView(textAreaChiStatus);
		
	}
	
	//NEW GETTERS FOR CHI TEST PANEL
	
	public JComboBox getCmbSEPFeatures() {
		return cmbSEPFeatures;
	}

	public JLabel getLblGetSamplesBy() {
		return lblGetSamplesBy;
	}

	public JButton getButtonEnterFeature() {
		return buttonEnterFeature;
	}
	
	public JButton getButtonStore() {
		return buttonStore;
	}
	
	public JList getListAttributes() {
		return listAttributes;
	}
	
	public JTextField getTextFieldFeature() {
		return textFieldFeature;
	}

	public JTextArea getTextAreaFeature() {
		return textAreaFeature;
	}
	
	//END OF NEW GETTERS

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

	public JCheckBox getCheckboxKMeansHeader() {
		return checkboxKMeansHeader;
	}
	
	public JTextField getTextFieldFile1() {
		return textFieldFile1;
	}

	public JButton getButtonChooseFile1() {
		return buttonChooseFile1;
	}

	public JTextField getTextFieldFile2() {
		return textFieldFile2;
	}

	public JButton getButtonChooseFile2() {
		return buttonChooseFile2;
	}

	public JButton getButtonChiChooseFiles() {
		return buttonChooseFile1;
	}

	public JTextArea getTextAreaChiStatus() {
		return textAreaChiStatus;
	}

	public JButton getButtonChiStart() {
		return buttonChiStart;
	}

	public JTextField getTextFieldDescriptorVarFile() {
		return textFieldDescriptorVarFile;
	}

	public JButton getButtonDescriptorVarFileChooseFile() {
		return buttonDescriptorVarFileChooseFile;
	}

	public JTextField getTextFieldDescriptorValFile() {
		return textFieldDescriptorValFile;
	}

	public JButton getButtonDescriptorValFileChooseFile() {
		return buttonDescriptorValFileChooseFile;
	}

	public JButton getButtonDescriptorStart() {
		return buttonDescriptorStart;
	}

	public JTextArea getTextAreaDescriptorStatus() {
		return textAreaDescriptorStatus;
	}

	public JTextField getTextFieldPreprocessorVarDesFile() {
		return textFieldPreprocessorVarDesFile;
	}

	public JTextField getTextFieldPreprocessorRawFile() {
		return textFieldPreprocessorRawFile;
	}

	public JButton getButtonPreprocessorVarDesFileChooseFile() {
		return buttonPreprocessorVarDesFileChooseFile;
	}

	public JButton getButtonPreprocessorRawFileChooseFile() {
		return buttonPreprocessorRawFileChooseFile;
	}

	public JButton getButtonPreprocessorStart() {
		return buttonPreprocessorStart;
	}

	public JTextArea getTextAreaPreprocessorStatus() {
		return textAreaPreprocessorStatus;
	}
	
	

}
