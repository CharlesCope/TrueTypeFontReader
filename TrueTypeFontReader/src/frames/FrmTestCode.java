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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
// Code Came from here.
// http://www.udel.edu/CIS/software/dist/batik-src/xml-batik/sources/org/apache/batik/svggen/




import Fonts.ChcFont;
import Fonts.PDFFont;
import Fonts.fontToPDFfont;

public class FrmTestCode extends JFrame {
	private static final long serialVersionUID = 3607491399201743045L;
	private JPanel contentPane;
	private JComboBox<String> cboFonts;
	private JTextArea txtDisplayResults;
	private String path;
	final public static String JavaNewLine = System.getProperty("line.separator");
	
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
		setBounds(100, 100, 667, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRunCode = new JButton("Run Code");
		btnRunCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runCode();
			}
		});
		btnRunCode.setBounds(289, 12, 89, 30);
		contentPane.add(btnRunCode);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 100, 626, 154);
		contentPane.add(scrollPane);
		
		txtDisplayResults = new JTextArea();
		txtDisplayResults.setLocation(35, 0);
		scrollPane.setViewportView(txtDisplayResults);
		
		cboFonts = new JComboBox<String>();
		cboFonts.setBackground(Color.WHITE);
		cboFonts.setBounds(15, 59, 363, 30);
		contentPane.add(cboFonts);
		
		JLabel lblNewLabel = new JLabel("Select Font File");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(15, 12, 262, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnTestPDFontCode = new JButton("Test PDF Font Code");
		btnTestPDFontCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileName = path + File.separator + "Fonts"+ File.separator +(String) cboFonts.getSelectedItem();
				
				PDFFont myPDFFont = fontToPDFfont.ConvertFontFileToPDFFont(fileName);
				txtDisplayResults.setText(myPDFFont.toString());
			}
		});
		btnTestPDFontCode.setBounds(428, 59, 129, 30);
		contentPane.add(btnTestPDFontCode);
		// Give them a list to choose from.
		listFonts();
	}
	
	private void runCode(){

		String fileName = path + File.separator + "Fonts"+ File.separator +(String) cboFonts.getSelectedItem();
		short FamilyID = 1;
		short SubfamilyID = 2;
		short FontIdentifierID = 3;
		
		if(fileName.isEmpty()){return;}
		ChcFont myFont = new ChcFont().create(fileName);
		
		String strToDisplay = "Name > ";
		strToDisplay += myFont.getNameTable().getRecord(FamilyID);
		strToDisplay += JavaNewLine;
		strToDisplay += "Font style > ";
		strToDisplay += myFont.getNameTable().getRecord(SubfamilyID);
		strToDisplay += JavaNewLine;
		strToDisplay += "Font Identifier > ";
		strToDisplay += myFont.getNameTable().getRecord(FontIdentifierID);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags > ";
		strToDisplay += String.valueOf(myFont.getHeadTable().getFlags());
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(0);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(1);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(2);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(3);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(4);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(5);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(6);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(7);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(8);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(9);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(10);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(11);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(12);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(13);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(14);
		strToDisplay += JavaNewLine;
		strToDisplay += "Flags Bits > ";
		strToDisplay += myFont.getHeadTable().getFlags(15);
		strToDisplay += JavaNewLine;
		strToDisplay += "Ascender > ";
		strToDisplay += String.valueOf(myFont.getHheaTable().getAscender());
		strToDisplay += JavaNewLine;
		strToDisplay += "Descender > ";
		strToDisplay += String.valueOf(myFont.getHheaTable().getDescender());
		strToDisplay += JavaNewLine;
		strToDisplay += "min Left Side Bearing > ";
		strToDisplay += String.valueOf(myFont.getHheaTable().getMinLeftSideBearing());
		strToDisplay += JavaNewLine;
		strToDisplay += "Number Of HMetrics > ";
		strToDisplay += String.valueOf(myFont.getHheaTable().getNumberOfHMetrics());
		strToDisplay += JavaNewLine;
		strToDisplay += "Caret Slope Run > ";
		strToDisplay += String.valueOf(myFont.getHheaTable().getCaretSlopeRun());
		strToDisplay += JavaNewLine;
		strToDisplay += "Advance Width Max > ";
		strToDisplay += String.valueOf(myFont.getHheaTable().getAdvanceWidthMax());
		strToDisplay += JavaNewLine;
		strToDisplay += "Metric Data Format > ";
		strToDisplay += String.valueOf(myFont.getHheaTable().getMetricDataFormat());
		strToDisplay += JavaNewLine;
		strToDisplay += "Italic Angle > ";
		strToDisplay += String.valueOf(myFont.getPostTable().getItalicAngle());
		
		strToDisplay += JavaNewLine;
		strToDisplay += "PostScript Name (PDF BaseFont) > ";
		short PostScriptId = 6;
		strToDisplay += String.valueOf(myFont.getNameTable().getRecord(PostScriptId));
		
		
		// Here is where we build the output of the code.
		

		txtDisplayResults.setText(strToDisplay);

		
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
