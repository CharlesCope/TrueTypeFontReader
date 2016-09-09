package frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
// Code Came from here.
// http://www.udel.edu/CIS/software/dist/batik-src/xml-batik/sources/org/apache/batik/svggen/
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Fonts.ChcFont;
import Fonts.PDFFont;
import Fonts.fontToPDFfont;
import Fonts.table.CmapFormat0;
import Fonts.table.CmapFormat2;
import Fonts.table.CmapFormat4;
import Fonts.table.CmapFormat6;



public class FrmTestCode extends JFrame {
	private static final long serialVersionUID = 3607491399201743045L;
	private JPanel contentPane;
	private JComboBox<String> cboFonts;
	private JTextArea txtDisplayResults;
	private String path;
	private JScrollPane scrollPaneTable;
	final public static String JavaNewLine = System.getProperty("line.separator");
	private JTable fontTable;
	DefaultTableModel model;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTestCode frame = new FrmTestCode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmTestCode() {
		setTitle("Test Code Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 52, 670, 128);
		contentPane.add(scrollPane);
		
		txtDisplayResults = new JTextArea();
		txtDisplayResults.setLocation(35, 0);
		scrollPane.setViewportView(txtDisplayResults);
		
		cboFonts = new JComboBox<String>();
		cboFonts.setBackground(Color.WHITE);
		cboFonts.setBounds(118, 9, 363, 30);
		contentPane.add(cboFonts);
		
		JLabel lblNewLabel = new JLabel("Select Font File");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(15, 12, 111, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnPDFFontDictionary = new JButton("Get PDF font dictionary");
		btnPDFFontDictionary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileName = path + File.separator + "Fonts"+ File.separator +(String) cboFonts.getSelectedItem();
				
				PDFFont myPDFFont = fontToPDFfont.ConvertFontFileToPDFFont(fileName);
				txtDisplayResults.setText(myPDFFont.toString());
				//TODO: I think I did not finish this part of the code.
				myPDFFont.getGlyphWidthsToPDFWidths();
				
				model = (DefaultTableModel) fontTable.getModel();
				model.setColumnIdentifiers(new String[] {"Unicode", "Character", "Symbol", "GlyphID", "PDF Width"});

				refreshTableModel();
//				Check what format of table are we getting
				if (myPDFFont.getCmapFormat() != null) {
					if (myPDFFont.getCmapFormat().getFormat() == 4) {
						CmapFormat4 cmapFormat4 = (CmapFormat4) myPDFFont.getCmapFormat();
						getGlyphsAndWidths(cmapFormat4.getGlyphIdArray(), myPDFFont);
					}else if(myPDFFont.getCmapFormat().getFormat() == 2){
						CmapFormat2 cmapFormat2 = (CmapFormat2) myPDFFont.getCmapFormat();
						getGlyphsAndWidths(cmapFormat2.getGlyphIndexArray(), myPDFFont);
					}else if(myPDFFont.getCmapFormat().getFormat() == 0){
						CmapFormat0 cmapFormat0 = (CmapFormat0) myPDFFont.getCmapFormat();
						getGlyphsAndWidths(cmapFormat0.getGlyphIdArray(), myPDFFont);
					}else if(myPDFFont.getCmapFormat().getFormat() == 6){
						CmapFormat6 cmapFormat6 = (CmapFormat6) myPDFFont.getCmapFormat();
						getGlyphsAndWidths(cmapFormat6.getGlyphIdArray(), myPDFFont);
					}
				}
				// Show data center in table.
				DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
				dtcr.setHorizontalAlignment(SwingConstants.CENTER);
				fontTable.getColumnModel().getColumn(0).setCellRenderer(dtcr);
				fontTable.getColumnModel().getColumn(1).setCellRenderer(dtcr);
				fontTable.getColumnModel().getColumn(2).setCellRenderer(dtcr);
				fontTable.getColumnModel().getColumn(3).setCellRenderer(dtcr);
				fontTable.getColumnModel().getColumn(4).setCellRenderer(dtcr);
				scrollPaneTable.setViewportView(fontTable);
				
			}
		});
		btnPDFFontDictionary.setBounds(491, 9, 195, 30);
		contentPane.add(btnPDFFontDictionary);
		
		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(15, 234, 670, 128);
		contentPane.add(scrollPaneTable);
		
		fontTable = new JTable();
		
		
		// Give them a list to choose from.
		listFonts();
	}
	public static String addZeros(String a)
	{
		int i=0;
		i=a.length();
		if ( i == 4 )
		return a;
		else
		{
		int j= 4 - i;
		for (int k=0; k<j; k++)
		{
		a="0"+a;
		}
		return a;
		}
		}
	
	public char convertToChar(char[] charArray){
		char f = 0;
		for (char c : charArray) {
			f+=c;
		}
		return f;
	}
	
	public void refreshTableModel(){
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}
	}
	
	public void getGlyphsAndWidths(int[] glyphIDs, PDFFont myPDFFont){
		for (int i : glyphIDs) {
//			Convert GlyphID to Hex Value then add Leading zeroes.
			String temp = "\\u"+addZeros(Integer.toHexString(i));
			String unicode = "U+"+addZeros(Integer.toHexString(i));
			char symbol = convertToChar(temp.toCharArray());
//			TODO: Sarah need to get the PDF Width here...
//			Calculate the pdf widths per glyph using their glyphID
			int pdfwidth;
			try {
				pdfwidth = fontToPDFfont.pdfScalingFormula(fontToPDFfont.getMyChcFont().getGlyph(i).getAdvanceWidth(), myPDFFont.getUnitsPerEm());
			} catch (Exception e) {
				pdfwidth = 0;
			}
			model.addRow(new Object[]{unicode,(int)symbol,symbol,i,pdfwidth});
		}
	}
	public void getGlyphsAndWidths(short[] glyphIDs, PDFFont myPDFFont){
		for (int i : glyphIDs) {
//			Convert GlyphID to Hex Value then add Leading zeroes.
			String temp = "\\u"+addZeros(Integer.toHexString(i));
			String unicode = "U+"+addZeros(Integer.toHexString(i));
			char symbol = convertToChar(temp.toCharArray());
//			TODO: Sarah need to get the PDF Width here...
//			Calculate the pdf widths per glyph using their glyphID
			int pdfwidth;
			try {
				pdfwidth = fontToPDFfont.pdfScalingFormula(fontToPDFfont.getMyChcFont().getGlyph(i).getAdvanceWidth(), myPDFFont.getUnitsPerEm());
			} catch (Exception e) {
				pdfwidth = 0;
			}
			model.addRow(new Object[]{unicode,(int)symbol,symbol,i,pdfwidth});
		}
	}
	public void listFonts(){
		path = System.getenv("WINDIR");
		File directory =  new File(path, "Fonts");
		//get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList){
			if (file.isFile()){
				if(file.getName().endsWith("ttf")){cboFonts.addItem(file.getName());}
			}
		}
	}
}
